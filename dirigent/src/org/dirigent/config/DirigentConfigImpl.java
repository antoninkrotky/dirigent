package org.dirigent.config;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class DirigentConfigImpl extends DirigentConfig {

	private Properties p;

	protected DirigentConfigImpl(String configName) {
		p = new Properties();
		String configFile = configName + ".dirigent.properties";
		try {
			String path = System.getProperty("dirigent.home", System
					.getProperty("user.home")
					+ "/.dirigent")
					+ "/" + configFile;
			File f = new File(path);
			if (f.exists()) {
				p.load(new FileInputStream(f));
			} else {
				p.load(this.getClass().getResourceAsStream("/" + configFile));
			}
		} catch (Exception e) {
			throw new RuntimeException("Cannot load configuration file "
					+ configFile + ".", e);
		}
		p.putAll(System.getProperties());

	}

	@Override
	public String getProperty(String key) {
		return p.getProperty(key);
	}

}
