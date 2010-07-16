package org.dirigent.flex.command;

import org.dirigent.flex.ICommand;
import org.dirigent.metafacade.builder.vo.DimensionVO;

public class SaveDimension implements ICommand{

	public DimensionVO dimension;
	
	@Override
	public Object execute() {
		throw new RuntimeException("Save not yet supported.");
	}

}
