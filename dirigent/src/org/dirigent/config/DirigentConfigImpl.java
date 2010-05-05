package org.dirigent.config;

import java.util.Properties;

public class DirigentConfigImpl extends DirigentConfig {

	private Properties p;
	
	protected DirigentConfigImpl() {
		
		p=new Properties(System.getProperties());		
	}
	@Override
	public String getProperty(String key) {
		return p.getProperty(key);
	}

}
