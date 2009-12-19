package org.dirigent.metafacade.builder.csv.test;

import junit.framework.TestCase;

import org.dirigent.metafacade.builder.csv.MappingDao;
import org.dirigent.metafacade.builder.vo.ColumnMappingVO;
import org.dirigent.metafacade.builder.vo.MappingSourceTableVO;
import org.dirigent.metafacade.builder.vo.MappingVO;

public class TestMappingDao extends TestCase {

	public void testGetMapping() {
		testVO(new MappingDao().getMapping("M_EMPLOYEE"));
	}
	
	public void testGetMappings() {
		testVO(new MappingDao().getMappings().iterator().next());
	}
	
	private void testVO(MappingVO v) {
		assertEquals("M_EMPLOYEE", v.uri);
		assertEquals("M_EMPLOYEE", v.name);
		assertEquals("S_HSQL", v.schemaUri);
		assertEquals("T_EMPLOYEE", v.targetTableUri);
		assertEquals("E.DEPT_ID=D.DEPT_ID", v.joinCondition);
		assertEquals(v.filterCondition,"");
		assertEquals(v.groupByClause,"");
		assertEquals(4, v.columnMappings.size());
		assertEquals(2, v.mappingSourceTables.size());
		ColumnMappingVO cm=v.columnMappings.iterator().next();
		assertEquals("M_EMPLOYEE", cm.mappingUri);
		assertEquals("EMPLOYEE_ID", cm.columnName);
		assertEquals("E.EMP_ID", cm.expression);
		MappingSourceTableVO mst=v.mappingSourceTables.iterator().next();
		assertEquals("M_EMPLOYEE", mst.mappingUri);
		assertEquals("T_EMP", mst.tableUri);
		assertEquals("E", mst.alias);
		
	}
}
