package org.dirigent.metafacade.builder.csv;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import org.dirigent.metafacade.builder.vo.MappingSourceVO;
import org.dirigent.metafacade.builder.vo.SchemaVO;

public class MappingSourceTableDao extends CsvDao<MappingSourceVO> {

	public Collection<MappingSourceVO> getMappingSourceTablesByMappingUri(String uri) {
		return findVOs("select * from MappingSourceTable where MapingURI="+uri);
	}
	
	@Override 
	protected MappingSourceVO createVO(ResultSet res) throws SQLException {
		MappingSourceVO v=new MappingSourceVO();		
		v.mappingUri=res.getString(1);
		v.sourceUri=res.getString(2);
		v.alias=res.getString(3);
		v.uri=v.mappingUri+v.alias;
		v.joinType="inner";
		return v;
	}




}
