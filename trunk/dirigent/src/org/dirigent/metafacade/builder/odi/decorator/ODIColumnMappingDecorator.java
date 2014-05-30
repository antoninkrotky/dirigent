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
package org.dirigent.metafacade.builder.odi.decorator;

import java.util.Map;

import org.dirigent.metafacade.IColumn;
import org.dirigent.metafacade.IColumnMapping;
import org.dirigent.metafacade.IElement;
import org.dirigent.metafacade.builder.odi.vo.ODIColumnMappingVO;

/**
 * @author khubl
 *
 */
public class ODIColumnMappingDecorator implements IColumnMapping {

	
	private ODIColumnMappingVO v;
	/**
	 * @param v
	 */
	public ODIColumnMappingDecorator(ODIColumnMappingVO v) {
		this.v=v;
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.IAttribute#getName()
	 */
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return v.name;
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
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.IColumnMapping#getExpression()
	 */
	@Override
	public String getExpression() {
		// TODO Auto-generated method stub
		return v.expression;
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.IColumnMapping#getColumn()
	 */
	@Override
	public IColumn getColumn() {
		// TODO Auto-generated method stub
		return null;
	}

}
