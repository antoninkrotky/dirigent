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
package org.dirigent.executor;

import java.io.InputStream;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

/**
 * @author user
 * 
 */
public class DirigentResourceLoader extends ClasspathResourceLoader {
	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.apache.velocity.runtime.resource.loader.ClasspathResourceLoader#
	 * getResourceStream(java.lang.String)
	 */
	@Override
	public InputStream getResourceStream(String arg0)
			throws ResourceNotFoundException {
		InputStream is = super.getResourceStream(arg0);
		if (is == null) {
			//Try to load resource like Dirigent patterns are loaded.
			//Enables resource loading from Web container classpath in dirigent-blazeds.
			is = DirigentResourceLoader.class.getResourceAsStream(arg0);
		}
		return is;
	}

}
