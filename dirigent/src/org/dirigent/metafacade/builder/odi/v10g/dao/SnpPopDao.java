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
import org.dirigent.metafacade.builder.odi.vo.ODIMappingVO;

/**
 * @author khubl
 *
 */
public class SnpPopDao extends AbstractDao<ODIMappingVO> {

	
	public ODIMappingVO findById(long id) {
		return findVO("select * from SNP_POP where I_POP=?", new Object[]{id});
	}
	
	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.builder.AbstractDao#createVO(java.sql.ResultSet)
	 */
	@Override
	protected ODIMappingVO createVO(ResultSet res) throws SQLException {
		ODIMappingVO v=new ODIMappingVO();
		v.uri="POP_"+Long.toString(res.getLong("I_POP"));
		v.id=res.getLong("I_POP");
		v.name=res.getString("POP_NAME");
		v.targetTable=new SnpTableDao().findById(res.getLong("I_TABLE"));
		return v;
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.builder.AbstractDao#insert(java.lang.Object)
	 */
	@Override
	public void insert(ODIMappingVO v) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.builder.AbstractDao#update(java.lang.Object)
	 */
	@Override
	public void update(ODIMappingVO v) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.builder.AbstractDao#delete(java.lang.Object)
	 */
	@Override
	public void delete(ODIMappingVO v) {
		// TODO Auto-generated method stub

	}

	/**
	 * @param uri
	 * @return
	 */
	public ODIMappingVO findByUri(String uri) {
		if (!uri.startsWith("POP_")){
			throw new RuntimeException("Invalid uri. Uri of ODI interface must start with POP_");
		}
		long id=Long.parseLong(uri.substring(4));
		return findById(id);
	}

}
