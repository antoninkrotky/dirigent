package org.dirigent.metafacade.builder.ea.test;

import java.util.Collection;
import java.util.Iterator;

import junit.framework.TestCase;

import org.dirigent.metafacade.IDiagram;
import org.dirigent.metafacade.IDimension;
import org.dirigent.metafacade.IElement;
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
		c=MetafacadeBuilder.getMetafacadeBuilder().getChildObjects("{83152BF8-7FA8-49de-B230-744F3D19E10B}");
		
		assertNotNull("No objects returned.",c);
		assertTrue("No objects returned.",c.size()>0);
		assertEquals("Source tables", c.iterator().next().name);

	}
	
	public void testDiagram(){		
		IDiagram domainsDiagram=(IDiagram)MetafacadeBuilder.getMetafacadeBuilder().getMetafacade("{70F9DD11-2A14-41c2-B68B-3AD1F0D7A1CD}");
		Collection<IElement> c=domainsDiagram.getChildElements();
		Iterator<IElement> i=c.iterator();		
		IElement e1=i.next();
		assertEquals("COUNT", e1.getName());
		IElement e2=i.next();
		assertEquals("CODE", e2.getName());
		IElement e3=i.next();
		assertEquals("KEY", e3.getName());
	}
}