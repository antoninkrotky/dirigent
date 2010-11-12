package org.dirigent.metafacade;

import java.util.Collection;

/*
 * Interface for FactTable implementation
 */

public interface IFactTable {
	
	public Collection<IColumn> getColumns();
	public Collection<IDimension> getRelatedDimensions();

}
