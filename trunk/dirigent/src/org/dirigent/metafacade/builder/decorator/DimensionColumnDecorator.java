package org.dirigent.metafacade.builder.decorator;

import org.dirigent.metafacade.IDimensionColumn;
import org.dirigent.metafacade.IDomain;
import org.dirigent.metafacade.builder.vo.DimensionColumnVO;

public class DimensionColumnDecorator extends BIColumnDecorator implements IDimensionColumn {

	private DimensionColumnVO column;
	public DimensionColumnDecorator(DimensionColumnVO v) {
		super(v);
		this.column=v;
	}
	
	

	
	@Override
	public String getHistoryType() {
		return column.scdColumnType;
	}




	@Override
	public IDomain getDomain() {
		throw new RuntimeException("getDomain() not implemented");
	}




}







