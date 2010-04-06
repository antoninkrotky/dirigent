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
		
		v.countAlias = res.getString(5);
		v.countColumn = res.getString(4);
		
		v.md5 = this.evaluateBooleanValue(res.getString(6));
		v.sh1 = this.evaluateBooleanValue(res.getString(7));
		return v;
	}

	/**
	 * returns true if value is 1 or true, false otherwise
	 * 
	 * @param String
	 *            value
	 * @return boolean
	 */
	private boolean evaluateBooleanValue(String value) {
		if (value.equals("1") || value.equals("true"))
			return true;
		return false;
	}

}
