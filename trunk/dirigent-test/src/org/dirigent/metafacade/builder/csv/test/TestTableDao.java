package org.dirigent.metafacade.builder.csv.test;

import junit.framework.TestCase;

import org.dirigent.metafacade.builder.csv.TableDao;
import org.dirigent.metafacade.builder.vo.TableVO;

public class TestTableDao extends TestCase {
	public void testGetTables() {
		testVO(new TableDao().getTables().iterator().next());
	}
	
	public void testGetTable() {
		testVO(new TableDao().getTable("T_EMP"));
	}
	
	private void testVO(TableVO v) {
		assertEquals("S_HSQL", v.schemaUri);
		assertEquals("EMP", v.name);
		assertEquals("T_EMP", v.uri);
	}
}
