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
package org.dirigent.metafacade.builder.classloader;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Map;

import org.dirigent.metafacade.IAttribute;
import org.dirigent.metafacade.IElement;

/**
 * @author khubl
 *
 */
public class FieldDecorator implements IAttribute {

	private ClassDecorator typeElement;
	private Field field;
	/**
	 * @param type 
	 * @param field
	 */
	public FieldDecorator(ClassDecorator typeElement, Field field) {
		this.typeElement=typeElement;
		this.field=field;
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.IAttribute#getName()
	 */
	@Override
	public String getName() {
		return field.getName();
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.IAttribute#getDescription()
	 */
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.IAttribute#getProperties()
	 */
	@Override
	public Map<String, String> getProperties() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.IAttribute#getType()
	 */
	@Override
	public String getType() {
		Type genericType=field.getGenericType();
		Class<?> type=field.getType();
		if (genericType instanceof Class) {
			return type.getName();
		} else {
			return genericType.toString();
		}
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.IAttribute#getStereotype()
	 */
	@Override
	public String getStereotype() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.IAttribute#getInitialValue()
	 */
	@Override
	public String getInitialValue() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.IAttribute#getClassifier()
	 */
	@Override
	public IElement getClassifier() {
		return typeElement;
	}

}
