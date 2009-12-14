package org.dirigent.metafacade;
/**
 * Metafacade representing column mapping.
 * */
public interface ColumnMapping {
	/**
	 * Expression used to populate column.
	 * */
	public String getExpression();
	
	/**
	 * Reference to target column metafacade.
	 * */
	public Column getColumn();
}
