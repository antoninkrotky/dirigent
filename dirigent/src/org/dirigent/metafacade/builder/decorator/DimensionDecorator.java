package org.dirigent.metafacade.builder.decorator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.dirigent.metafacade.IColumn;
import org.dirigent.metafacade.IDimension;
import org.dirigent.metafacade.IDimensionColumn;
import org.dirigent.metafacade.IGeneratable;
import org.dirigent.metafacade.builder.vo.DimensionColumnVO;
import org.dirigent.metafacade.builder.vo.DimensionVO;

public abstract class DimensionDecorator extends TableDecorator implements IDimension,IGeneratable {
	public static final String SCD_COLUMN_TYPE_PROPERTY = "scdColumnType";
	private DimensionVO dimension;
	private Collection<IColumn> columns=new ArrayList<IColumn>();
	private Collection<IDimensionColumn> dimensionColumns=new ArrayList<IDimensionColumn>();
	
	public DimensionDecorator(DimensionVO dimension) {
		super(dimension);
		this.dimension=dimension;
		Iterator<DimensionColumnVO> i=dimension.columns.iterator();
		while (i.hasNext()) {
			DimensionColumnDecorator v=new DimensionColumnDecorator(i.next());
			columns.add(v);
			dimensionColumns.add(v);
		}
		
	}

	@Override
	public int getSCDType() {
		return dimension.scdType;
	}
	
	@Override
	public Collection<IColumn> getColumns() {
		return columns;
	}

	@Override
	public Collection<IDimensionColumn> getDimensionColumns() {
		return dimensionColumns;
	}

	@Override
	public Collection<IDimensionColumn> getColumnListBySCDType(String scdType) {
		Collection<IDimensionColumn> res = new ArrayList<IDimensionColumn>(10);
		for (IDimensionColumn col : this.dimensionColumns) {
			if (col.getProperties().get(SCD_COLUMN_TYPE_PROPERTY).equals(scdType)) {
				res.add(col);
			}
		}
		return res;
	}

	@Override
	public boolean isColumnOfSCDType(String scdType) {
		return (! this.getColumnListBySCDType(scdType).isEmpty());
	}

}
