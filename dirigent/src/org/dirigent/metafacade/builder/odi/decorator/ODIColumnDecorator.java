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

import java.util.Collection;
import java.util.Map;

import org.dirigent.metafacade.IColumn;
import org.dirigent.metafacade.IDomain;
import org.dirigent.metafacade.IElement;
import org.dirigent.metafacade.IRelation;
import org.dirigent.metafacade.builder.odi.vo.ODIDataStoreColumnVO;
import org.dirigent.metafacade.builder.vo.VO;

/**
 * @author khubl
 * 
 */
public class ODIColumnDecorator implements IColumn {

	private ODIDataStoreColumnVO column;

	public ODIColumnDecorator(ODIDataStoreColumnVO v) {
		column = v;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dirigent.metafacade.IAttribute#getName()
	 */
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return column.name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dirigent.metafacade.IAttribute#getDescription()
	 */
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dirigent.metafacade.IAttribute#getProperties()
	 */
	@Override
	public Map<String, String> getProperties() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dirigent.metafacade.IAttribute#getType()
	 */
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dirigent.metafacade.IAttribute#getStereotype()
	 */
	@Override
	public String getStereotype() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dirigent.metafacade.IAttribute#getInitialValue()
	 */
	@Override
	public String getInitialValue() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Create dummy domain based on column data type. Works only for oracle.
	 * */
	private IElement getDomainInternal() {
		final String name, dataType, xnaValue, xunValue;
		if ("CHAR".equals(column.dataType) && column.length == 1) {
			name="FLAG";
			dataType="CHAR(1)";
			xnaValue="X";
			xunValue="X";
		} else if ("DATE".equals(column.dataType)) {
			name="DATE";
			dataType="DATE";
			xnaValue="TO_DATE('1.1.3000','dd.mm.yyyy')";
			xunValue="TO_DATE('1.1.3000','dd.mm.yyyy')";
			
		} else {
			name="CODE";
			dataType="VARCHAR2(255)";
			xnaValue="'XNA'";
			xunValue="'XUN'";
			
		}
		IDomain d = new IDomain() {
			@Override
			public VO getValueObject() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getUri() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getType() {
				// TODO Auto-generated method stub
				return dataType;
			}

			@Override
			public String getStereotype() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getStatus() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Collection<IRelation> getStartingRelations(String type,
					String stereotype, boolean includeGeneralizedRelations) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Collection<IRelation> getStartingRelations(boolean inherit) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Collection<IRelation> getStartingRelations() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Map<String, String> getProperties() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public IElement getParent() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getName() {
				return name;
			}

			@Override
			public String getKeywords() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public IElement getGeneralizedParent() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public IRelation getFirstStartingRelation(String type,
					String stereotype) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Collection<IRelation> getEndingRelations() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getDescription() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getAlias() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getXUNValue() {
				// TODO Auto-generated method stub
				return xunValue;
			}

			@Override
			public String getXNAValue() {
				// TODO Auto-generated method stub
				return xnaValue;
			}

			@Override
			public String getDataType() {
				return dataType;
			}
		};

		return d;

	}

	private IElement classifier;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dirigent.metafacade.IAttribute#getClassifier()
	 */
	@Override
	public IElement getClassifier() {
		if (classifier == null) {
			classifier = getDomainInternal();
		}
		return classifier;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dirigent.metafacade.IColumn#getDataType()
	 */
	@Override
	public String getDataType() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dirigent.metafacade.IColumn#isMandatory()
	 */
	@Override
	public Boolean isMandatory() {
		// TODO Auto-generated method stub
		return null;
	}

}
