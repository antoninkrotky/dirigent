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
package org.dirigent.metafacade.builder.odi.v10g.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.dirigent.metafacade.builder.AbstractDao;
import org.dirigent.metafacade.builder.odi.vo.ODIDataStoreVO;

/**
 * @author khubl
 *
 */
public class SnpTableDao extends AbstractDao<ODIDataStoreVO> {

	
	public ODIDataStoreVO findById(long id) {
		String query="SELECT * from SNP_TABLE where I_TABLE=?";
		return findVO(query, new Object[]{id});
	}
	
	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.builder.AbstractDao#createVO(java.sql.ResultSet)
	 */
	@Override
	protected ODIDataStoreVO createVO(ResultSet res) throws SQLException {
		ODIDataStoreVO v=new ODIDataStoreVO();
		v.uri="TAB_"+res.getLong("I_TABLE");		
		v.id=res.getLong("I_TABLE");
		v.name=res.getString("TABLE_NAME");
		v.columns=new SnpColDao().findDataStoreColumns(v.id);
		return v;
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.builder.AbstractDao#insert(java.lang.Object)
	 */
	@Override
	public void insert(ODIDataStoreVO v) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.builder.AbstractDao#update(java.lang.Object)
	 */
	@Override
	public void update(ODIDataStoreVO v) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.builder.AbstractDao#delete(java.lang.Object)
	 */
	@Override
	public void delete(ODIDataStoreVO v) {
		// TODO Auto-generated method stub
		
	}

}
