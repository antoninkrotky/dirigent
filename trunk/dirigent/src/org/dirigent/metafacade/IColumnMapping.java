package org.dirigent.metafacade;
/**
 * Metafacade representing column mapping.
 * */
public interface IColumnMapping {
	/**
	 * Expression used to populate column.
	 * */
	public String getExpression();
	
	/**
	 * Reference to target column metafacade.
	 * */
	public IColumn getColumn();
	
	/**
	 * String may be null if no sub-query is needed
	 * @return String 
	 */
	public String getCountSubqueryExpression();
	
	/**
	 * true if count function needs sub-query in form clause
	 * @return boolean 
	 */
	public boolean hasCountWithSubquery(); 
}
