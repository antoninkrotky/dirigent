package org.dirigent.metafacade;

import java.util.Collection;
import java.util.Map;

public interface IMapping extends IElement,ISchemaProvider {
	public ISchema getSchema();
	public ITable getTargetTable();
	public Map<String, ITable>getSourceTables();
	public Collection<IColumnMapping> getColumnMappings();
	public String getSQLQuery();
}
