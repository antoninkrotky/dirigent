package org.dirigent.metafacade.builder.csv;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import org.dirigent.metafacade.builder.vo.SchemaVO;

public class SchemaDao extends CsvDao<SchemaVO> {

	public SchemaVO getSchema(String uri) {
		return findVO("select * from Schema where URI="
					+ uri);	}

	public Collection<SchemaVO> getSchemas() {
		return findVOs("select * from Schema");
	}
	
	protected SchemaVO createVO(ResultSet res) throws SQLException {
		SchemaVO v = new SchemaVO();
		v.uri = res.getString(1);
		v.name = res.getString(2);
		v.schema = res.getString(3);
		v.userName = res.getString(4);
		v.password = res.getString(5);
		v.jdbcUrl = res.getString(6);
		v.jdbcDriver = res.getString(7);
		return v;
	}

}
