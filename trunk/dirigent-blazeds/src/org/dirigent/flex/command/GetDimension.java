package org.dirigent.flex.command;

import org.dirigent.flex.ICommand;
import org.dirigent.metafacade.IDimension;
import org.dirigent.metafacade.builder.MetafacadeBuilder;

public class GetDimension implements ICommand {

	public String uri;
	
	
	public GetDimension(){}
	@Override
	public Object execute() {
		//TODO: Remove this
		System.setProperty("dirigent.model.type","EA");
		
		IDimension d=(IDimension)MetafacadeBuilder.getMetafacadeBuilder().getMetafacade(uri);
		return d.getValueObject();
	}

}
