package org.dirigent.metafacade.builder.ea;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import org.dirigent.metafacade.builder.vo.MappingSourceVO;

public class EAMappingSourceDao extends EADao<MappingSourceVO> {

	private EAConnectorPropertyDAO connectorPropertyDao=new EAConnectorPropertyDAO();
	
	public Collection<MappingSourceVO> getMappingSources(long mappingId) {
		return findVOs(
				"select c.connector_id,c.name,c.ea_guid,so.ea_guid,eo.ea_guid"
						+ " from t_connector c,t_object so,t_object eo"
						+ " where c.stereotype='BIMappingSource'"
						+ " and c.start_object_id=so.object_id"
						+ " and c.end_object_id=eo.object_id and so.object_id=?",
				new Object[] { new BigDecimal(mappingId) });
	}

	@Override
	protected MappingSourceVO createVO(ResultSet res) throws SQLException {
		MappingSourceVO v=new MappingSourceVO();
		v.id=res.getLong(1);
		v.alias=res.getString(2);
		v.uri=res.getString(3);
		v.mappingUri=res.getString(4);
		v.sourceUri=res.getString(5);
		v.joinType=connectorPropertyDao.getObjectProperty(v.id, "joinType");
		v.joinCondition=connectorPropertyDao.getObjectProperty(v.id, "joinCondition");
		return v;
	}

	@Override
	public void delete(MappingSourceVO v) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insert(MappingSourceVO v) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(MappingSourceVO v) {
		// TODO Auto-generated method stub

	}

}
