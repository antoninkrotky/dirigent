package org.dirigent.metafacade.builder.ea.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import org.dirigent.metafacade.builder.ea.vo.EAAttributeVO;

public class EAAttributeDAO extends EADao<EAAttributeVO> {

	public Collection<EAAttributeVO> getAttributes(String uri) {
		return findVOs("select id,name,notes,length,precision,scale,stereotype,pos,ea_guid,type,Classifier from t_attribute where object_id=(select object_id from t_object where ea_guid=?)  order by pos", new Object[] { uri });
	}

	@Override
	protected EAAttributeVO createVO(ResultSet res) throws SQLException {
		EAAttributeVO v = new EAAttributeVO();
		v.id=res.getLong(1);
		v.name=res.getString(2);
		v.notes=res.getString(3);
		v.length=res.getBigDecimal(4);
		v.precision=res.getBigDecimal(5);
		v.scale=res.getBigDecimal(6);
		v.stereotype=res.getString(7);
		v.position=res.getInt(8);
		v.ea_guid=res.getString(9);
		v.type=res.getString(10);
		v.classifier=res.getLong(11);
		return v;
	}

	@Override
	public void delete(EAAttributeVO v) {
		throw new IllegalStateException();

	}

	@Override
	public void insert(EAAttributeVO v) {
		throw new IllegalStateException();

	}

	@Override
	public void update(EAAttributeVO v) {
		throw new IllegalStateException();
	}

}
