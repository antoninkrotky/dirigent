package org.dirigent.metafacade.builder.ea.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import org.dirigent.metafacade.builder.AbstractDao;
import org.dirigent.metafacade.builder.ea.vo.EAOperationVO;

public class EAOperationDAO extends AbstractDao<EAOperationVO> {

	public Collection<EAOperationVO> getOperations(String uri) {
		return findVOs("select operationid,name,notes,stereotype,ea_guid,type,Classifier,returnarray from t_operation where object_id=(select object_id from t_object where ea_guid=?)  order by pos", new Object[] { uri });
	}

	@Override
	protected EAOperationVO createVO(ResultSet res) throws SQLException {
		EAOperationVO v = new EAOperationVO();
		v.id=res.getLong(1);
		v.name=res.getString(2);
		v.notes=res.getString(3);
		v.stereotype=res.getString(4);
		v.ea_guid=res.getString(5);
		v.type=res.getString(6);
		v.classifier=res.getString(7);
		v.returnArray=res.getString(8);
		return v;
//		operationid integer NOT NULL DEFAULT nextval(('"operationid_seq"'::text)::regclass),
//		  object_id integer DEFAULT 0,
//		  "name" character varying(255),
//		  scope character varying(50),
//		  "type" character varying(255),
//		  returnarray character(1),
//		  stereotype character varying(50),
//		  isstatic character(1),
//		  concurrency character varying(50),
//		  notes text,
//		  behaviour text,
//		  abstract character(1),
//		  genoption text,
//		  synchronized character(1),
//		  pos integer,
//		  const integer,
//		  style character varying(255),
//		  pure integer NOT NULL DEFAULT 0,
//		  throws character varying(255),
//		  classifier character varying(50),
//		  code text,
//		  isroot integer DEFAULT 0,
//		  isleaf integer DEFAULT 0,
//		  isquery integer DEFAULT 0,
//		  stateflags character varying(255),
//		  ea_guid character varying(40),
//		  styleex text,
	}

	@Override
	public void delete(EAOperationVO v) {
		throw new IllegalStateException();

	}

	@Override
	public void insert(EAOperationVO v) {
		throw new IllegalStateException();

	}

	@Override
	public void update(EAOperationVO v) {
		throw new IllegalStateException();
	}

}
