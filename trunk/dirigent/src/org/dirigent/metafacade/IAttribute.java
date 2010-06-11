package org.dirigent.metafacade;

import java.util.Map;

public interface IAttribute {
	/**
	 * Name of attribute.
	 * */
	public String getName();

	/**
	 * Description of attribute.
	 * */
	public String getDescription();

	/**
	 * Properties of attribute. Attribute properties are mapped to attribute
	 * tagged values in UML models.
	 * */
	public Map<String, String> getProperties();
	
	public String getType();
}
