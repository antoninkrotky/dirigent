package org.dirigent.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Abstract Dirigent configuration factory. There can be more configurations
 * with different names. There is shared configuration cache. The default
 * configuration name is "default". For current thread configuration name to use
 * can be changed using setConfigName method. The resetConfig method can be used
 * to remove current thread configuration from cache.
 * */
public abstract class DirigentConfig {

	/**
	 * Cache of configurations.
	 * */
	private static Map<String, DirigentConfig> configMap = new HashMap<String, DirigentConfig>();

	/**
	 * Creates configuration. 
	 * */
	public static DirigentConfig getDirigentConfig() {
		DirigentConfig c = configMap.get(configName.get());
		if (c == null) {
			c = new DirigentConfigImpl(configName.get());
			configMap.put(configName.get(), c);
		}
		return c;
	}

	/**
	 * Removes current configuration from cache.
	 * */
	public static void resetConfig() {
		configMap.remove(configName.get());
	}

	private static final ThreadLocal<String> configName = new ThreadLocal<String>() {
		@Override
		protected String initialValue() {
			return System.getProperty("dirigent.config", "default");
		}
	};

	public static void setConfigName(String name) {
		configName.set(name);
	}

	public abstract String getProperty(String key);
	public abstract String getProperty(String key,String defaultValue);
	public abstract Set<String> getProperties();
	public abstract Set<String> getProperties(String prefix);

	public static final String MODEL_PATH = "dirigent.model.path";
	public static final String MODEL_DRIVER = "dirigent.model.jdbcDriver";
	public static final String MODEL_URL = "dirigent.model.jdbcUrl";
	public static final String MODEL_USER = "dirigent.model.userName";
	public static final String MODEL_PASSWORD = "dirigent.model.password";
	public static final String SCHEMA_PREFIX = "dirigent.schema.";
	public static final String MODEL_TYPE = "dirigent.model.type";

	//Allow defualt pattern override (default true)
	public static final String PATTERN_OVERRIDE = "dirigent.pattern.override.default";
	
	public static final String DEFAULT_PATTERN_PACKAGE = "dirigent.pattern.default.package";
	public static final String DEFAULT_PATTERN_ELEMENT = "dirigent.pattern.default.element";
	public static final String DEFAULT_PATTERN_DIAGRAM = "dirigent.pattern.default.diagram";
	
	public static final String DIRIGENT_BROWSER_ROOT_URI="dirigent.gui.browser.root.uri";

	public static final String DIRIGENT_CONTEXT_FACTORY = "dirigent.context.factory";
	public static final String DIRIGENT_FILEEXECUTOR_ENCODING = "dirigent.fileexecutor.encoding";
	
	public static final String GLOBAL_LIBRARY_PREFIX="dirigent.library";
	
	//public static final String DEFAULT_PATTERN_MAPPING = "dirigent.pattern.default.mapping";
	//public static final String DEFAULT_PATTERN_DIMENSION = "dirigent.pattern.default.dimension";
	//public static final String DEFAULT_PATTERN_TABLE = "dirigent.pattern.default.table";

}
