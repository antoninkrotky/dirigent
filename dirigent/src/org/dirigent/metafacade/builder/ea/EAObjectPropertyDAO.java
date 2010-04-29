package org.dirigent.metafacade.builder.ea;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EAObjectPropertyDAO extends EADao<String> {

	@Override
	protected String createVO(ResultSet res) throws SQLException {
		return res.getString(1);
	}
	
	public String getObjectProperty(long id,String property) {
		return findVO("select Value from t_objectproperties where Object_id=? and Property=?",new Object[]{new BigDecimal(id),property});
	}

	@Override
	public void delete(String v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(String v) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void update(String v) {
		// TODO Auto-generated method stub
		
	}

}
