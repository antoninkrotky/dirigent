package org.dirigent.metafacade.builder.ea.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.dirigent.metafacade.IPackage;
import org.dirigent.metafacade.builder.AbstractDao;
import org.dirigent.metafacade.builder.MetafacadeBuilder;
import org.dirigent.metafacade.builder.ea.vo.EADiagramVO;
import org.dirigent.metafacade.builder.vo.ElementVO;
import org.dirigent.metafacade.builder.vo.ObjectVO;

public class EAObjectDao extends AbstractDao<ObjectVO> {

	@Override
	protected ObjectVO createVO(ResultSet res) throws SQLException {
		ObjectVO v = new ObjectVO();

		v.id = res.getLong(1);
		v.type = res.getString(2);
		v.name = res.getString(3);
		v.stereotype = res.getString(4);
		v.uri = res.getString(5);
		v.pdata1 = res.getString(6);

		//Replace diagram link with referenced diagram
		if ("Text".equals(v.type) && v.pdata1!=null && !"0".equals(v.pdata1)) {
			long diagramId=Long.parseLong(v.pdata1);
			EADiagramVO diagram=new EADiagramDAO().getDiagram(diagramId);
			v.id=diagram.diagramId;
			v.uri=diagram.ea_guid;
		}
		
		return v;
	}

	public Collection<ObjectVO> getDiagramObjects(long diagramId) {
		return findVOs("select o.Object_ID,o.Object_Type,o.Name,o.Stereotype,o.ea_guid,o.pdata1 from t_object o,t_diagramobjects d where o.object_id=d.object_id and d.diagram_id=? order by d.recttop desc",new Object[]{new BigDecimal(diagramId)});
	}
	
	public ObjectVO getMappingTarget(long mappingId) {
		return findVO(
				"select eo.object_id,eo.object_type,eo.name,eo.stereotype,eo.ea_guid"
						+ " from t_connector c,t_object so,t_object eo"
						+ " where	c.stereotype='BIMappingTarget'"
						+ " and c.start_object_id=so.object_id"
						+ " and c.end_object_id=eo.object_id"
						+ " and so.object_id=?", new Object[] { new BigDecimal(
						mappingId) });
	}

	public ObjectVO getObject(String guid) {
		ObjectVO v= findVO(
				"select Object_ID,Object_Type,Name,Stereotype,ea_guid,pdata1 from t_object where ea_guid=?",
				new Object[] { guid });
		//Root models are not stored in t_object table - try lookup in t_package table
		if (v==null) {
			v=findVO(
					"select t.package_id,'Package' as type,t.Name,null as Stereotype,t.ea_guid,pdata1 from t_package t where ea_guid=?",
					new Object[] { guid });
		}
		return v;
		
	}
	public ObjectVO getObjectById(long id) {
		ObjectVO v= findVO(
				"select Object_ID,Object_Type,Name,Stereotype,ea_guid,pdata1 from t_object where Object_ID=?",
				new Object[] { new BigDecimal(id) });
		return v;
		
	}

	public Collection<ObjectVO> getPackageObjects(long packageId) {
		Collection<ObjectVO> r = new ArrayList<ObjectVO>();
		if (packageId==0) {
		r
				.addAll(findVOs(
						"select t.package_id,'Package' as type,t.Name,null as Stereotype,t.ea_guid,pdata1 from t_package t where parent_id=?",
						new Object[] { new BigDecimal(packageId) }));
		}
		else  {
			r
					.addAll(findVOs(
							"select Object_ID,Object_Type,Name,Stereotype,ea_guid,pdata1 from t_object where package_id=?  order by tpos",
							new Object[] { new BigDecimal(packageId) }));
		}
		return r;
	}

	public Collection<ObjectVO> getChildObjects(String uri) {
		if (uri == null) {
			return getPackageObjects(0);
		}
		Object o=MetafacadeBuilder.getMetafacadeBuilder().getMetafacade(uri);
		if (o instanceof IPackage) {		
			return getPackageObjects((((ElementVO)((IPackage)o).getValueObject()).id));
		}
		
		return null;
	}

	@Override
	public void delete(ObjectVO v) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insert(ObjectVO v) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(ObjectVO v) {
		// TODO Auto-generated method stub

	}



}
