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
package org.dirigent.metafacade;

import java.util.Map;

/**
 * @author Karel
 * 
 */
public interface ILibraryImport {
	/**
	 * @return Map of library className imports. Libraries are POJOs with public
	 *         no argument constructors containing functions to be used in
	 *         templates. Library classes will be instantiated and put into
	 *         template engine context under the defined keys.
	 */
	public Map<String, String> getLibraries();

}
