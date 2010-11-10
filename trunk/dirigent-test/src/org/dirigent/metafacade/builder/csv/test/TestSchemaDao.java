package org.dirigent.metafacade.builder.csv.test;

import java.util.Iterator;

import junit.framework.TestCase;

import org.dirigent.config.DirigentConfig;
import org.dirigent.metafacade.builder.csv.SchemaDao;
import org.dirigent.metafacade.builder.vo.SchemaVO;



public class TestSchemaDao extends TestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		DirigentConfig.resetConfig();
	}
	
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
	

	public void testGetSchema_6() {
		String path = "resources/builderTestFiles/model_6";
		System.setProperty(DirigentConfig.MODEL_PATH, path);
		
		testVO_6_1(new SchemaDao().getSchema("S_SOURCE"));
		testVO_6_2(new SchemaDao().getSchema("S_TARGET"));
	}
	
	public void testGetSchemas_6() {
		String path = "resources/builderTestFiles/model_6";
		System.setProperty(DirigentConfig.MODEL_PATH, path);
		
		Iterator<SchemaVO> iterator = new SchemaDao().getSchemas().iterator();
		testVO_6_2(iterator.next());
		testVO_6_1(iterator.next());
	}
	
	private void testVO_6_1(SchemaVO v) {
		assertEquals("S_SOURCE", v.uri);
		assertEquals("CaballinusSource", v.name);
		assertEquals("caballinus_source", v.schema);
		assertEquals("root", v.userName);
		assertEquals("root", v.password);
		assertEquals("jdbc:hsqldb:hsql://localhost:3306/caballinus_source", v.jdbcUrl);
		assertEquals("org.hsqldb.jdbcDriver", v.jdbcDriver);
	}
	
	private void testVO_6_2(SchemaVO v) {
		assertEquals("S_TARGET", v.uri);
		assertEquals("CaballinusTarget", v.name);
		assertEquals("caballinus_target", v.schema);
		assertEquals("root", v.userName);
		assertEquals("root", v.password);
		assertEquals("jdbc:hsqldb:hsql://localhost:3306/caballinus_target", v.jdbcUrl);
		assertEquals("org.hsqldb.jdbcDriver", v.jdbcDriver);
	}
	
}
