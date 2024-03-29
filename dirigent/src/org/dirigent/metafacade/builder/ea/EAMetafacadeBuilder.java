package org.dirigent.metafacade.builder.ea;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import org.dirigent.config.ConfigSchemaDao;
import org.dirigent.metafacade.IElement;
import org.dirigent.metafacade.IRelation;
import org.dirigent.metafacade.builder.MetafacadeBuilder;
import org.dirigent.metafacade.builder.decorator.SchemaDecorator;
import org.dirigent.metafacade.builder.ea.dao.EAConnectorDAO;
import org.dirigent.metafacade.builder.ea.dao.EADiagramDAO;
import org.dirigent.metafacade.builder.ea.dao.EAElementDAO;
import org.dirigent.metafacade.builder.ea.dao.EAObjectDao;
import org.dirigent.metafacade.builder.ea.decorator.EAClassDecorator;
import org.dirigent.metafacade.builder.ea.decorator.EADiagramDecorator;
import org.dirigent.metafacade.builder.ea.decorator.EADimensionDecorator;
import org.dirigent.metafacade.builder.ea.decorator.EADomainDecorator;
import org.dirigent.metafacade.builder.ea.decorator.EAFactTableDecorator;
import org.dirigent.metafacade.builder.ea.decorator.EAMappingDecorator;
import org.dirigent.metafacade.builder.ea.decorator.EAPackageDecorator;
import org.dirigent.metafacade.builder.ea.decorator.EARelationDecorator;
import org.dirigent.metafacade.builder.ea.decorator.EATableDecorator;
import org.dirigent.metafacade.builder.ea.vo.EAConnectorVO;
import org.dirigent.metafacade.builder.ea.vo.EADiagramVO;
import org.dirigent.metafacade.builder.ea.vo.EAElementVO;
import org.dirigent.metafacade.builder.vo.ObjectVO;

public class EAMetafacadeBuilder extends MetafacadeBuilder {

	private EAObjectDao objectDao = new EAObjectDao();
	private EAElementDAO elementDao = new EAElementDAO();
	private EAConnectorDAO connectorDao = new EAConnectorDAO();
	private EADiagramDAO diagramDao = new EADiagramDAO();

	private ConfigSchemaDao schemaDao = new ConfigSchemaDao();
	private static ThreadLocal<HashMap<String, IElement>> localCache=new ThreadLocal<HashMap<String,IElement>>();
	
	@Override
	public IElement getMetafacade(String uri) {
		HashMap<String, IElement> cache=localCache.get();
		if (cache==null) {
			cache=new HashMap<String, IElement>();
			localCache.set(cache);
		}
		IElement element=cache.get(uri);
		if (element==null) {
			element=createMetafacade(uri);
			cache.put(uri, element);
		}
		return element;
	}
	
	
	protected IElement createMetafacade(String uri) {
		// SCHEMA
		if (uri.startsWith("schema:")) {
			return new SchemaDecorator(schemaDao.getSchemaVO(uri)){
				@Override
				public IElement getGeneralizedParent() {
					return null;
				}};
		}
		// OBJECT
		EAElementVO v = elementDao.getEAElement(uri);
		if (v != null) {
			if ("Class".equals(v.type) && "BIDimension".equals(v.stereotype)) {
				return new EADimensionDecorator(v);
			}
			if ("Class".equals(v.type) && "BIFact".equals(v.stereotype)) {
				return new EAFactTableDecorator(v);
			}else if ("Class".equals(v.type)
					&& "BIMapping".equals(v.stereotype)) {
				return new EAMappingDecorator(v);
			} else if ("Class".equals(v.type)
					&& "BIDomain".equals(v.stereotype)) {
				return new EADomainDecorator(v);
			} else if ("Class".equals(v.type) && "table".equals(v.stereotype)) {
				return new EATableDecorator(v);
			}
			else if ("Class".equals(v.type) && "BIStage".equals(v.stereotype)) {
				return new EATableDecorator(v);
			}
			else if ("Package".equals(v.type)) {
				return new EAPackageDecorator(v);
			} else {
				return new EAClassDecorator(v);
			}
		}
		// DIAGRAM
		EADiagramVO d = diagramDao.getDiagram(uri);
		if (d != null) {
			return new EADiagramDecorator(d);
		}

		return null;
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
		Collection<EAElementVO> c = elementDao.getPackageElements(uri);
		Vector<IElement> r = new Vector<IElement>(c.size());
		Iterator<EAElementVO> i = c.iterator();
		while (i.hasNext()) {
			r.add(MetafacadeBuilder.getMetafacadeBuilder().getMetafacade(
					i.next().guid));
		}
		return r;
	}

	
	@Deprecated
	public Collection<EAConnectorVO> getStartingConnectors(String elementUri) {
		return connectorDao.getStartingConnectors(elementUri);
	}

	@Override
	public Collection<IRelation> getStartingRelations(String elementUri) {
		return EARelationDecorator.convertCollection(connectorDao
				.getStartingConnectors(elementUri));
	}

	@Override
	public Collection<IRelation> getEndingRelations(String elementUri) {
		return EARelationDecorator.convertCollection(connectorDao
				.getEndingConnectors(elementUri));
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.builder.MetafacadeBuilder#clearCache()
	 */
	@Override
	public void clearCache() {
		localCache.set(null);		
	}




}
