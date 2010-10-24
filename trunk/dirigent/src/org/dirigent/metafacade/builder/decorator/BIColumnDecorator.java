package org.dirigent.metafacade.builder.decorator;

import org.dirigent.metafacade.builder.vo.BIColumnVO;

public class BIColumnDecorator extends ColumnDecorator {

	private BIColumnVO column;
	
	public BIColumnDecorator(BIColumnVO v) {
		super(v);
		column=v;
	}

	@Override
	public String getDataType() {
		return column.domain.dataType;
	}

}
