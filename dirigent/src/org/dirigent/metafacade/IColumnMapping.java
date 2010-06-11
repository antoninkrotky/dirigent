package org.dirigent.metafacade;
/**
 * Metafacade representing column mapping.
 * */
public interface IColumnMapping extends IAttribute {
	/**
	 * Expression used to populate column.
	 * */
	public String getExpression();
	
	/**
	 * Reference to target column metafacade.
	 * */
	public IColumn getColumn();
	
}
