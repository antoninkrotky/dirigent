package org.dirigent.metafacade.builder.decorator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.dirigent.config.DirigentConfig;
import org.dirigent.metafacade.IColumn;
import org.dirigent.metafacade.IDimension;
import org.dirigent.metafacade.IDimensionColumn;
import org.dirigent.metafacade.IDomain;
import org.dirigent.metafacade.IGeneratable;
import org.dirigent.metafacade.builder.vo.DimensionColumnVO;
import org.dirigent.metafacade.builder.vo.DimensionVO;
import org.dirigent.pattern.IPattern;
import org.dirigent.pattern.builder.PatternBuilder;

public class DimensionDecorator extends TableDecorator implements IDimension,IGeneratable {
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
	public IPattern getPattern() {		
		String pattern=DirigentConfig.getDirigentConfig().getProperty(DirigentConfig.DEFAULT_PATTERN_DIMENSION);
		return PatternBuilder.getPatternBuilder().getPattern(
				pattern + ".pattern.xml");
	}

}
