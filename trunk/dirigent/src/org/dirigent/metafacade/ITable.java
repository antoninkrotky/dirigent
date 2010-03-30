package org.dirigent.metafacade;

import java.util.Collection;

public interface ITable extends IElement,ISchemaProvider {
	public ISchema getSchema();
	public Collection<IColumn> getColumns();
	public String getFullName();
}
