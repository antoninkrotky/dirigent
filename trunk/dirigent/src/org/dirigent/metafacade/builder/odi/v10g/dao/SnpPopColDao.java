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
import org.dirigent.metafacade.builder.odi.vo.ODIColumnMappingVO;

/**
 * @author khubl
 *
 */
public class SnpPopColDao extends AbstractDao<ODIColumnMappingVO> {

	private SnpTxtDao txtDao=new SnpTxtDao();
	
	public Collection<ODIColumnMappingVO> findByPopId(long popId) {
		String query="select * from SNP_POP_COL where I_POP=?";
		return findVOs(query,new Object[]{popId});
	}
	
	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.builder.AbstractDao#createVO(java.sql.ResultSet)
	 */
	@Override
	protected ODIColumnMappingVO createVO(ResultSet res) throws SQLException {
		ODIColumnMappingVO v=new ODIColumnMappingVO();
		v.uri="PCOL_"+res.getLong("I_POP_COL");
		v.id=res.getLong("I_POP_COL");
		v.name=res.getString("COL_NAME");
		v.expression=txtDao.getText(res.getLong("I_TXT_MAP"));
		return v;
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.builder.AbstractDao#insert(java.lang.Object)
	 */
	@Override
	public void insert(ODIColumnMappingVO v) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.builder.AbstractDao#update(java.lang.Object)
	 */
	@Override
	public void update(ODIColumnMappingVO v) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.builder.AbstractDao#delete(java.lang.Object)
	 */
	@Override
	public void delete(ODIColumnMappingVO v) {
		// TODO Auto-generated method stub

	}

}
