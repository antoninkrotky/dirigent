package org.dirigent.metafacade.builder.test;

import junit.framework.TestCase;

import org.dirigent.config.DirigentConfig;
import org.dirigent.metafacade.IMapping;
import org.dirigent.metafacade.builder.MetafacadeBuilder;
import org.dirigent.metafacade.builder.csv.CsvMetafacadeBuilder;
import org.dirigent.test.utils.FileComparator;

/**
 * JUnit test case for MetafacadeBuilder class.
 * */
public class TestMetafacadeBuilder extends TestCase {
@Override
protected void setUp() throws Exception {
	super.setUp();
	String path = "resources/builderTestFiles/model_1";
	System.setProperty(DirigentConfig.MODEL_PATH, path);
	System.setProperty("dirigent.model.type", "CSV");
}
	/**
	 * testing basic join condition
	 */
	public void testGetMetafacadeBuilder() {
		MetafacadeBuilder mfb = MetafacadeBuilder.getMetafacadeBuilder();
		assertNotNull(mfb);
		assertTrue(mfb instanceof CsvMetafacadeBuilder);
		assertNotNull(mfb.getMetafacade("S_HSQL"));
		assertNotNull(mfb.getMetafacade("T_EMP"));
		assertNotNull(mfb.getMetafacade("M_EMPLOYEE"));
		FileComparator
				.assertEquals(
						"results/builderTests/TestMetafacadeBuilder.expected.query.1.sql",
						((IMapping) mfb.getMetafacade("M_EMPLOYEE"))
								.getSQLQuery());
	}

	/**
	 * testing basic filter clause
	 */
	public void testGetMetafacadeBuilder_2() {
		String path = "resources/builderTestFiles/model_2";
		System.setProperty(DirigentConfig.MODEL_PATH, path);
		MetafacadeBuilder mfb = MetafacadeBuilder.getMetafacadeBuilder();
		assertNotNull(mfb);
		assertTrue(mfb instanceof CsvMetafacadeBuilder);
		assertNotNull(mfb.getMetafacade("S_MYSQL"));
		assertNotNull(mfb.getMetafacade("T_EMP"));
		assertNotNull(mfb.getMetafacade("M_EMPLOYEE"));
		FileComparator
				.assertEquals(
						"results/builderTests/TestMetafacadeBuilder.expected.query.2.sql",
						((IMapping) mfb.getMetafacade("M_EMPLOYEE"))
								.getSQLQuery());
	}

	/**
	 * testing basic groubBy clause
	 */
	public void testGetMetafacadeBuilder_3() {
		String path = "resources/builderTestFiles/model_3";
		System.setProperty(DirigentConfig.MODEL_PATH, path);
		MetafacadeBuilder mfb = MetafacadeBuilder.getMetafacadeBuilder();
		assertNotNull(mfb);
		assertTrue(mfb instanceof CsvMetafacadeBuilder);
		assertNotNull(mfb.getMetafacade("S_HSQL"));
		assertNotNull(mfb.getMetafacade("T_EMP"));
		assertNotNull(mfb.getMetafacade("M_EMPLOYEE"));
		FileComparator
				.assertEquals(
						"results/builderTests/TestMetafacadeBuilder.expected.query.3.sql",
						((IMapping) mfb.getMetafacade("M_EMPLOYEE"))
								.getSQLQuery());
	}
	
	/**
	 * groupBy, filter, join
	 */
	public void testGetMetafacadeBuilder_4() {
		String path = "resources/builderTestFiles/model_4";
		System.setProperty(DirigentConfig.MODEL_PATH, path);
		MetafacadeBuilder mfb = MetafacadeBuilder.getMetafacadeBuilder();
		assertNotNull(mfb);
		assertTrue(mfb instanceof CsvMetafacadeBuilder);
		assertNotNull(mfb.getMetafacade("S_HSQL"));
		assertNotNull(mfb.getMetafacade("T_EMP"));
		assertNotNull(mfb.getMetafacade("M_EMPLOYEE"));
		FileComparator
				.assertEquals(
						"results/builderTests/TestMetafacadeBuilder.expected.query.4.sql",
						((IMapping) mfb.getMetafacade("M_EMPLOYEE"))
								.getSQLQuery());
	}
	/**
	 * so far only way how to add more than one condition in where clause
	 */
	public void testGetMetafacadeBuilder_5() {
		String path = "resources/builderTestFiles/model_5";
		System.setProperty(DirigentConfig.MODEL_PATH, path);
		MetafacadeBuilder mfb = MetafacadeBuilder.getMetafacadeBuilder();
		assertNotNull(mfb);
		assertTrue(mfb instanceof CsvMetafacadeBuilder);
		assertNotNull(mfb.getMetafacade("S_HSQL"));
		assertNotNull(mfb.getMetafacade("T_EMP"));
		assertNotNull(mfb.getMetafacade("M_EMPLOYEE"));
		FileComparator
				.assertEquals(
						"results/builderTests/TestMetafacadeBuilder.expected.query.5.sql",
						 ((IMapping) mfb.getMetafacade("M_EMPLOYEE"))
								.getSQLQuery());
	}
	
	/**
	 * more selects, count and hash functions
	 */
	public void testGetMetafacadeBuilder_6() {
		String path = "resources/builderTestFiles/model_6";
		System.setProperty(DirigentConfig.MODEL_PATH, path);
		
		MetafacadeBuilder mfb = MetafacadeBuilder.getMetafacadeBuilder();
		assertNotNull(mfb);
		assertTrue(mfb instanceof CsvMetafacadeBuilder);
		assertNotNull(mfb.getMetafacade("S_SOURCE"));
		assertNotNull(mfb.getMetafacade("S_TARGET"));
		//assertNotNull(mfb.getMetafacade("T_album"));
		//assertNotNull(mfb.getMetafacade("M_EMPLOYEE"));
		FileComparator
				.assertEquals(
						"results/builderTests/TestMetafacadeBuilder.expected.query.6.sql",
						((IMapping) mfb.getMetafacade("M_category"))
								.getSQLQuery());
	}
}