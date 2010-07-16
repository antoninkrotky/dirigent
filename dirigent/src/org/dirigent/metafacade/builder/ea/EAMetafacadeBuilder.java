package org.dirigent.metafacade.builder.ea;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import org.dirigent.config.ConfigSchemaDao;
import org.dirigent.metafacade.IAttribute;
import org.dirigent.metafacade.IElement;
import org.dirigent.metafacade.builder.MetafacadeBuilder;
import org.dirigent.metafacade.builder.decorator.SchemaDecorator;
import org.dirigent.metafacade.builder.ea.dao.EAAttributeDAO;
import org.dirigent.metafacade.builder.ea.dao.EAConnectorDAO;
import org.dirigent.metafacade.builder.ea.dao.EAElementDAO;
import org.dirigent.metafacade.builder.ea.dao.EAObjectDao;
import org.dirigent.metafacade.builder.ea.decorator.EAAttributteDecorator;
import org.dirigent.metafacade.builder.ea.decorator.EAColumnDecorator;
import org.dirigent.metafacade.builder.ea.decorator.EAColumnMappingDecorator;
import org.dirigent.metafacade.builder.ea.decorator.EADimensionColumnDecorator;
import org.dirigent.metafacade.builder.ea.decorator.EADimensionDecorator;
import org.dirigent.metafacade.builder.ea.decorator.EADomainDecorator;
import org.dirigent.metafacade.builder.ea.decorator.EAMappingDecorator;
import org.dirigent.metafacade.builder.ea.decorator.EAPackageDecorator;
import org.dirigent.metafacade.builder.ea.decorator.EATableDecorator;
import org.dirigent.metafacade.builder.ea.vo.EAAttributeVO;
import org.dirigent.metafacade.builder.ea.vo.EAConnectorVO;
import org.dirigent.metafacade.builder.ea.vo.EAElementVO;
import org.dirigent.metafacade.builder.vo.ObjectVO;

public class EAMetafacadeBuilder extends MetafacadeBuilder {

	private EAObjectDao objectDao = new EAObjectDao();
	private EAElementDAO elementDao=new EAElementDAO();
	private EAAttributeDAO attributeDao=new EAAttributeDAO();
	private EAConnectorDAO connectorDao=new EAConnectorDAO();
		
	private ConfigSchemaDao schemaDao=new ConfigSchemaDao();


	@Override
	public IElement getMetafacade(String uri) {
		if (uri.startsWith("schema:")) {
			return new SchemaDecorator(schemaDao.getSchemaVO(uri));
		}


		EAElementVO v = elementDao.getEAElement(uri);

		if (v != null) {
			if ("Class".equals(v.type) && "BIDimension".equals(v.stereotype)) {
				return new EADimensionDecorator(v);
			} else if ("Class".equals(v.type) && "BIMapping".equals(v.stereotype)) {
				return new EAMappingDecorator(v);
			} else if ("Class".equals(v.type) && "BIDomain".equals(v.stereotype)) {
				return new EADomainDecorator(v);
			} else if ("Class".equals(v.type) && "table".equals(v.stereotype)) {
				return new EATableDecorator(v);
			} else if ("Package".equals(v.type)) {
				return new EAPackageDecorator(v);
			}
		}
		return null;
	}
	
	public Collection<EAConnectorVO> getStartingConnectors(String elementUri) {
		return connectorDao.getStartingConnectors(elementUri);
	}
	


	@Override
	public void save(IElement element) {
			throw new RuntimeException("Save not supported for "
					+ element.getClass().getName());

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

	@Override
	public Collection<IAttribute> getAttributes(String elementURI) {
		Collection<EAAttributeVO> c=attributeDao.getAttributes(elementURI);
		Collection<IAttribute> res=new ArrayList<IAttribute>(c.size());
		Iterator<EAAttributeVO> i=c.iterator();
		while (i.hasNext()) {
			EAAttributeVO a=i.next();
			if ("column".equals(a.stereotype)) {
				res.add(new EAColumnDecorator(a));
			} else if ("BIDimensionColumn".equals(a.stereotype)) {
				res.add(new EADimensionColumnDecorator(a));
			} else if ("BIMappingColumn".equals(a.stereotype)) {				
				res.add(new EAColumnMappingDecorator(a));
			}else {
				res.add(new EAAttributteDecorator(a));
			}
		}
		return res;
	}
	
	

}
