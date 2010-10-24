package org.dirigent.metafacade.builder.ea.decorator;

import java.util.Collection;
import java.util.Collections;

import org.dirigent.metafacade.IDimension;
import org.dirigent.metafacade.IDimensionColumn;
import org.dirigent.metafacade.builder.ea.vo.EAElementVO;

public class EADimensionDecorator extends EATableDecorator implements IDimension {
	public EADimensionDecorator(EAElementVO ea) {
		super(ea);
	}


	@SuppressWarnings("unchecked")
	@Override
	public Collection<IDimensionColumn> getDimensionColumns() {
		return Collections.checkedCollection((Collection)getAttributes(), IDimensionColumn.class);
		
		
	}

	@Override
	public int getSCDType() {
		return Integer.valueOf(getProperties().get("slowlyChangingDimensionType"));
	}
	

}
