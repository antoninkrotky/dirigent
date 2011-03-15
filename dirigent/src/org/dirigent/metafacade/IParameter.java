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
 * Metafacade representing parameter of operation.
 * @author khubl
 *
 */
public interface IParameter {
	/**
	 * Name of parameter.
	 * */
	public String getName();
	public String getStereotype();
	public String getDescription();	

	/**
	 * Type of parameter.
	 * */
	public String getType();
	/**
	 * Parameter classifier. Classifier is model class representing attribute type.
	 * */
	public IElement getClassifier();
	
	/**
	 * Properties of parameter. Parameter properties are mapped to parameter
	 * tagged values in UML models.
	 * */
	public Map<String, String> getProperties();
	
	/**
	 * Indicate if parameter is array of specified type.
	 * */
	public boolean isArray();
	
	

}
