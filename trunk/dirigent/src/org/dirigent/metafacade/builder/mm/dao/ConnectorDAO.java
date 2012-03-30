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
import org.dirigent.metafacade.builder.mm.vo.ConnectorVO;

/**
 * @author khubl
 *
 */
public class ConnectorDAO extends EADao<ConnectorVO>{

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.builder.ea.dao.EADao#createVO(java.sql.ResultSet)
	 */
	@Override
	protected ConnectorVO createVO(ResultSet res) throws SQLException {
		ConnectorVO v=new ConnectorVO();
		v.name=res.getString("CONNECTOR_NAME");
		v.sortOrder=res.getInt("SORT_ORDER");
		v.sourceArtefactUri=res.getString("SOURCE_ARTEFACT_URI");
		v.targetArtefactUri=res.getString("TARGET_ARTEFACT_URI");
		v.sourceName=res.getString("CONNECTOR_SOURCE_NAME");
		v.typeName=res.getString("CONNECTOR_TYPE_NAME");
		v.uri=res.getString("CONNECTOR_URI");
		return v;
	}

	/**
	 * @param elementUri
	 * @return
	 */
	public Collection<ConnectorVO> findStartingRelations(String elementUri) {
		//TODO: sort by connector sort_order
		return findVOs("select * from CONNECTOR c, ARTEFACT a where c.TARGET_ARTEFACT_URI=a.ARTEFACT_URI and c.SOURCE_ARTEFACT_URI=? order by a.SORT_ORDER", new String[]{elementUri});
	}
	
	public Collection<ConnectorVO> findEndingRelations(String elementUri) {
		return findVOs("select * from CONNECTOR c, ARTEFACT a where c.SOURCE_ARTEFACT_URI=a.ARTEFACT_URI and TARGET_ARTEFACT_URI=? order by a.SORT_ORDER", new String[]{elementUri});
	}
	
	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.builder.ea.dao.EADao#insert(java.lang.Object)
	 */
	@Override
	public void insert(ConnectorVO v) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.builder.ea.dao.EADao#update(java.lang.Object)
	 */
	@Override
	public void update(ConnectorVO v) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.builder.ea.dao.EADao#delete(java.lang.Object)
	 */
	@Override
	public void delete(ConnectorVO v) {
		// TODO Auto-generated method stub
		
	}



}
