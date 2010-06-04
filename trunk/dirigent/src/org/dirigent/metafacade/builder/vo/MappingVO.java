package org.dirigent.metafacade.builder.vo;

import java.util.Collection;

public class MappingVO extends ElementVO{
	public String pattern;
	public String schemaUri;
	public String targetTableUri;
	public String filterCondition;
	public String havingClause;
	public String groupByClause;
	public String businessRule;
	public Collection<ColumnMappingVO> columnMappings;
	public Collection<MappingSourceVO> sources;
}
