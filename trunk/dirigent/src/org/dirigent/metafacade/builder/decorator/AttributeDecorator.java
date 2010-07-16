package org.dirigent.metafacade.builder.decorator;

import java.util.Map;

import org.dirigent.metafacade.IAttribute;
import org.dirigent.metafacade.builder.vo.AttributeVO;

public class AttributeDecorator implements IAttribute {

	private AttributeVO attribute;
	
	public AttributeDecorator(AttributeVO v) {
		this.attribute=v;
		
	}
	@Override
	public String getDescription() {
		return attribute.description;
	}

	@Override
	public String getName() {
		return attribute.name;
	}

	@Override
	public Map<String, String> getProperties() {
		return attribute.properties;
	}
	@Override
	public String getType() {
		return attribute.type;
	}
	@Override
	public String getStereotype() {
		return attribute.stereotype;
	}

}
