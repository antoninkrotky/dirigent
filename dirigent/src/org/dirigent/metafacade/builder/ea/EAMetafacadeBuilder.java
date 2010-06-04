package org.dirigent.metafacade.builder.ea;

import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import org.dirigent.config.ConfigSchemaDao;
import org.dirigent.metafacade.IDimension;
import org.dirigent.metafacade.IElement;
import org.dirigent.metafacade.IPackage;
import org.dirigent.metafacade.ITable;
import org.dirigent.metafacade.builder.MetafacadeBuilder;
import org.dirigent.metafacade.builder.decorator.DimensionDecorator;
import org.dirigent.metafacade.builder.decorator.MappingDecorator;
import org.dirigent.metafacade.builder.decorator.PackageDecorator;
import org.dirigent.metafacade.builder.decorator.SchemaDecorator;
import org.dirigent.metafacade.builder.decorator.TableDecorator;
import org.dirigent.metafacade.builder.ea.vo.EAElementVO;
import org.dirigent.metafacade.builder.vo.DimensionVO;
import org.dirigent.metafacade.builder.vo.DomainVO;
import org.dirigent.metafacade.builder.vo.ElementVO;
import org.dirigent.metafacade.builder.vo.ObjectVO;
import org.dirigent.metafacade.builder.vo.TableVO;

public class EAMetafacadeBuilder extends MetafacadeBuilder {

	private EAObjectDao objectDao = new EAObjectDao();
	private EADimensionDao dimensionDao = new EADimensionDao();
	private EADomainDao domainDao = new EADomainDao();
	private EAMappingDao mappingDao = new EAMappingDao();
	private EAElementDAO elementDao=new EAElementDAO();
	private EAObjectPropertyDAO objectPropertyDao=new EAObjectPropertyDAO();
	
	private ConfigSchemaDao schemaDao=new ConfigSchemaDao();


	@Override
	public IElement getMetafacade(String uri) {
		if (uri.startsWith("schema:")) {
			return new SchemaDecorator(schemaDao.getSchemaVO(uri));
		}


		ObjectVO v = objectDao.getObject(uri);

		if (v != null) {
			if ("Class".equals(v.type) && "BIDimension".equals(v.stereotype)) {
				return new DimensionDecorator(dimensionDao.getDimension(uri));
			} else if ("Class".equals(v.type) && "BIMapping".equals(v.stereotype)) {
				return new MappingDecorator(mappingDao.getMapping(uri));
			} else if ("Class".equals(v.type) && "table".equals(v.stereotype)) {
				return createTable(uri);
			} else if ("Package".equals(v.type)) {
				return createPackage(uri);
			}
		}
		return null;
	}
	
	private IPackage createPackage(String uri) {
		EAElementVO v=elementDao.getEAElement(uri);
		ElementVO t=new ElementVO();
		initElement(t, v);
		//for package is stored in PDATA1 field.
		if (v.pdata1!=null) {
			t.id=Long.parseLong(v.pdata1);
		}
		return new PackageDecorator(t);
	}

	private void initElement(ElementVO v,EAElementVO c) {
		v.uri=c.guid;
		v.id=c.objectId;
		v.name=c.name;
		v.type=c.type;
		v.description=c.note;
		v.stereotype=c.stereotype;
		v.packageId=c.packageId;
		v.properties=objectPropertyDao.getObjectProperties(c.objectId);
		
	}
	
	private ITable createTable(String uri) {
		EAElementVO v=elementDao.getEAElement(uri);
		TableVO t=new TableVO();
		initElement(t, v);	
		t.codeName=v.alias;
		t.schemaUri="schema:default";
		return new TableDecorator(t);
		
	}

	@Override
	public void save(IElement element) {
		if (element instanceof IDimension) {
			dimensionDao.merge((DimensionVO) element.getValueObject());
		} else {
			throw new RuntimeException("Save not supported for "
					+ element.getClass().getName());
		}

	}

	@Override
	public Collection<DomainVO> getDomains() {
		return domainDao.getDomains();
	}

	@Override
	public Collection<ObjectVO> getChildObjects(String uri) {
		return objectDao.getChildObjects(uri);
	}

	@Override
	public Vector<IElement> getChildElements(String uri) {
		Collection<EAElementVO> c=elementDao.getPackageElements(uri);
		Vector<IElement> r=new Vector<IElement>(c.size());
		Iterator<EAElementVO> i=c.iterator();
		while (i.hasNext()) {
			r.add(MetafacadeBuilder.getMetafacadeBuilder().getMetafacade(i.next().guid));
		}
		return r;
	}

}
