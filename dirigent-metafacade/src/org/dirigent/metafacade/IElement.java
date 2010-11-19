/*
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
 *  along with Dirigent.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.dirigent.metafacade;

/**
 * Element metafacade interface. 
 * @author Karel Hubl
 *
 */
public interface IElement {

	/**
	 * Returns model element URI (unique resource identifier).
	 * @return model element URI (unique resource identifier).
	 */
     String
	 getUri();

	/**
	 * Sets model element URI (unique resource identifier).
	 * @param uri model element URI (unique resource identifier).
	 */
	void setUri(String uri);
	/**
	 * Returns model element type.
	 * @return model element type.
	 */
     String
	 getType();

	/**
	 * Sets model element type.
	 * @param type model element type.
	 */
	void setType(String type);
	/**
	 * Returns model element stereotype.
	 * @return model element stereotype.
	 */
     String
	 getStereotype();

	/**
	 * Sets model element stereotype.
	 * @param stereotype model element stereotype.
	 */
	void setStereotype(String stereotype);
	/**
	 * Returns model element name.
	 * @return model element name.
	 */
     String
	 getName();

	/**
	 * Sets model element name.
	 * @param name model element name.
	 */
	void setName(String name);
}

