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
	
	/**
	 * Returns true if there is a column (or more columnt) with specified
	 * scdType tagged value. If there is not such a column, it returns false
	 * @param scdType scdType taggedValue (naturalKey, addRowOnChange, ...)
	 * @return true if there is a column (or more columnt) with specified
	 * scdType otherwise returns false
	 */
	public boolean isColumnOfSCDType(String scdType);
}
