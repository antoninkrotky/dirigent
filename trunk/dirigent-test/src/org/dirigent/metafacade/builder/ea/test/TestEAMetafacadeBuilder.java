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
		IDimension d=(IDimension)MetafacadeBuilder.getMetafacadeBuilder().getMetafacade("{3B3D53A6-68CF-473c-B050-F110D81C2D9D}");
		assertEquals("ODBORNOST", d.getName());
	}
	
	public void testGetChildObjects() {
		//root models
		Collection<ObjectVO> c=MetafacadeBuilder.getMetafacadeBuilder().getChildObjects(null);
		assertNotNull("No objects returned.",c);
		assertTrue("No objects returned.",c.size()>0);
		assertEquals("Model", c.iterator().next().name);
		//package
		ObjectVO object=new ObjectVO();
		object.id=4;
		object.type="Package";
		c=MetafacadeBuilder.getMetafacadeBuilder().getChildObjects(object);
		
		assertNotNull("No objects returned.",c);
		assertTrue("No objects returned.",c.size()>0);
		assertEquals("POPLATEK_ZP", c.iterator().next().name);

	}
}
