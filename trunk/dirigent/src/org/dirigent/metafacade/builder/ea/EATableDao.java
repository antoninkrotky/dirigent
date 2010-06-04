package org.dirigent.metafacade.builder.ea;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.dirigent.metafacade.builder.vo.TableVO;
@Deprecated
public class EATableDao extends EADao<TableVO> {

	@Override
	protected TableVO createVO(ResultSet res) throws SQLException {
		TableVO v=new TableVO();
		v.id=res.getLong(1);
		v.uri=res.getString(2);
		v.name=res.getString(3);
		v.codeName=res.getString(4);
		v.description=res.getString(5);
		//TODO: Set schema uri
		v.schemaUri="schema:default";
		return v;
	}

	public TableVO getTable(String uri) {
		return findVO("select object_id,ea_guid,name,alias,note from t_object where ea_guid=?", new Object[]{uri});
	}
	
	@Override
	public void delete(TableVO v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(TableVO v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(TableVO v) {
		// TODO Auto-generated method stub
		
	}

}
