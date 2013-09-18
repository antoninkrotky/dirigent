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

import org.dirigent.metafacade.builder.classloader.AttributeDecorator;
import org.dirigent.metafacade.builder.classloader.ClassDecorator;
import org.junit.Test;

public class TestAttributeDecorator {

	@Test
	public void testFacadeId() throws Exception {
		AttributeDecorator id = new AttributeDecorator(new ClassDecorator(
				TestingModelClass.class),
				TestingModelClass.class.getDeclaredField("id"));
		assertEquals("id", id.getName());
		assertEquals("java.lang.Long", id.getType());
		assertEquals("0", id.getInitialValue());

	}

	@Test
	public void testFacadeItems() throws Exception {
		AttributeDecorator items = new AttributeDecorator(new ClassDecorator(
				TestingModelClass.class),
				TestingModelClass.class.getDeclaredField("items"));
		assertEquals("items", items.getName());
		assertEquals("java.util.List<java.lang.String>", items.getType());
		assertEquals(null, items.getInitialValue());

	}

}
