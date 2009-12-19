package org.dirigent.metafacade.builder.vo;

import java.util.Collection;

public class MappingVO extends VO{
	public String name;
	public String schemaUri;
	public String targetTableUri;
	public String joinCondition;
	public String filterCondition;
	public String groupByClause;
	public Collection<ColumnMappingVO> columnMappings;
	public Collection<MappingSourceTableVO> mappingSourceTables;
}
