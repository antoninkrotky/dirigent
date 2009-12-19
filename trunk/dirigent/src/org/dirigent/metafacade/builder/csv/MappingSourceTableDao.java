package org.dirigent.metafacade.builder.csv;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import org.dirigent.metafacade.builder.vo.MappingSourceTableVO;

public class MappingSourceTableDao extends CsvDao<MappingSourceTableVO> {

	public Collection<MappingSourceTableVO> getMappingSourceTablesByMappingUri(String uri) {
		return findVOs("select * from MappingSourceTable where MapingURI="+uri);
	}
	
	@Override
	protected MappingSourceTableVO createVO(ResultSet res) throws SQLException {
		MappingSourceTableVO v=new MappingSourceTableVO();
		v.mappingUri=res.getString(1);
		v.tableUri=res.getString(2);
		v.alias=res.getString(3);
		return v;
	}

}
