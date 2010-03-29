package org.dirigent.metafacade.builder.csv.test;

import junit.framework.TestCase;

import org.dirigent.config.DirigentConfig;
import org.dirigent.metafacade.builder.csv.SchemaDao;
import org.dirigent.metafacade.builder.vo.SchemaVO;



public class TestSchemaDao extends TestCase {

	public void testGetSchemas_1() {
		String path = "resources/builderTestFiles/model_1";
		System.setProperty(DirigentConfig.MODEL_PATH, path);
		testVO_1(new SchemaDao().getSchemas().iterator().next());
	}
	public void testGetSchema_1() {
		String path = "resources/builderTestFiles/model_1";
		System.setProperty(DirigentConfig.MODEL_PATH, path);
		testVO_1(new SchemaDao().getSchema("S_HSQL"));
	}
	
	public void testGetSchemas_2() {
		String path = "resources/builderTestFiles/model_2";
		System.setProperty(DirigentConfig.MODEL_PATH, path);
		testVO_2(new SchemaDao().getSchemas().iterator().next());
	}
	public void testGetSchema_2() {
		testVO_2(new SchemaDao().getSchema("S_MYSQL"));
	}

	
	
	
	private void testVO_1(SchemaVO v) {
		assertEquals("S_HSQL", v.uri);
		assertEquals("TestSchema", v.name);
		assertEquals("PUBLIC", v.schema);
		assertEquals("sa", v.userName);
		assertEquals("", v.password);
		assertEquals("jdbc:hsqldb:hsql://localhost/dirigent-demo", v.jdbcUrl);
		assertEquals("org.hsqldb.jdbcDriver", v.jdbcDriver);
	}
	
	private void testVO_2(SchemaVO v) {
		assertEquals("S_MYSQL", v.uri);
		assertEquals("TestSchema", v.name);
		assertEquals("PUBLIC", v.schema);
		assertEquals("admin", v.userName);
		assertEquals("admin", v.password);
		assertEquals("jdbc:hsqldb:hsql://localhost/dirigent-demo", v.jdbcUrl);
		assertEquals("org.hsqldb.jdbcDriver", v.jdbcDriver);
	}
}
