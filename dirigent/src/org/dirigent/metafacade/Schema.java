package org.dirigent.metafacade;

public interface Schema extends Element {	
	public String getJdbcDriver();
	public String getJdbcUrl();
	public String getUsername();
	public String getPassword();
	public String getSchema();
}
