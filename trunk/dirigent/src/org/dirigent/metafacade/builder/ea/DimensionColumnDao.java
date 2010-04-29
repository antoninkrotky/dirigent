package org.dirigent.metafacade.builder.ea;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

import org.dirigent.metafacade.builder.vo.DimensionColumnVO;
import org.dirigent.metafacade.builder.vo.DimensionVO;

public class DimensionColumnDao extends EADao<DimensionColumnVO> {
	private EAAttributeTagDAO attributeTagDao = new EAAttributeTagDAO();
	private DomainDao doaminDao = new DomainDao();

	@Override
	protected DimensionColumnVO createVO(ResultSet res) throws SQLException {
		DimensionColumnVO v = new DimensionColumnVO();
		v.id = res.getLong(1);
		v.name = res.getString(2);
		v.description = res.getString(3);
		long classifier = res.getLong(4);
		v.uri = res.getString(5);
		v.domain = doaminDao.getDomain(classifier);
		v.scdColumnType = attributeTagDao.getObjectProperty(v.id,
				"scdColumnType");
		v.tableId=res.getLong(6);
		return v;
	}

	public Collection<DimensionColumnVO> getColumns(long id) {
		return findVOs(
				"select ID,Name,Notes,Classifier,ea_guid,Object_ID from t_attribute where Object_ID=?",
				new Object[] { new BigDecimal(id) });
	}

	public void merge(DimensionVO v, Collection<DimensionColumnVO> columns) {
		Collection<DimensionColumnVO> currentColumns = getColumns(v.id);
		Iterator<DimensionColumnVO> i = columns.iterator();
		while (i.hasNext()) {
			DimensionColumnVO c = i.next();
			currentColumns.remove(c);
			c.tableId=v.id;
			merge(c);
		}
		// delete column not found in VO
		delete(currentColumns);
	}

	public void delete(DimensionColumnVO column) {
		executeUpdate("delete from t_attribute where ea_guid=?",
				new Object[] { column.uri });
	}

	@Override
	public void insert(DimensionColumnVO v) {
		v.uri=generateGUID();
		executeUpdate("insert into t_attribute (\"Name\",\"Notes\",\"Classifier\",\"Type\",\"Object_ID\",\"ea_guid\",\"Stereotype\") values (?,?,?,?,?,?,?)", new Object[]{v.name,v.description,new BigDecimal(v.domain.id),v.domain.name,new BigDecimal(v.tableId),v.uri,"BIDimensionColumn"});
		v.id=getColumn(v.uri).id;
		attributeTagDao.merge(v.id, "scdColumnType", v.scdColumnType);
	}

	private DimensionColumnVO getColumn(String guid) {
		return findVO(
				"select ID,Name,Notes,Classifier,ea_guid,Object_ID from t_attribute where ea_guid=?",
				new Object[] { guid });
	}

	@Override
	public void update(DimensionColumnVO v) {
		executeUpdate("update t_attribute set Name=?,Notes=?,Classifier=?,Type=? where ea_guid=?", new Object[]{v.name,v.description,new BigDecimal(v.domain.id),v.domain.name,v.uri});
		attributeTagDao.merge(v.id, "scdColumnType", v.scdColumnType);

	}

}
