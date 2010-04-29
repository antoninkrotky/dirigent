package org.dirigent.flex.command;

import org.dirigent.flex.ICommand;
import org.dirigent.metafacade.builder.MetafacadeBuilder;

public class GetGlobals implements ICommand{

	
	@Override
	public Object execute() {
		//TODO: Remove this
		System.setProperty("dirigent.model.type","EA");
		
		GlobalsVO v=new GlobalsVO();
		v.domains=MetafacadeBuilder.getMetafacadeBuilder().getDomains();
		return v;
	}

}
