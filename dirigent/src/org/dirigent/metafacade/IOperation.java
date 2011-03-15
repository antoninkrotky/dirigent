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

import java.util.Collection;
import java.util.Map;

/**
 * Metafacade representing element operation.
 * @author khubl
 *
 */
public interface IOperation {
	/**
	 * Name of operation.
	 * */
	public String getName();
	public String getStereotype();
	public String getDescription();	
	/**
	 * Operation return type.
	 * */
	public String getReturnType();
	
	/**
	 * Operation return type classifier. Classifier is model class representing attribute type.
	 * */
	public IElement getReturnClassifier();
	
	/**
	 * Properties of operation. Operation properties are mapped to operation
	 * tagged values in UML models.
	 * */
	public Map<String, String> getProperties();
	
	/**
	 * Indicate if operation is returning array of specified return type.
	 * */
	public boolean isReturningArray();
	
	public Collection<IParameter> getParameters();
	
	

}
