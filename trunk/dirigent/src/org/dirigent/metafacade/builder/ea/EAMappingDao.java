package org.dirigent.metafacade.builder.ea;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.dirigent.metafacade.builder.vo.MappingVO;
import org.dirigent.metafacade.builder.vo.ObjectVO;

public class EAMappingDao extends EADao<MappingVO> {
	private EAObjectPropertyDAO objectPropertyDao = new EAObjectPropertyDAO();
	private EAColumnMappingDao columnMappingDao = new EAColumnMappingDao();
	private EAObjectDao objectDao = new EAObjectDao();
	private EAMappingSourceDao mappingSourceDao = new EAMappingSourceDao();

	public MappingVO getMapping(String uri) {
		return findVO(
				"select name,ea_guid,object_id,note from t_object where ea_guid=?",
				new Object[] { uri });
	}

	@Override
	protected MappingVO createVO(ResultSet res) throws SQLException {
		MappingVO m = new MappingVO();
		m.name = res.getString(1);
		m.uri = res.getString(2);
		m.id = res.getLong(3);
		m.businessRule=res.getString(4);

		m.filterCondition = objectPropertyDao.getObjectProperty(m.id,
				"filterCondition")[1];
		m.groupByClause = objectPropertyDao.getObjectProperty(m.id,
				"groupByClause")[1];
		m.havingClause = objectPropertyDao.getObjectProperty(m.id,
				"havingClause")[1];
		m.pattern = objectPropertyDao.getObjectProperty(m.id, "patternName")[1];
		m.columnMappings = columnMappingDao.getCollumnMapping(m.id);
		m.sources=mappingSourceDao.getMappingSources(m.id);
		
		ObjectVO mappingTarget = objectDao.getMappingTarget(m.id);
		if (mappingTarget != null) {
			m.targetTableUri = mappingTarget.uri;
		}
		// TODO: set schema URI
		m.schemaUri = "schema:default";

		return m;
	}

	@Override
	public void delete(MappingVO v) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insert(MappingVO v) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(MappingVO v) {
		// TODO Auto-generated method stub

	}

}
