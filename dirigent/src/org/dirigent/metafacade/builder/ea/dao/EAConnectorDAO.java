package org.dirigent.metafacade.builder.ea.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import org.dirigent.metafacade.builder.ea.vo.EAConnectorVO;

public class EAConnectorDAO extends EADao<EAConnectorVO> {

	/**
	 * Get all connectors starting in specified element.
	 * */
	public Collection<EAConnectorVO> getStartingConnectors(String elementUri) {
		return findVOs("select ea_guid,name,connector_type,stereotype,notes,start_object_id,end_object_id,connector_id from t_connector where start_object_id=(select object_id from t_object where ea_guid=?)",new Object[]{elementUri});
	}
	
	@Override
	protected EAConnectorVO createVO(ResultSet res) throws SQLException {
		EAConnectorVO v=new EAConnectorVO();
		v.ea_guid=res.getString(1);
		v.name=res.getString(2);
		v.type=res.getString(3);
		v.stereotype=res.getString(4);
		v.note=res.getString(5);
		v.startObjectId=res.getLong(6);
		v.endObjectId=res.getLong(7);
		v.id=res.getLong(8);
		return v;
	}

	@Override
	public void delete(EAConnectorVO v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(EAConnectorVO v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(EAConnectorVO v) {
		// TODO Auto-generated method stub
		
	}

}
