package org.dirigent.metafacade.builder.ea.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dirigent.metafacade.builder.AbstractDao;

public class EAConnectorTagDAO extends AbstractDao<String[]> {

	@Override
	protected String[] createVO(ResultSet res) throws SQLException {
        String value=res.getString(2);
        String notes=res.getString(3);
        if ("<memo>".equals(value)) {
                        value=notes;
        }
        return new String[]{res.getString(1),value};
	}
	
	public String[] getObjectProperty(long attributeId,String property) {
		return findVO("select Property,Value,Notes from t_connectortag where ElementID=? and Property=?",new Object[]{new BigDecimal(attributeId),property});		
	}

	public Map<String,String> getObjectProperties(long attributeId) {
		Iterator<String[]> i=findVOs("select Property,Value,Notes from t_connectortag where ElementID=?",new Object[]{new BigDecimal(attributeId)}).iterator();
		Map<String, String> m=new HashMap<String, String>();
		while (i.hasNext()) {
			String[] s=i.next();
			m.put(s[0], s[1]);
		}
		return m;
	}
	
	public void merge(long attributeId,String property, String value) {		
		executeUpdate("delete from t_attributetag where ElementID=? and Property=?", new Object[]{new BigDecimal(attributeId),property});
		executeUpdate("insert into t_attributetag  (\"elementid\",\"property\",\"value\") values (?,?,?)", new Object[]{new BigDecimal(attributeId),property,value});
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
