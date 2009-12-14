package org.dirigent.metafacade;
/**
 * Metafacade representing column of table.
 * */
public interface Column {
	/**
	 * Name of column.
	 * */
	public String getName();
	/**
	 * Data type of column. The data type is string in database specific format (f.e.: VARCHAR(255)).
	 * */
	public String getDataType();
	/**
	 * Reference to table metafacade.
	 * */
	public Table getTable();
	/**
	 * Description of table column.
	 * */
	public String getDescription();
	
}
