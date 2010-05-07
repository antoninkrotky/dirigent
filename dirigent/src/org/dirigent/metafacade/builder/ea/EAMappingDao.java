package org.dirigent.metafacade.builder.ea;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.dirigent.metafacade.builder.vo.MappingVO;

public class EAMappingDao extends EADao<MappingVO>{
	private EAObjectPropertyDAO objectPropertyDao = new EAObjectPropertyDAO();
	private EAColumnMappingDao columnMappingDao= new EAColumnMappingDao();
	private EAObjectDao objectDao=new EAObjectDao();
	private EAMappingSourceTableDao mappingSourceTableDao=new EAMappingSourceTableDao();
	public MappingVO getMapping(String uri) {
		return findVO("select name,ea_guid,object_id from t_object where ea_guid=?",new Object[]{uri});
	}

	@Override
	protected MappingVO createVO(ResultSet res) throws SQLException {
		MappingVO m=new MappingVO();
		m.name=res.getString(1);
		m.uri=res.getString(2);
		m.id=res.getLong(3);		
		m.filterCondition=objectPropertyDao.getObjectProperty(m.id, "filterCondition");
		m.groupByClause=objectPropertyDao.getObjectProperty(m.id, "groupByClause");
		m.havingClause=objectPropertyDao.getObjectProperty(m.id, "havingClause");
		m.joinCondition=objectPropertyDao.getObjectProperty(m.id, "joinCondition");		
		m.pattern=objectPropertyDao.getObjectProperty(m.id, "patternName");
		m.columnMappings=columnMappingDao.getCollumnMapping(m.id);
		m.mappingSourceTables=mappingSourceTableDao.getSourceTables(m.id);
		m.targetTableUri=objectDao.getMappingTarget(m.id).uri;
		//TODO: set schema URI
		m.schemaUri="schema:default";
		
		
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
