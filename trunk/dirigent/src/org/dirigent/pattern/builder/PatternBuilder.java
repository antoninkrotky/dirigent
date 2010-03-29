package org.dirigent.pattern.builder;

import org.dirigent.pattern.IPattern;
import org.dirigent.pattern.builder.jaxb.JAXBPatternBuilder;

public abstract class PatternBuilder {
	public abstract IPattern getPattern(String uri);
	
	public static PatternBuilder getPatternBuilder() {
		return new JAXBPatternBuilder();
	}
}
