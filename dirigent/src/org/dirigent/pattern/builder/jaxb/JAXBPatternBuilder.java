package org.dirigent.pattern.builder.jaxb;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;

import org.dirigent.pattern.IPattern;
import org.dirigent.pattern.builder.PatternBuilder;

public class JAXBPatternBuilder extends PatternBuilder {

	@SuppressWarnings("unchecked")
	@Override
	public IPattern getPattern(String uri) {
		try {
			JAXBContext ctx=JAXBContext
					.newInstance("org.dirigent.pattern.builder.jaxb");
			Object p=ctx.createUnmarshaller().unmarshal(JAXBPatternBuilder.class.getResourceAsStream("/patterns/"+uri));
			IPattern pt=new PatternDecorator(((JAXBElement<Pattern>)p).getValue());
			return pt;			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
