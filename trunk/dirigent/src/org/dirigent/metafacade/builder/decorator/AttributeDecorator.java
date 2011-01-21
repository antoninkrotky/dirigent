package org.dirigent.metafacade.builder.decorator;

import java.util.Map;

import org.dirigent.metafacade.IAttribute;
import org.dirigent.metafacade.IElement;
import org.dirigent.metafacade.builder.MetafacadeBuilder;
import org.dirigent.metafacade.builder.vo.AttributeVO;

public class AttributeDecorator implements IAttribute {

	private AttributeVO attribute;
	private IElement classifier;
	
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
	@Override
	public String getInitialValue() {
		return attribute.initialValue;
	}
	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.IAttribute#getClassifier()
	 */
	@Override
	public IElement getClassifier() {
		if (classifier==null) {
			if (attribute.classifierURI!=null) {
				classifier=MetafacadeBuilder.getMetafacadeBuilder().getMetafacade(attribute.classifierURI);
			}
		}
		return classifier;
	}

}
