package org.dirigent.metafacade.builder.vo;

import java.util.Map;

public class ElementVO extends VO {
	public String stereotype;
	public String type;
	public String name;
	public String description;
	private Map<String, String> properties;
	private ILazyLoader<Map<String, String>> propertiesLazyLoader;

	public ILazyLoader<Map<String, String>> getPropertiesLazyLoader() {
		return propertiesLazyLoader;
	}

	public void setPropertiesLazyLoader(
			ILazyLoader<Map<String, String>> propertiesLazyLoader) {
		this.propertiesLazyLoader = propertiesLazyLoader;
	}

	public Map<String, String> getProperties() {
		if (properties == null && getPropertiesLazyLoader() != null) {
			properties = getPropertiesLazyLoader().load();
		}
		return properties;
	}

	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}

	public long packageId;
	public String parentUri;
	public String alias;
	public String keywords;
}
