package org.dirigent.metafacade.builder.csv;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import org.dirigent.metafacade.builder.vo.TableVO;

public class TableDao extends CsvDao<TableVO> {
	public TableVO getTable(String uri) {
		return findVO("select * from Table where URI="
					+ uri);
	}
	
	public Collection<TableVO> getTables() {
		return findVOs("select * from Table");
	}

	protected TableVO createVO(ResultSet res)  throws SQLException {
		TableVO v = new TableVO();
		v.uri = res.getString(1);
		v.name = res.getString(2);
		v.schemaUri = res.getString(3);
		return v;
	}
	
}
