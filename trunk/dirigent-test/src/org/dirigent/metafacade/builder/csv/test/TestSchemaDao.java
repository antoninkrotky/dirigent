package org.dirigent.metafacade.builder.csv.test;

import junit.framework.TestCase;

import org.dirigent.metafacade.builder.csv.SchemaDao;
import org.dirigent.metafacade.builder.vo.SchemaVO;

public class TestSchemaDao extends TestCase {

	public void testGetSchemas() {
		testVO(new SchemaDao().getSchemas().iterator().next());
	}
	
	public void testGetSchema() {
		testVO(new SchemaDao().getSchema("S_HSQL"));
	}
	
	private void testVO(SchemaVO v) {
		assertEquals("S_HSQL", v.uri);
		assertEquals("TestSchema", v.name);
		assertEquals("PUBLIC", v.schema);
		assertEquals("sa", v.userName);
		assertEquals("", v.password);
		assertEquals("jdbc:hsqldb:hsql://localhost/dirigent-demo", v.jdbcUrl);
		assertEquals("org.hsqldb.jdbcDriver", v.jdbcDriver);
	}
}
