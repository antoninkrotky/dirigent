package org.dirigent.metafacade.builder.ea.test;

import java.util.Collection;
import java.util.Iterator;

import junit.framework.TestCase;

import org.dirigent.config.DirigentConfig;
import org.dirigent.metafacade.IDiagram;
import org.dirigent.metafacade.IDimension;
import org.dirigent.metafacade.IElement;
import org.dirigent.metafacade.IMetafacadeBase;
import org.dirigent.metafacade.builder.MetafacadeBuilder;

/**
 * Test EAMetafacadeBuilder
 * There must be an ODBC datasource named DIRIGENT configured on the computer running this test, which is of type MS Access 
 * */
public class TestEAMetafacadeBuilder extends TestCase{
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		System.setProperty("dirigent.model.type", "EA");
		DirigentConfig.resetConfig();
	}

	public void testGetDimension() {
		IDimension d=(IDimension)MetafacadeBuilder.getMetafacadeBuilder().getMetafacade("{83E9E3A0-2E6A-42f5-BCCD-718731E2AB8D}");
		assertEquals("DEPARTMENT", d.getName());
	}
	
	
	public void testDiagram(){		
		IDiagram domainsDiagram=(IDiagram)MetafacadeBuilder.getMetafacadeBuilder().getMetafacade("{70F9DD11-2A14-41c2-B68B-3AD1F0D7A1CD}");
		Collection<IElement> c=domainsDiagram.getChildElements();
		Iterator<IElement> i=c.iterator();		
		IMetafacadeBase e1=i.next();
		assertEquals("DESCRIPTION", e1.getName());
		IMetafacadeBase e2=i.next();
		assertEquals("NAME", e2.getName());
		IMetafacadeBase e3=i.next();
		assertEquals("KEY", e3.getName());
	}
}
