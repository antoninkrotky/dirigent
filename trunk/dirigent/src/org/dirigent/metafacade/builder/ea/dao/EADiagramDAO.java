package org.dirigent.metafacade.builder.ea.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.dirigent.metafacade.builder.ea.vo.EADiagramVO;

public class EADiagramDAO extends EADao<EADiagramVO>{
	
	public EADiagramVO getDiagram(String uri) {
		return findVO("select diagram_id,ea_guid,name,notes,package_id,parentid,stereotype from t_diagram where ea_guid=?",new Object[]{uri});
	}
	
	public EADiagramVO getDiagram(long id) {
		return findVO("select diagram_id,ea_guid,name,notes,package_id,parentid,stereotype from t_diagram where diagram_id=?",new Object[]{new BigDecimal(id)});
	}

	@Override
	protected EADiagramVO createVO(ResultSet res) throws SQLException {
		EADiagramVO v=new EADiagramVO();
		v.diagramId=res.getLong(1);
		v.ea_guid=res.getString(2);
		v.name=res.getString(3);
		v.notes=res.getString(4);
		v.packageId=res.getLong(5);
		v.parentId=res.getLong(6);
		v.stereotype=res.getString(7);
		return v;
	}

	@Override
	public void delete(EADiagramVO v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(EADiagramVO v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(EADiagramVO v) {
		// TODO Auto-generated method stub
		
	}

}
