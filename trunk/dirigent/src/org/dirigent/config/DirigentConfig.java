package org.dirigent.config;

public abstract class DirigentConfig {
	public static DirigentConfig getDirigentConfig() {
		return new DirigentConfigImpl();
	}
	
	public abstract String getProperty(String key);
	
	public static final String MODEL_PATH="dirigent.model.path";
}
