package org.dirigent.metafacade.builder.ea;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import org.dirigent.metafacade.builder.vo.MappingSourceTableVO;

public class EAMappingSourceTableDao extends EADao<MappingSourceTableVO> {

	public Collection<MappingSourceTableVO> getSourceTables(long mappingId) {
		return findVOs(
				"select c.connector_id,c.name,c.ea_guid,so.ea_guid,eo.ea_guid"
						+ " from t_connector c,t_object so,t_object eo"
						+ " where c.stereotype='BIMappingSource'"
						+ " and c.start_object_id=so.object_id"
						+ " and c.end_object_id=eo.object_id and so.object_id=?",
				new Object[] { new BigDecimal(mappingId) });
	}

	@Override
	protected MappingSourceTableVO createVO(ResultSet res) throws SQLException {
		MappingSourceTableVO v=new MappingSourceTableVO();
		v.id=res.getLong(1);
		v.alias=res.getString(2);
		v.uri=res.getString(3);
		v.mappingUri=res.getString(4);
		v.tableUri=res.getString(5);		
		return v;
	}

	@Override
	public void delete(MappingSourceTableVO v) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insert(MappingSourceTableVO v) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(MappingSourceTableVO v) {
		// TODO Auto-generated method stub

	}

}
