/**
 *  This file is part of Dirigent - the MDA generator.
 *  Copyright (C) 2010  Karel Hubl http://dirigent.googlecode.com
 *
 *  Dirigent is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Dirigent is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU LesservGeneral Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.dirigent.metafacade.builder.classloader.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.dirigent.metafacade.builder.classloader.ClassDecorator;
import org.junit.Test;


/**
 * @author Karel
 *
 */
public class TestClassDecorator {

	private ClassDecorator metafacade=new ClassDecorator(TestingModelClass.class);
	
	@Test
	public void testFacade() {
		assertEquals(TestingModelClass.class.getName(), metafacade.getType());
		assertEquals("TestingStereotype", metafacade.getStereotype());
		assertNotNull(metafacade.getPattern());	
		assertEquals("TestingModelClass", metafacade.getName());
		assertEquals(3, metafacade.getAttributes().size());
		assertNotNull(metafacade.getSuperclass());
		assertEquals("org.dirigent.metafacade.builder.classloader.test", metafacade.getParent().getName());
		assertEquals("org/dirigent/metafacade/builder/classloader/test", metafacade.getPackagePath());
	}

}
