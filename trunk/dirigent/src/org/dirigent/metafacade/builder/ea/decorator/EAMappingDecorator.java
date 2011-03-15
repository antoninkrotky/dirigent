package org.dirigent.metafacade.builder.ea.decorator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import org.dirigent.metafacade.IAttribute;
import org.dirigent.metafacade.IColumnMapping;
import org.dirigent.metafacade.IElement;
import org.dirigent.metafacade.IMapping;
import org.dirigent.metafacade.IRelation;
import org.dirigent.metafacade.ISchema;
import org.dirigent.metafacade.ITable;
import org.dirigent.metafacade.builder.MetafacadeBuilder;
import org.dirigent.metafacade.builder.decorator.ColumnMappingDecorator;
import org.dirigent.metafacade.builder.decorator.MappingDecorator;
import org.dirigent.metafacade.builder.ea.EAMetafacadeBuilder;
import org.dirigent.metafacade.builder.ea.dao.EAConnectorTagDAO;
import org.dirigent.metafacade.builder.ea.vo.EAElementVO;
import org.dirigent.metafacade.builder.vo.MappingSourceVO;
import org.dirigent.metafacade.builder.vo.MappingVO;

public class EAMappingDecorator extends EAClassDecorator implements IMapping {

	private MappingDecorator mappingDecorator;

	public EAMappingDecorator(EAElementVO ea) {
		super(ea);
		mappingDecorator = new MappingDecorator(init(ea, new MappingVO())) {
			@Override
			public Collection<IColumnMapping> getColumnMappings() {
				if (columnMappings == null) {
					columnMappings = new ArrayList<IColumnMapping>();
					for (IAttribute a : getAttributes()) {
						if (a instanceof EAColumnMappingDecorator) {
							EAColumnMappingDecorator m = (EAColumnMappingDecorator) a;
							m.setMapping(this);
							columnMappings.add(m);
						}
						/*
						 * EAColumnMappingDecorator m = new
						 * EAColumnMappingDecorator( ((EAAttributteDecorator)
						 * a).getEAAttribute()); m.setMapping(this);
						 */

					}
				}
				return super.getColumnMappings();
			}

			protected void initColumnMappings() {
				columnMappings = new ArrayList<IColumnMapping>();
				for (IAttribute a : getAttributes()) {
					if (a instanceof ColumnMappingDecorator) {
						ColumnMappingDecorator cm = (ColumnMappingDecorator) a;
						cm.setMapping(this);
						columnMappings.add(cm);
					}
				}
			}
		};

	}

	private static Collection<MappingSourceVO> getMapingSourceVOs(
			String elementUri) {
		Iterator<IRelation> i = ((EAMetafacadeBuilder) MetafacadeBuilder
				.getMetafacadeBuilder()).getStartingRelations(elementUri)
				.iterator();
		Collection<MappingSourceVO> res = new ArrayList<MappingSourceVO>();
		while (i.hasNext()) {
			EARelationDecorator v = (EARelationDecorator) i.next();
			if ("Dependency".equals(v.getType())
					&& "BIMappingSource".equals(v.getStereotype())) {
				MappingSourceVO s = new MappingSourceVO();
				s.alias = v.getName();
				s.id = v.getValueObject().id;
				Map<String, String> properties = new EAConnectorTagDAO()
						.getObjectProperties(s.id);
				s.joinCondition = properties.get("joinCondition");
				s.joinType = properties.get("joinType");
				s.joinOrder = properties.get("joinOrder");
				s.mappingUri = v.getStartElementUri();
				s.sourceUri = v.getEndElementUri();
				s.uri = v.getUri();
				res.add(s);
			}

		}
		return res;
	}

	private static MappingVO init(EAElementVO ea, MappingVO v) {
		EAClassDecorator.init(ea, v);
		v.businessRule = ea.note;
		v.schemaUri = "schema:default";
		v.sources = getMapingSourceVOs(v.uri);
		v.targetTableUri = getTargetTableUri(v.uri);
		v.filterCondition = v.getProperties().get("filterCondition");
		v.groupByClause = v.getProperties().get("groupByClause");
		v.orderByClause = v.getProperties().get("orderByClause");
		v.pattern = v.getProperties().get("pattern");
		return v;
	}

	private static String getTargetTableUri(String elementUri) {
		Iterator<IRelation> i = ((EAMetafacadeBuilder) MetafacadeBuilder
				.getMetafacadeBuilder()).getStartingRelations(elementUri)
				.iterator();
		while (i.hasNext()) {
			IRelation v = i.next();
			if ("Dependency".equals(v.getType())
					&& "BIMappingTarget".equals(v.getStereotype())) {
				return v.getEndElementUri();
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dirigent.metafacade.builder.decorator.MappingDecorator#getSchema()
	 */
	@Override
	public ISchema getSchema() {
		return EACommon.getSchema(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dirigent.metafacade.IQueriable#getSQLQuery()
	 */
	@Override
	public String getSQLQuery() {
		return mappingDecorator.getSQLQuery();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dirigent.metafacade.IQueriable#getSQLQuery(int)
	 */
	@Override
	public String getSQLQuery(int offset) {
		return mappingDecorator.getSQLQuery(offset);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dirigent.metafacade.IMapping#getTargetTable()
	 */
	@Override
	public ITable getTargetTable() {
		return mappingDecorator.getTargetTable();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dirigent.metafacade.IMapping#getSources()
	 */
	@Override
	public Map<MappingSourceVO, IElement> getSources() {
		return mappingDecorator.getSources();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dirigent.metafacade.IMapping#getTargetColumnList()
	 */
	@Override
	public String getTargetColumnList() {
		return mappingDecorator.getTargetColumnList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dirigent.metafacade.IMapping#injectSubqueries(java.lang.String)
	 */
	@Override
	public String injectSubqueries(String template) {
		return mappingDecorator.injectSubqueries(template);
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.IMapping#getColumnMappings()
	 */
	@Override
	public Collection<IColumnMapping> getColumnMappings() {
		return mappingDecorator.getColumnMappings();
	}
}
