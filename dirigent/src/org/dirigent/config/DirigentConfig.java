package org.dirigent.config;

public abstract class DirigentConfig {
	//private static DirigentConfig config;

	public static DirigentConfig getDirigentConfig() {
		/*if (config == null) {
			config = new DirigentConfigImpl();
		}
		return config;*/
		//TODO: Enable lazy initialisation but fix tests before.
		return new DirigentConfigImpl();
	}

	public abstract String getProperty(String key);
	
	public static final String MODEL_PATH = "dirigent.model.path";
	public static final String SCHEMA_PREFIX = "dirigent.schema.";
	public static final String DEFAULT_PATTERN_MAPPING = "dirigent.pattern.default.mapping";
	public static final String MODEL_TYPE = "dirigent.model.type"; 
}
