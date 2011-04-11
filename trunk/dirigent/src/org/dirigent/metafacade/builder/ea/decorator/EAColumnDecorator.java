package org.dirigent.metafacade.builder.ea.decorator;

import org.dirigent.metafacade.IColumn;
import org.dirigent.metafacade.builder.ea.vo.EAAttributeVO;

public class EAColumnDecorator extends EAAttributteDecorator implements IColumn{

	public EAColumnDecorator(EAAttributeVO ea) {
		super(ea);
	}

	private String dataType;
	private Boolean mandatory;
	
	@Override
	public String getDataType() {
		if (dataType==null) {
			dataType=getType();
			if (getEAAttribute().length!=null && getEAAttribute().length.intValue()>0) {
				String length=getEAAttribute().length.toString();
				String lengthType=this.getProperties().get("LengthType");
				if (lengthType!=null) {
					length=length+" "+lengthType;
				}				
				dataType=dataType+'('+length+')';
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

	@Override
	public Boolean isMandatory() {
		if (mandatory==null) {
			if ("1".equals(getEAAttribute().allowDuplicates)) {
				mandatory=true;
			}
			else {
				mandatory=false;
			}
		}
		return mandatory;
	}
	
}
