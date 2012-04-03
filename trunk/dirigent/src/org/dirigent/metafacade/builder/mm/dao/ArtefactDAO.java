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
package org.dirigent.metafacade.builder.mm.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import org.dirigent.metafacade.builder.ea.dao.EADao;
import org.dirigent.metafacade.builder.mm.vo.ArtefactVO;

/**
 * @author khubl
 *
 */
public class ArtefactDAO extends EADao<ArtefactVO> {

	/*public Object[] parseUri(String uri) {
		Object[] res=new Object[2];
		res[0]=uri.substring(0, uri.indexOf('.'));
		res[1]=uri.substring(uri.indexOf('.')+1);
		return res;
	}*/
	
	public ArtefactVO findByUri(String uri){
		return findVO("select * from artefact where artefact_uri=?", new Object[]{uri});
	}
	
	/*public ArtefactVO findByKey(Long key){
		return findVO("select * from artefact where artefact_key=?", new Object[]{key});
	}*/
	
	public Collection<ArtefactVO> findByParentUri(String uri) {
		return findVOs("select * from artefact where PARENT_ARTEFACT_URI=? order by sort_order", new Object[]{uri});
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.builder.ea.dao.EADao#createVO(java.sql.ResultSet)
	 */
	@Override
	protected ArtefactVO createVO(ResultSet res) throws SQLException {
		ArtefactVO v=new ArtefactVO();

		v.sourceName=res.getString("ARTEFACT_SOURCE_NAME");
		v.uri=res.getString("ARTEFACT_URI");
		v.name=res.getString("ARTEFACT_NAME");
		v.typeName=res.getString("ARTEFACT_TYPE_NAME");
		v.parentUri=res.getString("PARENT_ARTEFACT_URI");
		v.description=res.getString("DESCRIPTION");
		v.sortOrder=res.getInt("SORT_ORDER");
		v.expression=res.getString("EXPRESSION");
		v.dataType=res.getString("DATA_TYPE");
		v.joinType=res.getString("JOIN_TYPE");
		return v;
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.builder.ea.dao.EADao#insert(java.lang.Object)
	 */
	@Override
	public void insert(ArtefactVO v) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.builder.ea.dao.EADao#update(java.lang.Object)
	 */
	@Override
	public void update(ArtefactVO v) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.builder.ea.dao.EADao#delete(java.lang.Object)
	 */
	@Override
	public void delete(ArtefactVO v) {
		// TODO Auto-generated method stub
		
	}

}
