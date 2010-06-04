package org.dirigent.metafacade.builder.csv.test;

import junit.framework.TestCase;

import org.dirigent.config.DirigentConfig;
import org.dirigent.metafacade.builder.csv.MappingDao;
import org.dirigent.metafacade.builder.vo.ColumnMappingVO;
import org.dirigent.metafacade.builder.vo.MappingSourceVO;
import org.dirigent.metafacade.builder.vo.MappingVO;

public class TestMappingDao extends TestCase {
	protected void setUp() throws Exception {
		super.setUp();
		System.setProperty("dirigent.model.type", "CSV");
	}
	
	public void testGetMapping_1() {
		String path = "resources/builderTestFiles/model_1";
		System.setProperty(DirigentConfig.MODEL_PATH, path);
		testVO_1(new MappingDao().getMapping("M_EMPLOYEE"));
	}
	public void testGetMappings_1() {
		testVO_1(new MappingDao().getMappings().iterator().next());
	}
	
	private void testVO_1(MappingVO v) {
		assertEquals("M_EMPLOYEE", v.uri);
		assertEquals("M_EMPLOYEE", v.name);
		assertEquals("S_HSQL", v.schemaUri);
		assertEquals("T_EMPLOYEE", v.targetTableUri);
		//assertEquals("E.DEPT_ID=D.DEPT_ID", v.joinCondition);
		assertEquals(v.filterCondition,"");
		assertEquals(v.groupByClause,"");
		assertEquals(4, v.columnMappings.size());
		assertEquals(2, v.sources.size());
		ColumnMappingVO cm=v.columnMappings.iterator().next();
		assertEquals("M_EMPLOYEE", cm.mappingUri);
		assertEquals("EMPLOYEE_ID", cm.columnName);
		assertEquals("E.EMP_ID", cm.expression);
		MappingSourceVO mst=v.sources.iterator().next();
		assertEquals("M_EMPLOYEE", mst.mappingUri);
		assertEquals("T_EMP", mst.sourceUri);
		assertEquals("E", mst.alias);
		
	}
	
	public void testGetMapping_2() {
		String path = "resources/builderTestFiles/model_2";
		System.setProperty(DirigentConfig.MODEL_PATH, path);
		testVO_2(new MappingDao().getMapping("M_EMPLOYEE"));
	}

	private void testVO_2(MappingVO v) {
		assertEquals("M_EMPLOYEE", v.uri);
		assertEquals("M_EMPLOYEE", v.name);
		assertEquals("S_MYSQL", v.schemaUri);
		assertEquals("T_EMPLOYEE", v.targetTableUri);
		//assertEquals("", v.joinCondition);
		assertEquals("E.SALARY > 10",v.filterCondition);
		assertEquals(v.groupByClause,"");
		assertEquals(4, v.columnMappings.size());
		assertEquals(2, v.sources.size());
		ColumnMappingVO cm=v.columnMappings.iterator().next();
		assertEquals("M_EMPLOYEE", cm.mappingUri);
		assertEquals("EMPLOYEE_ID", cm.columnName);
		assertEquals("E.EMP_ID", cm.expression);
		MappingSourceVO mst=v.sources.iterator().next();
		assertEquals("M_EMPLOYEE", mst.mappingUri);
		assertEquals("T_EMP", mst.sourceUri);
		assertEquals("E", mst.alias);
	}
	
	
	public void testGetMapping_3() {
		String path = "resources/builderTestFiles/model_3";
		System.setProperty(DirigentConfig.MODEL_PATH, path);
		testVO_3(new MappingDao().getMapping("M_EMPLOYEE"));
	}

	private void testVO_3(MappingVO v) {
		assertEquals("M_EMPLOYEE", v.uri);
		assertEquals("M_EMPLOYEE", v.name);
		assertEquals("S_HSQL", v.schemaUri);
		assertEquals("T_EMPLOYEE", v.targetTableUri);
		//assertEquals("", v.joinCondition);
		assertEquals("", v.filterCondition);
		assertEquals(v.groupByClause,"E.HAIR_COLOR");
		assertEquals(4, v.columnMappings.size());
		assertEquals(2, v.sources.size());
		ColumnMappingVO cm=v.columnMappings.iterator().next();
		assertEquals("M_EMPLOYEE", cm.mappingUri);
		assertEquals("EMPLOYEE_ID", cm.columnName);
		assertEquals("E.EMP_ID", cm.expression);
		MappingSourceVO mst=v.sources.iterator().next();
		assertEquals("M_EMPLOYEE", mst.mappingUri);
		assertEquals("T_EMP", mst.sourceUri);
		assertEquals("E", mst.alias);
	}
	
	public void testGetMapping_4() {
		String path = "resources/builderTestFiles/model_4";
		System.setProperty(DirigentConfig.MODEL_PATH, path);
		testVO_4(new MappingDao().getMapping("M_EMPLOYEE"));
	}

	private void testVO_4(MappingVO v) {
		assertEquals("M_EMPLOYEE", v.uri);
		assertEquals("M_EMPLOYEE", v.name);
		assertEquals("S_HSQL", v.schemaUri);
		assertEquals("T_EMPLOYEE", v.targetTableUri);
		//assertEquals("E.ID = D.ID", v.joinCondition);
		assertEquals("S.SALARY > 100", v.filterCondition);
		assertEquals(v.groupByClause,"E.HAIR_COLOR");
		assertEquals(6, v.columnMappings.size());
		assertEquals(2, v.sources.size());
		ColumnMappingVO cm=v.columnMappings.iterator().next();
		assertEquals("M_EMPLOYEE", cm.mappingUri);
		assertEquals("EMPLOYEE_ID", cm.columnName);
		assertEquals("E.EMP_ID", cm.expression);
		MappingSourceVO mst=v.sources.iterator().next();
		assertEquals("M_EMPLOYEE", mst.mappingUri);
		assertEquals("T_EMP", mst.sourceUri);
		assertEquals("E", mst.alias);
	}
	
	public void testGetMapping_5() {
		String path = "resources/builderTestFiles/model_5";
		System.setProperty(DirigentConfig.MODEL_PATH, path);
		testVO_5(new MappingDao().getMapping("M_EMPLOYEE"));
	}

	private void testVO_5(MappingVO v) {
		assertEquals("M_EMPLOYEE", v.uri);
		assertEquals("M_EMPLOYEE", v.name);
		assertEquals("S_HSQL", v.schemaUri);
		assertEquals("T_EMPLOYEE", v.targetTableUri);
		//assertEquals("E.DEPARTMENT_ID = D.ID AND E.SALARY_ID = S.ID", v.joinCondition);
		assertEquals("", v.filterCondition);
		assertEquals(v.groupByClause,"");
		assertEquals(5, v.columnMappings.size());
		assertEquals(3, v.sources.size());
		ColumnMappingVO cm=v.columnMappings.iterator().next();
		assertEquals("M_EMPLOYEE", cm.mappingUri);
		assertEquals("EMPLOYEE_ID", cm.columnName);
		assertEquals("E.EMP_ID", cm.expression);
		MappingSourceVO mst=v.sources.iterator().next();
		assertEquals("M_EMPLOYEE", mst.mappingUri);
		assertEquals("T_EMP", mst.sourceUri);
		assertEquals("E", mst.alias);
	}
	
	public void testGetMapping_6() {
		String path = "resources/builderTestFiles/model_6";
		System.setProperty(DirigentConfig.MODEL_PATH, path);
		MappingVO v = new MappingDao().getMapping("M_category");
		assertEquals(16, v.columnMappings.size());
		assertEquals(1, v.sources.size());
		ColumnMappingVO cm=v.columnMappings.iterator().next();
		assertEquals("M_category", cm.mappingUri);
		
	}
	
	
	
	
}
