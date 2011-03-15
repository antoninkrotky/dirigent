package org.dirigent.pattern.builder.jaxb;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;

import org.dirigent.config.DirigentConfig;
import org.dirigent.pattern.IPattern;
import org.dirigent.pattern.builder.PatternBuilder;

public class JAXBPatternBuilder extends PatternBuilder {

	private static Logger l = Logger.getLogger(JAXBPatternBuilder.class.getName());
	
	
	@SuppressWarnings("unchecked")
	@Override
	public IPattern getPattern(String uri) {
		try {
			JAXBContext ctx = JAXBContext
					.newInstance("org.dirigent.pattern.builder.jaxb");
			String path1 = DirigentConfig.getDirigentConfig().getProperty("dirigent.home",System.getProperty("user.home")+"/.dirigent")
					+ "/patterns/" + uri;
			File f = new File(path1);
			Object p = null;
			IPattern pt = null;
			if (f.exists()) {
				try {
					p = ctx.createUnmarshaller().unmarshal(new FileInputStream(f));
				} catch (Exception e) {
					l.log(Level.SEVERE, "Not processed! Pattern " + path1 + " has invalid XML.");
					throw e;					
				}
			} else {
				String path2 = "/patterns/" + uri;
				InputStream is = JAXBPatternBuilder.class.getResourceAsStream(path2);
				if (is != null) {
					try {
						p = ctx.createUnmarshaller().unmarshal(is);
					} catch (Exception e) {
						l.log(Level.SEVERE, "Not processed! Pattern " + path2 + " has invalid XML.");
						throw e;
					}
				} else {
					l.log(Level.SEVERE, 
							"Not processed! Pattern "+uri+" not find at any of these locations: " + path1 + ", " + path2);
					 
				}
			}
			if (p != null) {
				pt = new PatternDecorator(((JAXBElement<Pattern>) p)
						.getValue());
			}
			return pt;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
