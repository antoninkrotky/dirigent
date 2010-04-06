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

			@Override
			public ITable getTable() {
				// TODO Auto-generated method stub
				return null;
			}
		};
	}

	@Override
	public String getExpression() {
		if (columnMapping.md5 == true) {
			return "md5(" + columnMapping.expression + ")";
		}
		if (columnMapping.sh1 == true) {
			return "sh1(" + columnMapping.expression + ")";
		}
		if (!columnMapping.countAlias.equals("")) {
			return columnMapping.countAlias + "." + columnMapping.columnName;
		}
		return columnMapping.expression;
	}

	@Override
	public String getCountSubqueryExpression() {
		return "(SELECT COUNT(" + getCountColumnExpression(columnMapping.countColumn) + ") AS "
				+ columnMapping.columnName 
				+ getFromAndWhereExpression(columnMapping.expression) +  
				" GROUP BY ("
				+ getCountColumnExpression(columnMapping.countColumn) + ") " + ") "
				+ columnMapping.countAlias;
	}

	@Override
	public boolean hasCountWithSubquery() {
		if (!columnMapping.countAlias.equals(""))
			return true;
		return false;
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
