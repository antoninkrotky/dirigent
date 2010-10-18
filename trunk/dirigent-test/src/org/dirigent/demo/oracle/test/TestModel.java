package org.dirigent.demo.oracle.test;

import junit.framework.TestCase;

import org.dirigent.config.DirigentConfig;
import org.dirigent.metafacade.IDimension;
import org.dirigent.metafacade.builder.MetafacadeBuilder;

/**
 * Tests of dirigent-demo/oracle model.
 * */
public class TestModel extends TestCase{
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		DirigentConfig.resetConfig();
		System.setProperty("dirigent.home", "../dirigent-demo/oracle/config");
	}
	
	public void testTimeDimension() {
		IDimension time=(IDimension)MetafacadeBuilder.getMetafacadeBuilder().getMetafacade("{65885E2D-0AEF-443f-BEB5-292A52FA4E52}");
		assertEquals("D_TIME", time.getName());
	}
}
