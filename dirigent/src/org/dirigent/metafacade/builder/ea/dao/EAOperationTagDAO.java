package org.dirigent.metafacade.builder.ea.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class EAOperationTagDAO extends EADao<String[]> {

	@Override
	protected String[] createVO(ResultSet res) throws SQLException {
		String name=res.getString(1);
		String value=res.getString(2);
		if ("<memo>".equals(value)) {
			value=res.getString(3);
		}
		return new String[]{name,value};
	}
	
	public String[] getObjectProperty(long operationId,String property) {
		return findVO("select Property,Value,Notes from t_operationtag where ElementID=? and Property=?",new Object[]{new BigDecimal(operationId),property});
	}

	public Map<String,String> getObjectProperties(long operationId) {
		Iterator<String[]> i=findVOs("select Property,Value,Notes from t_operationtag where ElementID=?",new Object[]{new BigDecimal(operationId)}).iterator();
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
