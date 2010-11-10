package org.dirigent.metafacade;

import java.util.Collection;


public interface IDimension extends ITable {
	public int getSCDType();
	public Collection<IDimensionColumn> getDimensionColumns();
	
	/**
	 * Returns list of columns that have specified SCDType attribute
	 * @param scdType desired scdType of returned columns
	 * @return list of columns with specific SCD type
	 */
	public Collection<IDimensionColumn> getColumnListBySCDType(String scdType);
}
