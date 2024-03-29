package org.dirigent.metafacade.builder.vo;

import java.util.Map;

public class AttributeVO extends VO {
	public String name;
	public String description;
	public String type;
	public String classifierURI;
	public Map<String, String> properties;
	public String stereotype;
	public String initialValue;
	public int position;
	public String allowDuplicates;
}
