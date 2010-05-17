package org.dirigent.metafacade.builder.ea.test;

import java.util.Collection;

import junit.framework.TestCase;

import org.dirigent.metafacade.IDimension;
import org.dirigent.metafacade.builder.MetafacadeBuilder;
import org.dirigent.metafacade.builder.vo.ObjectVO;

/**
 * Test EAMetafacadeBuilder
 * There must be an ODBC datasource named DIRIGENT configured on the computer running this test, which is of type MS Access 
 * */
public class TestEAMetafacadeBuilder extends TestCase{
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		System.setProperty("dirigent.model.type", "EA");
	}

	public void testGetDimension() {
		IDimension d=(IDimension)MetafacadeBuilder.getMetafacadeBuilder().getMetafacade("{83E9E3A0-2E6A-42f5-BCCD-718731E2AB8D}");
		assertEquals("DEPARTMENT", d.getName());
	}
	
	public void testGetChildObjects() {
		//root models
		Collection<ObjectVO> c=MetafacadeBuilder.getMetafacadeBuilder().getChildObjects(null);
		assertNotNull("No objects returned.",c);
		assertTrue("No objects returned.",c.size()>0);
		assertEquals("Model", c.iterator().next().name);
		//package
		ObjectVO object=new ObjectVO();
		object.id=1;
		object.type="Package";
		c=MetafacadeBuilder.getMetafacadeBuilder().getChildObjects(object);
		
		assertNotNull("No objects returned.",c);
		assertTrue("No objects returned.",c.size()>0);
		assertEquals("Domains", c.iterator().next().name);

	}
}