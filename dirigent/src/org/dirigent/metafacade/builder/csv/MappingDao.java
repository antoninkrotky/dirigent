package org.dirigent.metafacade.builder.csv;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import org.dirigent.metafacade.builder.vo.MappingVO;

public class MappingDao extends CsvDao<MappingVO> {
	
	private ColumnMappingDao cmDao = new ColumnMappingDao();
	private MappingSourceTableDao mstDao = new MappingSourceTableDao();

	public MappingVO getMapping(String uri) {
		return findVO("select * from Mapping where URI=" + uri);
	}

	public Collection<MappingVO> getMappings() {
		return findVOs("select * from Mapping");
	}

	protected MappingVO createVO(ResultSet res) throws SQLException {
		MappingVO v = new MappingVO();
		v.uri = res.getString(1);
		v.name = res.getString(2);
		v.pattern=res.getString(3);
		v.schemaUri = res.getString(4);
		v.targetTableUri = res.getString(5);
		v.joinCondition = res.getString(6);
		v.filterCondition = res.getString(7);
		v.groupByClause = res.getString(8);		
		v.columnMappings = cmDao.getColumnMappingByMappingURI(v.uri);
		v.mappingSourceTables = mstDao
				.getMappingSourceTablesByMappingUri(v.uri);
		return v;
	}

}
