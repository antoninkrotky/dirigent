package org.dirigent.metafacade;

public interface ISchema extends IElement {	
	public String getJdbcDriver();
	public String getJdbcUrl();
	public String getUsername();
	public String getPassword();
	public String getSchema();
}
