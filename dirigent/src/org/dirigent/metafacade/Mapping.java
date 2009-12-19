package org.dirigent.metafacade;

import java.util.Collection;
import java.util.Map;

public interface Mapping extends Element {
	public Schema getSchema();
	public Table getTargetTable();
	public Map<String, Table>getSourceTables();
	public Collection<ColumnMapping> getColumnMappings();
	public String getSQLQuery();
}
