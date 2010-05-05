package org.dirigent.metafacade.builder.decorator;



import org.dirigent.metafacade.IColumn;
import org.dirigent.metafacade.IColumnMapping;
import org.dirigent.metafacade.ITable;
import org.dirigent.metafacade.builder.csv.TableDao;
import org.dirigent.metafacade.builder.vo.ColumnMappingVO;

public class ColumnMappingDecorator implements IColumnMapping {

	private final ColumnMappingVO columnMapping;

	public ColumnMappingDecorator(ColumnMappingVO columnMapping) {
		this.columnMapping = columnMapping;
	}

	@Override
	public IColumn getColumn() {
		// TODO: replace with ColumnDecorator
		return new IColumn() {
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


		};
	}

	@Override
	public String getExpression() {
		return columnMapping.expression;
	}


	//TODO this way of getting subqueries should change later 
	private String getCountColumnExpression(String expression) {
		int i = expression.indexOf("."); 
		String tableName = new TableDao().getTable(expression.substring(0, i)).name;
		return tableName + expression.substring(i); 
	}
	
	//TODO this way of getting subqueries should change later 
	private String getFromAndWhereExpression(String expression) {
		int i = expression.indexOf(".");
		int j = expression.indexOf(".", i + 1);
		int k = expression.indexOf("=");

		String tableName1 = new TableDao().getTable(expression.substring(0, i)).name;
		String tableName2 = new TableDao().getTable(expression.substring(k + 2, j)).name;
		
		String column1 = expression.substring(i+1, k-1); 
		String column2 = expression.substring(j+1); 
		
		return " FROM " + 
		tableName1 +", " + 
		tableName2 + 
		" WHERE " + tableName1 + "." + column1 + " = " + tableName2 + "." + column2; 
		 
	}

}
