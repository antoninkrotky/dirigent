package org.dirigent.metafacade.builder.decorator;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.dirigent.config.DirigentConfig;
import org.dirigent.metafacade.IColumnMapping;
import org.dirigent.metafacade.IElement;
import org.dirigent.metafacade.IGeneratable;
import org.dirigent.metafacade.IMapping;
import org.dirigent.metafacade.IQueriable;
import org.dirigent.metafacade.ISchema;
import org.dirigent.metafacade.ITable;
import org.dirigent.metafacade.builder.MetafacadeBuilder;
import org.dirigent.metafacade.builder.vo.ColumnMappingVO;
import org.dirigent.metafacade.builder.vo.MappingSourceVO;
import org.dirigent.metafacade.builder.vo.MappingVO;
import org.dirigent.metafacade.builder.vo.VO;
import org.dirigent.pattern.IPattern;
import org.dirigent.pattern.builder.PatternBuilder;

public class MappingDecorator implements IMapping, IGeneratable {

	private MappingVO mapping;
	private Map<MappingSourceVO, IElement> sources;
	private Collection<IColumnMapping> columnMappings;

	/**
	 * VelocityContext of expression subqueries which are not included in FROM
	 * clause. This subqueries can be typically injected into SELECT or WHERE
	 * clasue.
	 * */
	private VelocityContext subqueriesContext;

	public MappingDecorator(MappingVO mapping) {
		this.mapping = mapping;
		initSources();
		initColumnMappings();

	}

	/**
	 * Init mapping sources map. The elements in the map must be sorted
	 * according to MappingSourceComparator. This is important for correct
	 * syntax of FROM clause containing outer joined tables / subqueries.
	 * */
	private void initSources() {
		sources = new TreeMap<MappingSourceVO, IElement>(
				new MappingSourceComparator());
		Map<String, String> expressionSubqueries = new HashMap<String, String>();
		Iterator<MappingSourceVO> i = mapping.sources.iterator();
		while (i.hasNext()) {
			MappingSourceVO v = i.next();
			IQueriable q = (IQueriable) MetafacadeBuilder
					.getMetafacadeBuilder().getMetafacade(v.sourceUri);
			sources.put(v, (IElement) q);
			if ("expression".equals(v.joinType)) {
				expressionSubqueries.put(v.alias, '('+q.getSQLQuery()+") "+v.alias);
			}
		}
		subqueriesContext = new VelocityContext(expressionSubqueries);

	}

	private void initColumnMappings() {
		columnMappings = new ArrayList<IColumnMapping>();
		Iterator<ColumnMappingVO> i = mapping.columnMappings.iterator();
		while (i.hasNext()) {
			columnMappings.add(new ColumnMappingDecorator(this,i.next()));
		}

	}

	@Override
	public ISchema getSchema() {
		return (ISchema) MetafacadeBuilder.getMetafacadeBuilder()
				.getMetafacade(mapping.schemaUri);
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
		StringBuffer sb = new StringBuffer();
		sb.append("/*Mapping: ");
		sb.append(getName());
		sb.append(". Query generated by DIRIGENT.*/\n");
		sb.append("SELECT \n");
		sb.append(getColumns());
		sb.append(getFromClause());
		sb.append(getWhereClause());
		sb.append(getGroupByClause());
		sb.append("\n/*End of mapping: ");
		sb.append(getName());
		sb.append(".*/\n");

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
		if (mapping.filterCondition != null
				&& !mapping.filterCondition.trim().equals("")) {
			sb.append("\tAND ");
			sb.append(injectSubqueries(mapping.filterCondition.trim()));
		}
		return sb.toString();
	}

	public String getFromClause() {
		StringBuffer sb = new StringBuffer("FROM\n");

		Iterator<MappingSourceVO> i = getSources().keySet().iterator();
		boolean first = true;
		while (i.hasNext()) {
			MappingSourceVO s = i.next();
			//ignore expression sources
			if ("expression".equals(s.joinType)) {
				continue;
			}
			IElement element = sources.get(s);

			sb.append('\t');
			if (first) {
				// DO NOTHING
			} else if ("inner".equals(s.joinType)
					&& (s.joinCondition == null || "".equals(s.joinCondition
							.trim()))) {
				sb.append(',');
			} else if ("inner".equals(s.joinType)) {
				sb.append(" JOIN ");
			} else if ("leftOuter".equals(s.joinType)) {
				sb.append(" LEFT JOIN ");
			} else if ("fullOuter".equals(s.joinType)) {
				sb.append(" FULL JOIN ");
			}
			if (element instanceof ITable) {
				ITable table = (ITable) element;

				sb.append(table.getSchema().getSchema());
				sb.append('.');
				sb.append(table.getName());
				sb.append(' ');
				sb.append(s.alias);
			}

			if (element instanceof IMapping) {

				IMapping mapping = (IMapping) element;
				sb.append("(\n");
				sb.append(mapping.getSQLQuery());
				sb.append(") ");
				sb.append(s.alias);

			}

			if (first) {
				first = false;
			} else if ("inner".equals(s.joinType)
					&& (s.joinCondition == null || "".equals(s.joinCondition
							.trim()))) {
				// DO NOTHING
			} else {
				sb.append(" ON ");
				sb.append(s.joinCondition);
			}
			sb.append('\n');
		}

		return sb.toString();
	}

	public String getTargetColumnList() {
		StringBuffer sb = new StringBuffer();
		Iterator<IColumnMapping> i = getColumnMappings().iterator();
		while (i.hasNext()) {
			IColumnMapping m = i.next();
			sb.append('\t');
			sb.append("\"");
			sb.append(m.getColumn().getName());
			sb.append("\"");
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
		String pattern = mapping.pattern;
		if (pattern == null) {
			pattern = DirigentConfig.getDirigentConfig().getProperty(
					DirigentConfig.DEFAULT_PATTERN_MAPPING);
		}
		return PatternBuilder.getPatternBuilder().getPattern(
				pattern + ".pattern.xml");

	}

	@Override
	public VO getValueObject() {
		return mapping;
	}

	@Override
	public Map<MappingSourceVO, IElement> getSources() {
		return sources;
	}

	public String injectSubqueries(String template) {
		StringWriter sb = new StringWriter();
		try {
			if (!Velocity.evaluate(subqueriesContext, sb,
					"MappingDecorator.injectSubqueries", new StringReader(
							template))) {
				throw new RuntimeException(
						"Injection of subqueries failed. See Velocity log.");
			}
		} catch (IOException e) {
			throw new RuntimeException("Injection of subqueries failed.", e);
		}
		return sb.toString();

	}

}
