package org.dirigent.metafacade.builder.ea;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class EAObjectPropertyDAO extends EADao<String[]> {

	@Override
	protected String[] createVO(ResultSet res) throws SQLException {
		return new String[]{res.getString(1),res.getString(2)};
	}
	
	public String[] getObjectProperty(long id,String property) {
		return findVO("select Property,Value from t_objectproperties where Object_id=? and Property=?",new Object[]{new BigDecimal(id),property});
	}
	
	public Map<String, String> getObjectProperties(long id) {
		Collection<String[]> l=findVOs("select Property,Value from t_objectproperties where Object_id=?",new Object[]{new BigDecimal(id)});
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
