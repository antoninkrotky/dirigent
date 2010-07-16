package org.dirigent.metafacade.builder.ea.decorator;

import org.dirigent.metafacade.IColumn;
import org.dirigent.metafacade.builder.ea.vo.EAAttributeVO;

public class EAColumnDecorator extends EAAttributteDecorator implements IColumn{

	public EAColumnDecorator(EAAttributeVO ea) {
		super(ea);
	}

	private String dataType;
	
	@Override
	public String getDataType() {
		if (dataType==null) {
			dataType=getType();
			if (getEAAttribute().length!=null) {
				dataType=dataType+'('+getEAAttribute().length+')';
			} else if (getEAAttribute().scale!=null) {
				dataType=dataType+'('+getEAAttribute().scale+','+getEAAttribute().precision+')';
			}
		}
		return dataType;
	}

}
