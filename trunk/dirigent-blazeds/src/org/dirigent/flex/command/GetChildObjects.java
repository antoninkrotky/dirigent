package org.dirigent.flex.command;

import org.dirigent.flex.ICommand;
import org.dirigent.metafacade.builder.MetafacadeBuilder;
import org.dirigent.metafacade.builder.vo.ObjectVO;

public class GetChildObjects  implements ICommand {
	public ObjectVO object;

	@Override
	public Object execute() {
		//TODO: Remove this
		System.setProperty("dirigent.model.type","EA");		
		return MetafacadeBuilder.getMetafacadeBuilder().getChildObjects(object);
	}
	

}
