package org.dirigent.config;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

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

	@Override
	public String getProperty(String key, String defaultValue) {
		return p.getProperty(key,defaultValue);
	}

	/* (non-Javadoc)
	 * @see org.dirigent.config.DirigentConfig#keySet()
	 */
	@Override
	public Set<String> getProperties() {
		HashSet<String> res=new HashSet<String>(p.size());
		for (Object key : p.keySet()) {
			res.add(key.toString());			
		}
		return res;
	}

	/* (non-Javadoc)
	 * @see org.dirigent.config.DirigentConfig#getProperties(java.lang.String)
	 */
	@Override
	public Set<String> getProperties(String prefix) {
		HashSet<String> res=new HashSet<String>();
		for (String property : getProperties()) {
			if (property.startsWith(prefix)) {
				res.add(property);
			}
		}
		return res;
	}

}
