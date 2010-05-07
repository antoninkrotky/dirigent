package org.dirigent.metafacade.builder.ea;

import java.util.Collection;

import org.dirigent.config.ConfigSchemaDao;
import org.dirigent.metafacade.IDimension;
import org.dirigent.metafacade.IElement;
import org.dirigent.metafacade.builder.MetafacadeBuilder;
import org.dirigent.metafacade.builder.decorator.DimensionDecorator;
import org.dirigent.metafacade.builder.decorator.MappingDecorator;
import org.dirigent.metafacade.builder.decorator.SchemaDecorator;
import org.dirigent.metafacade.builder.decorator.TableDecorator;
import org.dirigent.metafacade.builder.vo.DimensionVO;
import org.dirigent.metafacade.builder.vo.DomainVO;
import org.dirigent.metafacade.builder.vo.ObjectVO;

public class EAMetafacadeBuilder extends MetafacadeBuilder {

	private EAObjectDao objectDao = new EAObjectDao();
	private EADimensionDao dimensionDao = new EADimensionDao();
	private EADomainDao domainDao = new EADomainDao();
	private EAMappingDao mappingDao = new EAMappingDao();
	private EATableDao tableDao = new EATableDao();
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
				return new TableDecorator(tableDao.getTable(uri));
			} 
		}
		return null;
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
	public Collection<ObjectVO> getChildObjects(ObjectVO object) {
		return objectDao.getChildObjects(object);
	}

}
