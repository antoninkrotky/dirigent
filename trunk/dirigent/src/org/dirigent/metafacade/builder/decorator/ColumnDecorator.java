package org.dirigent.metafacade.builder.decorator;

import org.dirigent.metafacade.IColumn;
import org.dirigent.metafacade.builder.vo.ColumnVO;

public class ColumnDecorator extends AttributeDecorator implements IColumn {

	
	public ColumnDecorator(ColumnVO v) {
		super(v);
	}

	@Override
	public String getDataType() {
		return super.getType();
	}
	
	@Override
	public Boolean isMandatory() {
		return null;
	}
	
}
