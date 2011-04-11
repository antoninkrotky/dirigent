package org.dirigent.metafacade.builder.decorator;



import java.util.Map;
import java.util.logging.Logger;

import org.dirigent.metafacade.IColumn;
import org.dirigent.metafacade.IColumnMapping;
import org.dirigent.metafacade.IElement;
import org.dirigent.metafacade.IMapping;
import org.dirigent.metafacade.builder.vo.ColumnMappingVO;

public class ColumnMappingDecorator extends AttributeDecorator implements IColumnMapping {
	private Logger l=Logger.getLogger(AttributeDecorator.class.getName());
	private final ColumnMappingVO columnMapping;
	private IMapping mapping;
	public ColumnMappingDecorator(ColumnMappingVO columnMapping) {
		super(columnMapping);
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
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getInitialValue() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public IElement getClassifier() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Boolean isMandatory() {
				// TODO Auto-generated method stub
				return null;
			}
			
		};
	}

	@Override
	public String getExpression() {
		if (mapping==null) {
			throw new IllegalStateException("Mapping property must be set on ColumnMappingDescorator before call to getExpression() method.");
		}		
		if (columnMapping.expression==null) {
			l.warning("Missing expression for column mapping "+getName()+".");
			return null;
		}
		return getMapping().injectSubqueries(columnMapping.expression);
	}

	public void setMapping(IMapping mapping) {
		this.mapping = mapping;
	}

	public IMapping getMapping() {
		return mapping;
	}

	






}
