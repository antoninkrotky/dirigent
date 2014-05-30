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
import java.util.Collection;

import org.dirigent.metafacade.builder.AbstractDao;
import org.dirigent.metafacade.builder.odi.vo.ODIDataStoreColumnVO;

/**
 * @author khubl
 *
 */
public class SnpColDao extends AbstractDao<ODIDataStoreColumnVO> {

	
	public Collection<ODIDataStoreColumnVO> findDataStoreColumns(long tableId) {
		return findVOs("select c.* from SNP_COL c hwere I_TABLE=?", new Object[]{tableId});
	}
	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.builder.AbstractDao#createVO(java.sql.ResultSet)
	 */
	@Override
	protected ODIDataStoreColumnVO createVO(ResultSet res) throws SQLException {
		ODIDataStoreColumnVO v=new ODIDataStoreColumnVO();
		v.name=res.getString("COL_NAME");
		v.dataType=res.getString("SOURCE_DT");
		v.length=res.getInt("LONGC");
		return v;
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.builder.AbstractDao#insert(java.lang.Object)
	 */
	@Override
	public void insert(ODIDataStoreColumnVO v) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.builder.AbstractDao#update(java.lang.Object)
	 */
	@Override
	public void update(ODIDataStoreColumnVO v) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.builder.AbstractDao#delete(java.lang.Object)
	 */
	@Override
	public void delete(ODIDataStoreColumnVO v) {
		// TODO Auto-generated method stub

	}

}
