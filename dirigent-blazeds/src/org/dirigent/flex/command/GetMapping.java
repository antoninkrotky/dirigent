package org.dirigent.flex.command;

import org.dirigent.flex.ICommand;
import org.dirigent.metafacade.IMapping;
import org.dirigent.metafacade.builder.MetafacadeBuilder;

public class GetMapping implements ICommand {

	public String uri;
	
	
	public GetMapping(){}
	@Override
	public Object execute() {
		//TODO: Remove this
		System.setProperty("dirigent.model.type","EA");
		
		IMapping d=(IMapping)MetafacadeBuilder.getMetafacadeBuilder().getMetafacade(uri);
		return d.getValueObject();
	}

}
