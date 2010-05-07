package org.dirigent.metafacade.builder.csv;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import org.dirigent.metafacade.builder.vo.MappingSourceTableVO;
import org.dirigent.metafacade.builder.vo.SchemaVO;

public class MappingSourceTableDao extends CsvDao<MappingSourceTableVO> {

	public Collection<MappingSourceTableVO> getMappingSourceTablesByMappingUri(String uri) {
		return findVOs("select * from MappingSourceTable where MapingURI="+uri);
	}
	
//	public Collection<MappingSourceTableVO> getMappings() {
//		return findVOs("select * from MappingSourceTable");
//	}

	@Override 
	protected MappingSourceTableVO createVO(ResultSet res) throws SQLException {
		MappingSourceTableVO v=new MappingSourceTableVO();
		v.mappingUri=res.getString(1);
		v.tableUri=res.getString(2);
		v.alias=res.getString(3);
		return v;
	}




}
