package org.dirigent.metafacade;

public interface IQueriable  extends ISchemaProvider{
	public String getSQLQuery();
	public String getSQLQuery(int offset);
}
