package org.dirigent.metafacade.builder.ea;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import org.dirigent.metafacade.builder.vo.ColumnMappingVO;

public class EAColumnMappingDao extends EADao<ColumnMappingVO>{

	public Collection<ColumnMappingVO> getCollumnMapping(long mappingId) {
		return findVOs("select a.name,a.notes,o.ea_guid from t_attribute a,t_object o where a.object_id=o.object_id and a.object_id=? order by pos", new Object[]{new BigDecimal(mappingId)});
	}
	
	@Override
	protected ColumnMappingVO createVO(ResultSet res) throws SQLException {
		ColumnMappingVO v=new ColumnMappingVO();
		v.columnName=res.getString(1);
		v.expression=res.getString(2);
		v.mappingUri=res.getString(3);
		return v;
	}

	@Override
	public void delete(ColumnMappingVO v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(ColumnMappingVO v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(ColumnMappingVO v) {
		// TODO Auto-generated method stub
		
	}

}
