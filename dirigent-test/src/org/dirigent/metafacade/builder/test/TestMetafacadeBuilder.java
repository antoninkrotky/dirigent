package org.dirigent.metafacade.builder.test;

import junit.framework.TestCase;

import org.dirigent.metafacade.builder.CSVMetafacadeBuilder;
import org.dirigent.metafacade.builder.MetafacadeBuilder;
/**
 * JUnit test case for MetafacadeBuilder class.
 * */
public class TestMetafacadeBuilder extends TestCase{
	
	public void testGetMetafacadeBuilder() {
		MetafacadeBuilder mfb=MetafacadeBuilder.getMetafacadeBuilder();
		assertNotNull(mfb);
		assertTrue(mfb instanceof CSVMetafacadeBuilder);
	}

}
