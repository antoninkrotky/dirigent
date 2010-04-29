package org.dirigent.metafacade.builder.ea;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EAObjectDao extends EADao<EAObjectVO> {

	@Override
	protected EAObjectVO createVO(ResultSet res) throws SQLException {
		EAObjectVO v=new EAObjectVO();
		
		v.id=res.getLong(1);
		v.type=res.getString(2);
		v.name=res.getString(3);
		v.stereotype=res.getString(4);
		v.packageId=res.getLong(5);
		v.ea_guid=res.getString(6);
		return v;
	}
	
	public EAObjectVO getObject(String guid) {
		return findVO("select Object_ID,Object_Type,Name,Stereotype,PDATA1,ea_guid from t_object where ea_guid=?",new Object[]{guid});
	}

	@Override
	public void delete(EAObjectVO v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(EAObjectVO v) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void update(EAObjectVO v) {
		// TODO Auto-generated method stub
		
	}

}
