package org.dirigent.metafacade.builder.test;

import junit.framework.TestCase;

import org.dirigent.metafacade.Mapping;
import org.dirigent.metafacade.builder.MetafacadeBuilder;
import org.dirigent.metafacade.builder.csv.CsvMetafacadeBuilder;
import org.dirigent.test.utils.FileComparator;
/**
 * JUnit test case for MetafacadeBuilder class.
 * */
public class TestMetafacadeBuilder extends TestCase{
	
	public void testGetMetafacadeBuilder() {
		MetafacadeBuilder mfb=MetafacadeBuilder.getMetafacadeBuilder();
		assertNotNull(mfb);
		assertTrue(mfb instanceof CsvMetafacadeBuilder);
		assertNotNull(mfb.getMetafacade("S_HSQL"));
		assertNotNull(mfb.getMetafacade("T_EMP"));
		assertNotNull(mfb.getMetafacade("M_EMPLOYEE"));
		FileComparator.assertEquals(TestMetafacadeBuilder.class.getResourceAsStream("TestMetafacadeBuilder.expected.query.sql"), ((Mapping)mfb.getMetafacade("M_EMPLOYEE")).getSQLQuery());
	}

}
