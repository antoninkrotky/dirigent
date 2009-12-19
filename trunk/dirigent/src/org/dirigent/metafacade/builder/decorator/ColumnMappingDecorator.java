package org.dirigent.metafacade.builder.decorator;

import org.dirigent.metafacade.Column;
import org.dirigent.metafacade.ColumnMapping;
import org.dirigent.metafacade.Table;
import org.dirigent.metafacade.builder.vo.ColumnMappingVO;

public class ColumnMappingDecorator implements ColumnMapping {

	private final ColumnMappingVO columnMapping;

	public ColumnMappingDecorator(ColumnMappingVO columnMapping) {
		this.columnMapping=columnMapping;
	}
	
	@Override
	public Column getColumn() {
		//TODO: replace with ColumnDecorator 
		return new Column(){
			@Override
			public String getDataType() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getDescription() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getName() {
				return ColumnMappingDecorator.this.columnMapping.columnName;
			}

			@Override
			public Table getTable() {
				// TODO Auto-generated method stub
				return null;
			}
			
		};
	}

	@Override
	public String getExpression() {
		return columnMapping.expression;
	}

}
