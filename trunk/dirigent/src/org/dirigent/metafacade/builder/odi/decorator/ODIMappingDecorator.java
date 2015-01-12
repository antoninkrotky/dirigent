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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.dirigent.metafacade.IColumnMapping;
import org.dirigent.metafacade.IElement;
import org.dirigent.metafacade.IMapping;
import org.dirigent.metafacade.IRelation;
import org.dirigent.metafacade.ISchema;
import org.dirigent.metafacade.ITable;
import org.dirigent.metafacade.builder.odi.v10g.dao.SnpPopColDao;
import org.dirigent.metafacade.builder.odi.vo.ODIColumnMappingVO;
import org.dirigent.metafacade.builder.odi.vo.ODIMappingVO;
import org.dirigent.metafacade.builder.vo.MappingSourceVO;
import org.dirigent.metafacade.builder.vo.VO;
import org.dirigent.pattern.IPattern;

/**
 * @author khubl
 * 
 */
public class ODIMappingDecorator implements IMapping {

	private ODIMappingVO v;

	public ODIMappingDecorator(ODIMappingVO vo) {
		this.v = vo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dirigent.metafacade.IElement#getUri()
	 */
	@Override
	public String getUri() {
		return v.uri;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dirigent.metafacade.IElement#getName()
	 */
	@Override
	public String getName() {
		return v.name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dirigent.metafacade.IElement#getType()
	 */
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dirigent.metafacade.IElement#getStereotype()
	 */
	@Override
	public String getStereotype() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dirigent.metafacade.IElement#getProperties()
	 */
	@Override
	public Map<String, String> getProperties() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dirigent.metafacade.IElement#getValueObject()
	 */
	@Override
	public VO getValueObject() {
		return v;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dirigent.metafacade.IElement#getParent()
	 */
	@Override
	public IElement getParent() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dirigent.metafacade.IElement#getGeneralizedParent()
	 */
	@Override
	public IElement getGeneralizedParent() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dirigent.metafacade.IElement#getStartingRelations()
	 */
	@Override
	public Collection<IRelation> getStartingRelations() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dirigent.metafacade.IElement#getStartingRelations(boolean)
	 */
	@Override
	public Collection<IRelation> getStartingRelations(boolean inherit) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dirigent.metafacade.IElement#getStartingRelations(java.lang.String,
	 * java.lang.String, boolean)
	 */
	@Override
	public Collection<IRelation> getStartingRelations(String type,
			String stereotype, boolean includeGeneralizedRelations) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dirigent.metafacade.IElement#getFirstStartingRelation(java.lang.String
	 * , java.lang.String)
	 */
	@Override
	public IRelation getFirstStartingRelation(String type, String stereotype) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dirigent.metafacade.IElement#getEndingRelations()
	 */
	@Override
	public Collection<IRelation> getEndingRelations() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dirigent.metafacade.IElement#getDescription()
	 */
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dirigent.metafacade.IElement#getAlias()
	 */
	@Override
	public String getAlias() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dirigent.metafacade.IElement#getKeywords()
	 */
	@Override
	public String getKeywords() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dirigent.metafacade.IElement#getStatus()
	 */
	@Override
	public String getStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dirigent.metafacade.IGeneratable#getPattern()
	 */
	@Override
	public IPattern getPattern() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dirigent.metafacade.IQueriable#getSQLQuery()
	 */
	@Override
	public String getSQLQuery() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dirigent.metafacade.IQueriable#getSQLQuery(int)
	 */
	@Override
	public String getSQLQuery(int offset) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dirigent.metafacade.IMapping#getSchema()
	 */
	@Override
	public ISchema getSchema() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dirigent.metafacade.IMapping#getTargetTable()
	 */
	@Override
	public ITable getTargetTable() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dirigent.metafacade.IMapping#getSources()
	 */
	@Override
	public Map<MappingSourceVO, IElement> getSources() {
		// TODO Auto-generated method stub
		return null;
	}

	private Collection<IColumnMapping> columnMappings;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dirigent.metafacade.IMapping#getColumnMappings()
	 */
	@Override
	public Collection<IColumnMapping> getColumnMappings() {
		if (columnMappings == null) {
			columnMappings = new ArrayList<IColumnMapping>();
			for (ODIColumnMappingVO vo : new SnpPopColDao().findByPopId(v.id)) {
				columnMappings.add(new ODIColumnMappingDecorator(vo));
			}
		}
		return columnMappings;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dirigent.metafacade.IMapping#getTargetColumnList()
	 */
	@Override
	public String getTargetColumnList() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dirigent.metafacade.IMapping#injectSubqueries(java.lang.String)
	 */
	@Override
	public String injectSubqueries(String template) {
		// TODO Auto-generated method stub
		return null;
	}

}
