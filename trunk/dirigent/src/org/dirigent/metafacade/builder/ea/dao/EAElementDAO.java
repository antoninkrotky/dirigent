package org.dirigent.metafacade.builder.ea.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import org.dirigent.metafacade.builder.ea.vo.EAElementVO;

public class EAElementDAO extends EADao<EAElementVO> {


	public EAElementVO getEAElement(String uri) {
		EAElementVO v= findVO("select ea_guid,Package_id,Object_id,Name,Alias,Note,Stereotype,object_type,pdata1,(select i.ea_guid from t_package as i where i.package_id=t.Package_id) as Parent_GUID from t_object as t where ea_guid=?",new Object[]{uri});
		//Root models are not stored in t_object table - try lookup in t_package table
		if (v==null) {
			v= findVO("select ea_guid,parent_id,package_id,name,name,notes,null,'Package' as type,package_id,'' as Parent_GUID from t_package t where ea_guid=?",new Object[]{uri});
		}
		return v;
	}
	
	
	@Override
	protected EAElementVO createVO(ResultSet res) throws SQLException {
		EAElementVO vo=new EAElementVO();
		vo.guid=res.getString(1);
		vo.packageId=res.getLong(2);
		vo.objectId=res.getLong(3);
		vo.name=res.getString(4);
		vo.alias=res.getString(5);
		vo.note=res.getString(6);
		vo.stereotype=res.getString(7);
		vo.type=res.getString(8);
		vo.pdata1=res.getString(9);
		vo.parentGuid=res.getString(10);		
		return vo;
	}

	@Override
	public void delete(EAElementVO v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(EAElementVO v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(EAElementVO v) {
		// TODO Auto-generated method stub
		
	}


	public Collection<EAElementVO> getPackageElements(String uri) {
		return findVOs("select ea_guid,Package_id,Object_id,Name,Alias,Note,Stereotype,object_type,pdata1,(select i.ea_guid from t_package as i where i.package_id=t.Package_id) as Parent_GUID from t_object as t where package_id=(select pdata1 from t_object where ea_guid=?)",new Object[]{uri});
	}

}
