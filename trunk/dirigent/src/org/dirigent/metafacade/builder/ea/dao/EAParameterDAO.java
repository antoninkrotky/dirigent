package org.dirigent.metafacade.builder.ea.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import org.dirigent.metafacade.builder.ea.vo.EAParameterVO;

public class EAParameterDAO extends EADao<EAParameterVO> {

	public Collection<EAParameterVO> getParameters(long operationid) {
		return findVOs("select name,notes,ea_guid,type,Classifier, \"Default\" from t_operationparams where operationid=?  order by pos", new Object[] { new BigDecimal(operationid) });
	}

	@Override
	protected EAParameterVO createVO(ResultSet res) throws SQLException {
		EAParameterVO v = new EAParameterVO();
		v.name=res.getString(1);
		v.notes=res.getString(2);
		v.ea_guid=res.getString(3);
		v.type=res.getString(4);
		v.classifier=res.getString(5);
		v.defaultValue=res.getString(6);
		return v;
	}

	@Override
	public void delete(EAParameterVO v) {
		throw new IllegalStateException();

	}

	@Override
	public void insert(EAParameterVO v) {
		throw new IllegalStateException();

	}

	@Override
	public void update(EAParameterVO v) {
		throw new IllegalStateException();
	}

}
