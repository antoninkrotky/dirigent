package org.dirigent.flex.command;

import org.dirigent.flex.ICommand;
import org.dirigent.metafacade.builder.ea.EADimensionDao;
import org.dirigent.metafacade.builder.vo.DimensionVO;

public class SaveDimension implements ICommand{

	public DimensionVO dimension;
	
	@Override
	public Object execute() {
		new EADimensionDao().merge(dimension);
		return null;
	}

}
