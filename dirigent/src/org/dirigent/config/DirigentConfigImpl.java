package org.dirigent.config;

import java.io.IOException;
import java.util.Properties;

public class DirigentConfigImpl extends DirigentConfig {

	private Properties p;

	protected DirigentConfigImpl() {
		p=new Properties();		
		try {
			p.load(this.getClass().getResourceAsStream("/default.dirigent.properties"));
		} catch (IOException e) {
			throw new RuntimeException("Cannot load configuration file default.dirigent.properties.",e);
		}
		p.putAll(System.getProperties());	
		
	}

	@Override
	public String getProperty(String key) {
		return p.getProperty(key);
	}

}
