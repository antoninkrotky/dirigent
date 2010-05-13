package org.dirigent.metafacade.builder.ea;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EAConnectorPropertyDAO extends EADao<String> {

	@Override
	protected String createVO(ResultSet res) throws SQLException {
		return res.getString(1);
	}
	
	public String getObjectProperty(long id,String property) {
		return findVO("select value from t_connectortag where elementid=? and property=?",new Object[]{new BigDecimal(id),property});
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
