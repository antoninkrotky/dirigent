package org.dirigent.metafacade.builder.decorator;

import org.dirigent.metafacade.IDimensionColumn;
import org.dirigent.metafacade.builder.vo.DimensionColumnVO;

public class DimensionColumnDecorator implements IDimensionColumn{

	private DimensionColumnVO column;
	public DimensionColumnDecorator(DimensionColumnVO v) {
		this.column=v;
	}
	
	

	
	@Override
	public String getHistoryType() {
		return column.scdColumnType;
	}

	@Override
	public String getDataType() {
		return column.domain.dataType;
	}

	@Override
	public String getDescription() {
		return column.description;
	}

	@Override
	public String getName() {
		return column.name;
	}

}
