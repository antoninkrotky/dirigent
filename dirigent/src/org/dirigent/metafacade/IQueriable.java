package org.dirigent.metafacade;

public interface IQueriable  extends ISchemaProvider,IElement {
	public String getSQLQuery();
	public String getSQLQuery(int offset);
}
