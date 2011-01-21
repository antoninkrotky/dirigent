package org.dirigent.metafacade.builder.ea.decorator;

import org.dirigent.metafacade.IDimensionColumn;
import org.dirigent.metafacade.IDomain;
import org.dirigent.metafacade.builder.ea.vo.EAAttributeVO;

public class EADimensionColumnDecorator extends EAColumnDecorator implements IDimensionColumn {
	private IDomain domain;
	public EADimensionColumnDecorator(EAAttributeVO ea) {
		super(ea);
		domain=(IDomain)getClassifier();
	}

	@Override
	public String getHistoryType() {
		return getProperties().get("scdColumnType");
	}
	
	@Override
	public String getDataType() {
		return domain.getDataType();
	}

	@Override
	public IDomain getDomain() {
		return domain;
	}

}
