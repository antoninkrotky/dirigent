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
import org.dirigent.metafacade.builder.odi.vo.OdiTxtVO;

/**
 * @author khubl
 *
 */
public class SnpTxtDao extends AbstractDao<OdiTxtVO> {

	
	public String getText(long id) {
		StringBuffer sb=null;
		for (OdiTxtVO v : findById(id)) {
			if (sb==null) {
				sb=new StringBuffer();
			} else {
				sb.append('\n');
			}
			sb.append(v.txt);
		}
		return sb.toString();
	}
	
	
	public Collection<OdiTxtVO> findById(long id) {
		return findVOs("select * from snp_txt where i_txt=? order by TXT_ORD", new Object[]{id});
	}
	
	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.builder.AbstractDao#createVO(java.sql.ResultSet)
	 */
	@Override
	protected OdiTxtVO createVO(ResultSet res) throws SQLException {
		OdiTxtVO v=new OdiTxtVO();
		v.uri="TXT_"+res.getLong("I_TXT");
		v.id=res.getLong("I_TXT");
		v.txt=res.getString("TXT");
		return v;
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.builder.AbstractDao#insert(java.lang.Object)
	 */
	@Override
	public void insert(OdiTxtVO v) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.builder.AbstractDao#update(java.lang.Object)
	 */
	@Override
	public void update(OdiTxtVO v) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.builder.AbstractDao#delete(java.lang.Object)
	 */
	@Override
	public void delete(OdiTxtVO v) {
		// TODO Auto-generated method stub

	}

}
