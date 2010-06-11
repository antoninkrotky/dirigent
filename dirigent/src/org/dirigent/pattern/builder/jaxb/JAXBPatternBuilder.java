package org.dirigent.pattern.builder.jaxb;

import java.io.File;
import java.io.FileInputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;

import org.dirigent.pattern.IPattern;
import org.dirigent.pattern.builder.PatternBuilder;

public class JAXBPatternBuilder extends PatternBuilder {

	@SuppressWarnings("unchecked")
	@Override
	public IPattern getPattern(String uri) {
		try {
			JAXBContext ctx = JAXBContext
					.newInstance("org.dirigent.pattern.builder.jaxb");
			String path = System.getProperty("dirigent.home",System.getProperty("user.home")+"/.dirigent")
					+ "/patterns/" + uri;
			File f = new File(path);
			Object p;
			if (f.exists()) {
				p = ctx.createUnmarshaller().unmarshal(new FileInputStream(f));
			} else {
				p = ctx.createUnmarshaller().unmarshal(
						JAXBPatternBuilder.class
								.getResourceAsStream("/patterns/" + uri));
			}
			IPattern pt = new PatternDecorator(((JAXBElement<Pattern>) p)
					.getValue());
			return pt;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
