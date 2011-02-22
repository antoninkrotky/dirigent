package org.dirigent.metafacade.builder.ea.decorator;

import org.dirigent.metafacade.IDimensionColumn;
import org.dirigent.metafacade.builder.ea.vo.EAAttributeVO;

public class EADimensionColumnDecorator extends EABIColumnDecorator implements IDimensionColumn {

	/**
	 * 
	 */
	public EADimensionColumnDecorator(EAAttributeVO ea) {
		super(ea);
	}

	@Override
	public String getHistoryType() {
		return getProperties().get("scdColumnType");
	}
}
