package org.dirigent.flex.command;

import org.dirigent.executor.TemplateHelper;
import org.dirigent.flex.ICommand;
import org.dirigent.metafacade.IQueriable;
import org.dirigent.metafacade.builder.MetafacadeBuilder;

public class GetSQLQuery implements ICommand {

	public String uri;

	@Override
	public Object execute() {	
		MetafacadeBuilder.getMetafacadeBuilder().clearCache();
		IQueriable m=(IQueriable)MetafacadeBuilder.getMetafacadeBuilder().getMetafacade(uri);
		GetSQLQueryResult result=new GetSQLQueryResult();
		result.query=TemplateHelper.generateValue(m.getSQLQuery(),m,1);
		result.schemaUri=m.getSchema().getUri();
		return result;
	}

}
