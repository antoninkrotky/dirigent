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


import junit.framework.TestCase;

import org.dirigent.metafacade.builder.MetafacadeBuilder;
import org.dirigent.metafacade.builder.classloader.ClassloaderMetafacadeBuilder;

/**
 * @author Karel
 *
 */
public class TestMetafacadeBuilder extends TestCase {
	
	MetafacadeBuilder builder;
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		builder=new ClassloaderMetafacadeBuilder();
	}
	public void testGetMetafacade() {
		String testingClassName = TestingModelClass.class.getName();
		assertNotNull(builder.getMetafacade(testingClassName));		
	}
}
