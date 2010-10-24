package org.dirigent.demo.oracle.test;

import junit.framework.TestCase;

import org.dirigent.config.DirigentConfig;
import org.dirigent.generator.Generator;
import org.dirigent.metafacade.IDimension;
import org.dirigent.metafacade.IElement;
import org.dirigent.metafacade.IMapping;
import org.dirigent.metafacade.IRelation;
import org.dirigent.metafacade.builder.MetafacadeBuilder;
import org.dirigent.metafacade.builder.vo.MappingSourceVO;

/**
 * Tests of dirigent-demo/oracle model.
 * */
public class TestModel extends TestCase{
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		System.setProperty("dirigent.home", "../dirigent-demo/oracle/config");
		System.setProperty("dirigent.model.type", "EA");
		DirigentConfig.resetConfig();		
	}
	
	public void testTimeDimension() {
		IDimension time=(IDimension)MetafacadeBuilder.getMetafacadeBuilder().getMetafacade("{65885E2D-0AEF-443f-BEB5-292A52FA4E52}");
		assertEquals("D_TIME", time.getName());
		assertEquals("TIME", time.getAlias());
	}
	
	public void testTimeMapping() {
		IMapping time=(IMapping)MetafacadeBuilder.getMetafacadeBuilder().getMetafacade("{D11D5BA3-61BF-4f2a-98A5-34DCA8B877A8}");
		assertEquals("SCD_LOAD_TIME", time.getPattern().getName());
	}
	
	public void testMappingCreate(){
		IMapping time=(IMapping)MetafacadeBuilder.getMetafacadeBuilder().getMetafacade("{A0848078-956D-4833-AF60-E8FE9794BE1B}");
		Generator.generate(time);
		//todo - validate with an etalon
	}
}
