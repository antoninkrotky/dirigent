package org.dirigent.metafacade.builder.ea;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EAAttributeTagDAO extends EADao<String> {

	@Override
	protected String createVO(ResultSet res) throws SQLException {
		return res.getString(1);
	}
	
	public String getObjectProperty(long attributeId,String property) {
		return findVO("select Value from t_attributetag where ElementID=? and Property=?",new Object[]{new BigDecimal(attributeId),property});
	}
	
	
	public void merge(long attributeId,String property, String value) {		
		executeUpdate("delete from t_attributetag where ElementID=? and Property=?", new Object[]{new BigDecimal(attributeId),property});
		executeUpdate("insert into t_attributetag  (\"ElementID\",\"Property\",\"Value\") values (?,?,?)", new Object[]{new BigDecimal(attributeId),property,value});
	}


	@Override
	public void delete(String v) {
		throw new IllegalStateException();
		
	}

	@Override
	public void insert(String v) {
		throw new IllegalStateException();
		
	}



	@Override
	public void update(String v) {
		throw new IllegalStateException();		
	}

}
