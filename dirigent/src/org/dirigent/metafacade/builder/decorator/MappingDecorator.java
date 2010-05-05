package org.dirigent.metafacade.builder.decorator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dirigent.metafacade.IColumnMapping;
import org.dirigent.metafacade.IGeneratable;
import org.dirigent.metafacade.IMapping;
import org.dirigent.metafacade.ISchema;
import org.dirigent.metafacade.ITable;
import org.dirigent.metafacade.builder.MetafacadeBuilder;
import org.dirigent.metafacade.builder.vo.ColumnMappingVO;
import org.dirigent.metafacade.builder.vo.MappingSourceTableVO;
import org.dirigent.metafacade.builder.vo.MappingVO;
import org.dirigent.metafacade.builder.vo.VO;
import org.dirigent.pattern.IPattern;
import org.dirigent.pattern.builder.PatternBuilder;

public class MappingDecorator implements IMapping, IGeneratable {

	private MappingVO mapping;
	private Map<String, ITable> sourceTables;
	private Collection<IColumnMapping> columnMappings;

	public MappingDecorator(MappingVO mapping) {
		this.mapping = mapping;
		initSourceTables();
		initColumnMappings();

	}

	private void initColumnMappings() {
		columnMappings=new ArrayList<IColumnMapping>();
		Iterator<ColumnMappingVO> i=mapping.columnMappings.iterator();
		while (i.hasNext()){
		columnMappings.add(new ColumnMappingDecorator(i.next()));
		}

	}

	private void initSourceTables() {
		sourceTables = new HashMap<String, ITable>();
		Iterator<MappingSourceTableVO> i = mapping.mappingSourceTables
				.iterator();
		while (i.hasNext()) {
			MappingSourceTableVO v = i.next();
			sourceTables.put(v.alias, (ITable) MetafacadeBuilder
					.getMetafacadeBuilder().getMetafacade(v.tableUri));
		}
	}

	@Override
	public ISchema getSchema() {
		return (ISchema) MetafacadeBuilder.getMetafacadeBuilder().getMetafacade(
				mapping.schemaUri);
	}

	@Override
	public Map<String, ITable> getSourceTables() {
		return sourceTables;
	}

	@Override
	public ITable getTargetTable() {
		return (ITable) MetafacadeBuilder.getMetafacadeBuilder().getMetafacade(
				mapping.targetTableUri);
	}

	@Override
	public String getName() {
		return mapping.name;
	}

	@Override
	public String getUri() {
		return mapping.uri;
	}

	@Override
	public Collection<IColumnMapping> getColumnMappings() {
		return columnMappings;
	}

	@Override
	public String getSQLQuery() {
		StringBuffer sb = new StringBuffer("SELECT \n");
		sb.append(getColumns());
		sb.append(getFromClause());
		sb.append(getWhereClause());
		sb.append(getGroupByClause());
		return sb.toString();
	}

	public String getGroupByClause() {
		StringBuffer sb = new StringBuffer();
		if (mapping.groupByClause != null
				&& !mapping.groupByClause.trim().equals("")) {
			sb.append("\nGROUP BY \n");
			sb.append("\t(" + mapping.groupByClause.trim() + ")");
		}

		return sb.toString();
	}

	public String getWhereClause() {
		StringBuffer sb = new StringBuffer("WHERE\n\t(1=1)\n");
		if (mapping.joinCondition != null
				&& !mapping.joinCondition.trim().equals("")) {
			sb.append("\tAND ");
			sb.append(mapping.joinCondition.trim());
		}
		if (mapping.filterCondition != null
				&& !mapping.filterCondition.trim().equals("")) {
			sb.append("\tAND ");
			sb.append(mapping.filterCondition.trim());
		}
		return sb.toString();
	}

	//TODO generate sub-queries 
	public String getFromClause() {
		StringBuffer sb = new StringBuffer("FROM\n");
		Iterator<String> i = getSourceTables().keySet().iterator();
		
		while (i.hasNext()) {
			String alias = i.next();
			ITable table = getSourceTables().get(alias);
			sb.append('\t');
			sb.append(table.getSchema().getSchema());
			sb.append('.');
			sb.append(table.getName());
			sb.append(' ');
			sb.append(alias);
			if (i.hasNext()) {
				sb.append(',');
			}
			sb.append('\n');
		}
		return sb.toString();
	}

	public String getColumns() {
		StringBuffer sb = new StringBuffer();
		Iterator<IColumnMapping> i = getColumnMappings().iterator();
		while (i.hasNext()) {
			IColumnMapping m = i.next();
			sb.append('\t');
			sb.append(m.getExpression());
			sb.append(" AS \"");
			sb.append(m.getColumn().getName());
			sb.append("\"");
			if (i.hasNext()) {
				sb.append(',');
			}
			sb.append('\n');
		}
		return sb.toString();
	}

	@Override
	public IPattern getPattern() {
		return PatternBuilder.getPatternBuilder().getPattern(mapping.pattern+".pattern.xml");
	}

	@Override
	public VO getValueObject() {
		return mapping;
	}

}
