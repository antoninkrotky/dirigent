package org.dirigent.metafacade.builder.csv;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import org.dirigent.metafacade.builder.vo.ColumnMappingVO;

public class ColumnMappingDao extends CsvDao<ColumnMappingVO> {

	public Collection<ColumnMappingVO> getColumnMappingByMappingURI(
			String mappingUri) {
		return findVOs("select * from ColumnMapping where MappingUri="
				+ mappingUri);
	}

	@Override
	protected ColumnMappingVO createVO(ResultSet res) throws SQLException {
		ColumnMappingVO v = new ColumnMappingVO();
		v.mappingUri = res.getString(1);
		v.columnName = res.getString(2);
		v.expression = res.getString(3);
		return v;
	}


}
