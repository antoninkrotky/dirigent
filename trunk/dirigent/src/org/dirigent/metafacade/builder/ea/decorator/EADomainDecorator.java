package org.dirigent.metafacade.builder.ea.decorator;

import org.dirigent.metafacade.IDomain;
import org.dirigent.metafacade.builder.ea.vo.EAElementVO;

public class EADomainDecorator extends EAElementDecorator implements IDomain {
	
	public EADomainDecorator(EAElementVO element) {
		super(element);
	}
	
	@Override
	public String getDataType() {
		return getProperties().get("dataType");
	}

	@Override
	public String getXNAValue() {
		return getProperties().get("xnaValue");	}

	@Override
	public String getXUNValue() {
		return getProperties().get("xunValue");	}

}
