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
package org.dirigent.metafacade.builder.ea.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 
 * DAO for table t_taggedvalue. This table contains tagged values for objects, that has no special tagged value tables. For example packages, operation parameters.
 * @author khubl
 *
 */
public class EATaggedValueDAO extends EADao<String[]> {

	@Override
	protected String[] createVO(ResultSet res) throws SQLException {
		String name=res.getString(1);
		String value=res.getString(2);
		return new String[]{name,value};
	}
	
	public String[] getObjectProperty(String elementId,String property) {
		return findVO("select TagValue,Notes from t_taggedvalue where ElementID=? and TagValue=?",new Object[]{elementId,property});
	}

	public Map<String,String> getObjectProperties(String elementId) {
		Iterator<String[]> i=findVOs("select TagValue,Notes from t_taggedvalue where ElementID=?",new Object[]{elementId}).iterator();
		Map<String, String> m=new HashMap<String, String>();
		while (i.hasNext()) {
			String[] s=i.next();
			m.put(s[0], s[1]);
		}
		return m;
	}
	
	public void merge(long attributeId,String property, String value) {		
		throw new IllegalStateException();
	}


	@Override
	public void delete(String[] v) {
		throw new IllegalStateException();
		
	}

	@Override
	public void insert(String[] v) {
		throw new IllegalStateException();
		
	}



	@Override
	public void update(String[] v) {
		throw new IllegalStateException();		
	}

}
