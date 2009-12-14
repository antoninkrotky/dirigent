package org.dirigent.metafacade;

public interface Schema {
	public String getName();
	public String getJdbcDriver();
	public String getJdbcUrl();
	public String getUsername();
	public String getPassword();
	public String getSchema();
}
