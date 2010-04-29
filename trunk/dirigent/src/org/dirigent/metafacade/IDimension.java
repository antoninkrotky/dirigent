package org.dirigent.metafacade;

import java.util.Collection;


public interface IDimension extends ITable {
	public int getSCDType();
	public Collection<IDimensionColumn> getDimensionColumns();
}
