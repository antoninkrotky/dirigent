package org.dirigent.metafacade.builder.decorator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.dirigent.metafacade.IAttribute;
import org.dirigent.metafacade.IElement;
import org.dirigent.metafacade.builder.MetafacadeBuilder;
import org.dirigent.metafacade.builder.vo.ElementVO;
import org.dirigent.metafacade.builder.vo.VO;

public class ElementDecorator implements IElement {

	private ElementVO element;
	public ElementDecorator(ElementVO v) {
		this.element=v;
	}
	
	@Override
	public String getName() {
		return element.name;
	}

	@Override
	public Map<String, String> getProperties() {
		return element.properties;
	}

	@Override
	public String getUri() {
		return element.uri;
	}

	@Override
	public VO getValueObject() {
		return element;
	}

	@Override
	public String getStereotype() {		
		return element.stereotype;
	}

	private Collection<IAttribute> attributes;
	
	@Override
	public Collection<IAttribute> getAttributes() {
		//Lazy loading
		if (attributes==null) {
			attributes=new ArrayList<IAttribute>(MetafacadeBuilder.getMetafacadeBuilder().getAttributes(element.uri));
		}
		return attributes;
	}

}
