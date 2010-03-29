package org.dirigent.metafacade.builder.decorator;

import org.dirigent.metafacade.IColumn;
import org.dirigent.metafacade.IColumnMapping;
import org.dirigent.metafacade.ITable;
import org.dirigent.metafacade.builder.vo.ColumnMappingVO;

public class ColumnMappingDecorator implements IColumnMapping {

	private final ColumnMappingVO columnMapping;

	public ColumnMappingDecorator(ColumnMappingVO columnMapping) {
		this.columnMapping=columnMapping;
	}
	
	@Override
	public IColumn getColumn() {
		//TODO: replace with ColumnDecorator 
		return new IColumn(){
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
			public ITable getTable() {
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
