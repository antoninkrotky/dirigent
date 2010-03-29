package org.dirigent.generator.pattern.builder.xml.test;

import junit.framework.TestCase;

import org.dirigent.pattern.IPattern;
import org.dirigent.pattern.IPatternStep;
import org.dirigent.pattern.builder.jaxb.JAXBPatternBuilder;

public class TestXmlPatternBuilder extends TestCase{

	public void testXMLPatternBuilder() {
		IPattern p=new JAXBPatternBuilder().getPattern("HSQL/TRUNCATE_INSERT.pattern.xml");
		IPatternStep step=p.getSteps().iterator().next();
		assertEquals("TRUNCATE_INSERT", p.getName());
		assertEquals("Create target table", step.getName());
		assertEquals(" --Not implemented as HSQL does not support CREATE TABLE AS SELECT.", step.getTemplate());		
	}	
}
