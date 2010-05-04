package org.dirigent.kettle;

import java.util.Iterator;

import org.dirigent.config.DirigentConfig;
import org.dirigent.metafacade.builder.MetafacadeBuilder;
import org.dirigent.metafacade.builder.csv.MappingDao;
import org.dirigent.metafacade.builder.csv.SchemaDao;
import org.dirigent.metafacade.builder.decorator.SchemaDecorator;
import org.dirigent.metafacade.builder.vo.MappingVO;
import org.dirigent.metafacade.builder.vo.SchemaVO;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.trans.TransMeta;

public class DirigentPluginJob {

	public void process() throws KettleException {
		
		DirigentTransBuilder transbuilder = null; 
		TransMeta transMeta; 
		
		Iterator<SchemaVO> schemas = new SchemaDao().getSchemas().iterator();   
		while(schemas.hasNext()) {
			SchemaVO schema = schemas.next();
			transbuilder.createDBConnection(schema); 
		}
		transbuilder.finish(); 
		

	}
	

}
