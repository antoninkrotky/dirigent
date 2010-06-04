package org.dirigent.metafacade;

import java.util.Collection;

public interface ITable extends IElement,ISchemaProvider, IQueriable,IGeneratable {
	public ISchema getSchema();
	public Collection<IColumn> getColumns();
	public String getFullName();
	public String getCodeName();
}
