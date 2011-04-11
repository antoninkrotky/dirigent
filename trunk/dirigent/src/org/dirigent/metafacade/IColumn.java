package org.dirigent.metafacade;
/**
 * Metafacade representing column of table.
 * */
public interface IColumn  extends IAttribute{

	/**
	 * Data type of column. The data type is string in database specific format (f.e.: VARCHAR(255)).
	 * */
	public String getDataType();

	public Boolean isMandatory();
	
}
