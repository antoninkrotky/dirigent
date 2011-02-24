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
			if (getEAAttribute().length!=null && getEAAttribute().length.intValue()>0) {
				dataType=dataType+'('+getEAAttribute().length+')';
			}  else if (getEAAttribute().precision!=null && getEAAttribute().precision.intValue()>0) {
				if (getEAAttribute().scale!=null && getEAAttribute().scale.intValue()>0) {
					dataType=dataType+'('+getEAAttribute().precision+','+getEAAttribute().scale+')';
				} else {
					dataType=dataType+'('+getEAAttribute().precision+')';
				}
			}
		}
		return dataType;
	}

}
