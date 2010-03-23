package org.dirigent.metafacade.builder.test;

import junit.framework.TestCase;

import org.dirigent.metafacade.Mapping;
import org.dirigent.metafacade.builder.MetafacadeBuilder;
import org.dirigent.metafacade.builder.csv.CsvMetafacadeBuilder;
import org.dirigent.test.utils.FileComparator;

/**
 * JUnit test case for MetafacadeBuilder class.
 * */
public class TestMetafacadeBuilder extends TestCase {

	/**
	 * testing basic join condition
	 */
	public void testGetMetafacadeBuilder() {
		String path = "resources/builderTestFiles/model_1";
		MetafacadeBuilder mfb = MetafacadeBuilder.getMetafacadeBuilder(path);
		assertNotNull(mfb);
		assertTrue(mfb instanceof CsvMetafacadeBuilder);
		assertNotNull(mfb.getMetafacade("S_HSQL"));
		assertNotNull(mfb.getMetafacade("T_EMP"));
		assertNotNull(mfb.getMetafacade("M_EMPLOYEE"));
		FileComparator
				.assertEquals(
						"results/builderTests/TestMetafacadeBuilder.expected.query.1.sql",
						((Mapping) mfb.getMetafacade("M_EMPLOYEE"))
								.getSQLQuery());
	}

	/**
	 * testing basic filter clause
	 */
	public void testGetMetafacadeBuilder_2() {
		String path = "resources/builderTestFiles/model_2";
		MetafacadeBuilder mfb = MetafacadeBuilder.getMetafacadeBuilder(path);
		assertNotNull(mfb);
		assertTrue(mfb instanceof CsvMetafacadeBuilder);
		assertNotNull(mfb.getMetafacade("S_HSQL"));
		assertNotNull(mfb.getMetafacade("T_EMP"));
		assertNotNull(mfb.getMetafacade("M_EMPLOYEE"));
		FileComparator
				.assertEquals(
						"results/builderTests/TestMetafacadeBuilder.expected.query.2.sql",
						((Mapping) mfb.getMetafacade("M_EMPLOYEE"))
								.getSQLQuery());
	}

	/**
	 * testing basic groubBy clause
	 */
	public void testGetMetafacadeBuilder_3() {
		String path = "resources/builderTestFiles/model_3";
		MetafacadeBuilder mfb = MetafacadeBuilder.getMetafacadeBuilder(path);
		assertNotNull(mfb);
		assertTrue(mfb instanceof CsvMetafacadeBuilder);
		assertNotNull(mfb.getMetafacade("S_HSQL"));
		assertNotNull(mfb.getMetafacade("T_EMP"));
		assertNotNull(mfb.getMetafacade("M_EMPLOYEE"));
		FileComparator
				.assertEquals(
						"results/builderTests/TestMetafacadeBuilder.expected.query.3.sql",
						((Mapping) mfb.getMetafacade("M_EMPLOYEE"))
								.getSQLQuery());
	}
	
	/**
	 * groupBy, filter, join
	 */
	public void testGetMetafacadeBuilder_4() {
		String path = "resources/builderTestFiles/model_4";
		MetafacadeBuilder mfb = MetafacadeBuilder.getMetafacadeBuilder(path);
		assertNotNull(mfb);
		assertTrue(mfb instanceof CsvMetafacadeBuilder);
		assertNotNull(mfb.getMetafacade("S_HSQL"));
		assertNotNull(mfb.getMetafacade("T_EMP"));
		assertNotNull(mfb.getMetafacade("M_EMPLOYEE"));
		FileComparator
				.assertEquals(
						"results/builderTests/TestMetafacadeBuilder.expected.query.4.sql",
						((Mapping) mfb.getMetafacade("M_EMPLOYEE"))
								.getSQLQuery());
	}
	/**
	 * so far only way how to add more than condition in where clause
	 */
	public void testGetMetafacadeBuilder_5() {
		String path = "resources/builderTestFiles/model_5";
		MetafacadeBuilder mfb = MetafacadeBuilder.getMetafacadeBuilder(path);
		assertNotNull(mfb);
		assertTrue(mfb instanceof CsvMetafacadeBuilder);
		assertNotNull(mfb.getMetafacade("S_HSQL"));
		assertNotNull(mfb.getMetafacade("T_EMP"));
		assertNotNull(mfb.getMetafacade("M_EMPLOYEE"));
		FileComparator
				.assertEquals(
						"results/builderTests/TestMetafacadeBuilder.expected.query.5.sql",
						((Mapping) mfb.getMetafacade("M_EMPLOYEE"))
								.getSQLQuery());
	}
	

}
