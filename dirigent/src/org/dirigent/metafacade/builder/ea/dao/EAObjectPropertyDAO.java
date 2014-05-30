package org.dirigent.metafacade.builder.ea.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dirigent.metafacade.builder.AbstractDao;

public class EAObjectPropertyDAO extends AbstractDao<String[]> {

	@Override
	protected String[] createVO(ResultSet res) throws SQLException {
		String name=res.getString(1);
		String value=res.getString(2);
		if ("<memo>".equals(value)) {
			value=res.getString(3);
		}
		return new String[]{name,value};
	}
	
	public String[] getObjectProperty(long id,String property) {
		return findVO("select Property,Value,Notes from t_objectproperties where Object_id=? and Property=?",new Object[]{new BigDecimal(id),property});
	}
	
	public Map<String, String> getObjectProperties(long id) {
		Collection<String[]> l=findVOs("select Property,Value,Notes from t_objectproperties where Object_id=?",new Object[]{new BigDecimal(id)});
		Map<String,String> m = new HashMap<String, String>();
		Iterator<String[]> i=l.iterator();
		while (i.hasNext()) {
			String[] s=i.next();
			m.put(s[0], s[1]);
		}
		return m;
	}
	
	

	@Override
	public void delete(String[] v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(String[] v) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void update(String[] v) {
		// TODO Auto-generated method stub
		
	}

}
