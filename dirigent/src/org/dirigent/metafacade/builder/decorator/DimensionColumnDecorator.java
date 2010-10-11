package org.dirigent.metafacade.builder.decorator;

import java.util.Map;

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




	@Override
	public Map<String, String> getProperties() {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public String getStereotype() {
		return this.column.stereotype;
	}




	@Override
	public String getInitialValue() {
		
		return this.column.initialValue;
	}

}
