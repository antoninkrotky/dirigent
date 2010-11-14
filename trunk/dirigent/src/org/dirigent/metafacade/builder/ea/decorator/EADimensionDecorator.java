package org.dirigent.metafacade.builder.ea.decorator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.dirigent.metafacade.IDimension;
import org.dirigent.metafacade.IDimensionColumn;
import org.dirigent.metafacade.builder.ea.vo.EAElementVO;

public class EADimensionDecorator extends EATableDecorator implements IDimension {
	public static final String SCD_COLUMN_TYPE_PROPERTY = "scdColumnType";
	
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
	
	@Override
	public Collection<IDimensionColumn> getColumnListBySCDType(String scdType) {
		Collection<IDimensionColumn> res = new ArrayList<IDimensionColumn>(10);
		for (IDimensionColumn col : this.getDimensionColumns()) {
			String property = col.getProperties().get(SCD_COLUMN_TYPE_PROPERTY);
			if (property != null && property.equals(scdType)) {
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
