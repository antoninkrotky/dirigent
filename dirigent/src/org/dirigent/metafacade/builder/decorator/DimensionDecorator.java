package org.dirigent.metafacade.builder.decorator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.dirigent.metafacade.IColumn;
import org.dirigent.metafacade.IDimension;
import org.dirigent.metafacade.IDimensionColumn;
import org.dirigent.metafacade.builder.vo.DimensionColumnVO;
import org.dirigent.metafacade.builder.vo.DimensionVO;

public class DimensionDecorator extends TableDecorator implements IDimension {
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

}
