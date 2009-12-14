package org.dirigent.metafacade;

import java.util.Collection;

public interface Table extends Element {
	public Schema getSchema();
	public Collection<Column> getColumns();
}
