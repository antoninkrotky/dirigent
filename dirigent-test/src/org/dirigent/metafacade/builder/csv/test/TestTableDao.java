package org.dirigent.metafacade.builder.csv.test;

import java.util.Iterator;

import junit.framework.TestCase;

import org.dirigent.config.DirigentConfig;
import org.dirigent.metafacade.builder.csv.TableDao;
import org.dirigent.metafacade.builder.vo.TableVO;

public class TestTableDao extends TestCase {
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
	
	
	
	
}
