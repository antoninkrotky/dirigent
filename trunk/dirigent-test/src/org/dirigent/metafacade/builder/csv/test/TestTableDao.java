package org.dirigent.metafacade.builder.csv.test;

import java.util.Iterator;

import junit.framework.TestCase;

import org.dirigent.config.DirigentConfig;
import org.dirigent.metafacade.builder.csv.TableDao;
import org.dirigent.metafacade.builder.vo.TableVO;

public class TestTableDao extends TestCase {
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		DirigentConfig.resetConfig();
	}
	public void testGetTables_1() {
		String path = "resources/builderTestFiles/model_1";
		System.setProperty(DirigentConfig.MODEL_PATH, path);
		Iterator<TableVO> tables = new TableDao().getTables().iterator();
		testVO_1_0(tables.next());
		testVO_1_1(tables.next());
		testVO_1_2(tables.next());
	}
	
	public void testGetTable_1() {
		testVO_1_0(new TableDao().getTable("T_EMP"));
		testVO_1_1(new TableDao().getTable("T_DEPT"));
		testVO_1_2(new TableDao().getTable("T_EMPLOYEE"));
	}
	
	private void testVO_1_0(TableVO v) {
		assertEquals("S_HSQL", v.schemaUri);
		assertEquals("EMP", v.name);
		assertEquals("T_EMP", v.uri);
	}
	
	private void testVO_1_1(TableVO v) {
		assertEquals("S_HSQL", v.schemaUri);
		assertEquals("DEPT", v.name);
		assertEquals("T_DEPT", v.uri);
	}
	
	private void testVO_1_2(TableVO v) {
		assertEquals("S_HSQL", v.schemaUri);
		assertEquals("EMPLOYEE", v.name);
		assertEquals("T_EMPLOYEE", v.uri);
	}
	
	/**
	 * model 2
	 */
	public void testGetTables_2() {
		String path = "resources/builderTestFiles/model_2";
		System.setProperty(DirigentConfig.MODEL_PATH, path);
		Iterator<TableVO> tables = new TableDao().getTables().iterator();
		testVO_2_0(tables.next());
		testVO_2_1(tables.next());
		testVO_2_2(tables.next());
	}
	
	private void testVO_2_0(TableVO v) {
		assertEquals("S_MYSQL", v.schemaUri);
		assertEquals("EMP", v.name);
		assertEquals("T_EMP", v.uri);
		
	}
	
	private void testVO_2_1(TableVO v) {
		assertEquals("S_MYSQL", v.schemaUri);
		assertEquals("DEPT", v.name);
		assertEquals("T_DEPT", v.uri);
	}
	
	private void testVO_2_2(TableVO v) {
		assertEquals("S_MYSQL", v.schemaUri);
		assertEquals("EMPLOYEE", v.name);
		assertEquals("T_EMPLOYEE", v.uri);
	}
	
	/**
	 * model 3 
	 */
	public void testGetTables_3() {
		String path = "resources/builderTestFiles/model_3";
		System.setProperty(DirigentConfig.MODEL_PATH, path);
		Iterator<TableVO> tables = new TableDao().getTables().iterator();
		testVO_3_0(tables.next());
		testVO_3_1(tables.next());
		testVO_3_2(tables.next());
	}
	
	private void testVO_3_0(TableVO v) {
		assertEquals("S_HSQL", v.schemaUri);
		assertEquals("EMP", v.name);
		assertEquals("T_EMP", v.uri);
	}
	
	private void testVO_3_1(TableVO v) {
		assertEquals("S_HSQL", v.schemaUri);
		assertEquals("DEPT", v.name);
		assertEquals("T_DEPT", v.uri);
	}
	
	private void testVO_3_2(TableVO v) {
		assertEquals("S_HSQL", v.schemaUri);
		assertEquals("EMPLOYEE", v.name);
		assertEquals("T_EMPLOYEE", v.uri);
	}
	
	/**
	 * model 4
	 */
	public void testGetTables_4() {
		String path = "resources/builderTestFiles/model_4";
		System.setProperty(DirigentConfig.MODEL_PATH, path);
		Iterator<TableVO> tables = new TableDao().getTables().iterator();
		testVO_4_0(tables.next());
		testVO_4_1(tables.next());
		testVO_4_2(tables.next());
	}
	
	private void testVO_4_0(TableVO v) {
		assertEquals("S_HSQL", v.schemaUri);
		assertEquals("EMP", v.name);
		assertEquals("T_EMP", v.uri);
	}
	
	private void testVO_4_1(TableVO v) {
		assertEquals("S_HSQL", v.schemaUri);
		assertEquals("DEPT", v.name);
		assertEquals("T_DEPT", v.uri);
	}
	
	private void testVO_4_2(TableVO v) {
		assertEquals("S_HSQL", v.schemaUri);
		assertEquals("EMPLOYEE", v.name);
		assertEquals("T_EMPLOYEE", v.uri);
	}
	
	/**
	 * model 5 
	 */
	public void testGetTables_5() {
		String path = "resources/builderTestFiles/model_5";
		System.setProperty(DirigentConfig.MODEL_PATH, path);
		Iterator<TableVO> tables = new TableDao().getTables().iterator();  
		testVO_5_0(tables.next());
		testVO_5_1(tables.next());
		testVO_5_2(tables.next());
		testVO_5_3(tables.next());
		
	}
	
	public void testGetTable_5() {
		testVO_5_0(new TableDao().getTable("T_EMP"));
		testVO_5_1(new TableDao().getTable("T_DEPT"));
		testVO_5_2(new TableDao().getTable("T_EMPLOYEE"));
		testVO_5_3(new TableDao().getTable("T_SALARY"));
	}
	
	private void testVO_5_0(TableVO v) {
		assertEquals("S_HSQL", v.schemaUri);
		assertEquals("EMP", v.name);
		assertEquals("T_EMP", v.uri);
	}
	private void testVO_5_1(TableVO v) {
		assertEquals("S_HSQL", v.schemaUri);
		assertEquals("DEPT", v.name);
		assertEquals("T_DEPT", v.uri);
	}
	
	private void testVO_5_2(TableVO v) {
		assertEquals("S_HSQL", v.schemaUri);
		assertEquals("EMPLOYEE", v.name);
		assertEquals("T_EMPLOYEE", v.uri);
	}
	
	private void testVO_5_3(TableVO v) {
		assertEquals("S_HSQL", v.schemaUri);
		assertEquals("SALARY", v.name);
		assertEquals("T_SALARY", v.uri);
	}
	
	public void testGetTable_6() {
		String path = "resources/builderTestFiles/model_6";
		System.setProperty(DirigentConfig.MODEL_PATH, path);
		
		testVO_6_0(new TableDao().getTable("TS_sf_guard_permission"));
		testVO_6_1(new TableDao().getTable("TS_sf_guard_remember_key"));
		testVO_6_2(new TableDao().getTable("TS_sf_guard_user"));
		testVO_6_3(new TableDao().getTable("TT_album"));
		testVO_6_4(new TableDao().getTable("TT_articles"));
		testVO_6_5(new TableDao().getTable("TT_category"));
	}
	
	public void testGetSchemasCount_6() {
		String path = "resources/builderTestFiles/model_6";
		System.setProperty(DirigentConfig.MODEL_PATH, path);
		
		assertEquals(34, new TableDao().getTables().size()); 
	}
	
	private void testVO_6_0(TableVO v) {
		assertEquals("S_SOURCE", v.schemaUri);
		assertEquals("sf_guard_permission", v.name);
		assertEquals("TS_sf_guard_permission", v.uri);
	}
	
	private void testVO_6_1(TableVO v) {
		assertEquals("S_SOURCE", v.schemaUri);
		assertEquals("sf_guard_remember_key", v.name);
		assertEquals("TS_sf_guard_remember_key", v.uri);
	}
	
	private void testVO_6_2(TableVO v) {
		assertEquals("S_SOURCE", v.schemaUri);
		assertEquals("sf_guard_user", v.name);
		assertEquals("TS_sf_guard_user", v.uri);
	}
	
	private void testVO_6_3(TableVO v) {
		assertEquals("S_TARGET", v.schemaUri);
		assertEquals("album", v.name);
		assertEquals("TT_album", v.uri);
	}
	
	private void testVO_6_4(TableVO v) {
		assertEquals("S_TARGET", v.schemaUri);
		assertEquals("articles", v.name);
		assertEquals("TT_articles", v.uri);
	}
	
	private void testVO_6_5(TableVO v) {
		assertEquals("S_TARGET", v.schemaUri);
		assertEquals("category", v.name);
		assertEquals("TT_category", v.uri);
	}
}
