package org.dirigent.metafacade.builder.ea.decorator;

import java.util.Collection;
import java.util.Collections;

import org.dirigent.metafacade.IColumn;
import org.dirigent.metafacade.IDimension;
import org.dirigent.metafacade.IFactTable;
import org.dirigent.metafacade.builder.ea.vo.EAElementVO;

public class EAFactTableDecorator extends EATableDecorator implements IFactTable {

	public EAFactTableDecorator(EAElementVO ea) {
		super(ea);
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public Collection<IColumn> getColumns() {
		Collection<IColumn> k =Collections.checkedCollection((Collection)getAttributes(), IColumn.class);
		System.out.println(k);
		return Collections.checkedCollection((Collection)getAttributes(), IColumn.class);
		
	}
	@Override
	public Collection<IDimension> getRelatedDimensions() {
		
		return null;
	}

}
