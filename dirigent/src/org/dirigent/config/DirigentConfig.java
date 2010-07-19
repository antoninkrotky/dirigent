package org.dirigent.config;

public abstract class DirigentConfig {
	// private static DirigentConfig config;

	public static DirigentConfig getDirigentConfig() {
		/*
		 * if (config == null) { config = new DirigentConfigImpl(); } return
		 * config;
		 */
		// TODO: Enable lazy initialisation but fix tests before.
		return new DirigentConfigImpl(configName.get());
	}

	private static final ThreadLocal<String> configName = new ThreadLocal<String>() {
		@Override
		protected String initialValue() {
			return "default";
		}
	};

	public static void setConfigName(String name) {
		configName.set(name);
	}

	public abstract String getProperty(String key);

	public static final String MODEL_PATH = "dirigent.model.path";
	public static final String SCHEMA_PREFIX = "dirigent.schema.";
	public static final String DEFAULT_PATTERN_MAPPING = "dirigent.pattern.default.mapping";
	public static final String DEFAULT_PATTERN_DIMENSION = "dirigent.pattern.default.dimension";
	public static final String MODEL_TYPE = "dirigent.model.type";
	public static final String DEFAULT_PATTERN_TABLE = "dirigent.pattern.default.table";
	public static final String DEFAULT_PATTERN_PACKAGE = "dirigent.pattern.default.package";
	public static final String DEFAULT_PATTERN_DIAGRAM = "dirigent.pattern.default.diagram";

}
