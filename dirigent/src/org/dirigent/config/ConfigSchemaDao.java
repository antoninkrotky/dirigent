package org.dirigent.config;

import org.dirigent.metafacade.builder.vo.SchemaVO;

/***
 * Schema DAO for loading SchmemaVO directly from dirigent configuration. 
 */
public class ConfigSchemaDao {
	public SchemaVO getSchemaVO(String uri) {
		SchemaVO v=new SchemaVO();
		v.uri=uri;
		v.name=uri.replaceFirst("schema:", "");
		DirigentConfig c=DirigentConfig.getDirigentConfig();		
		v.jdbcDriver=c.getProperty(DirigentConfig.SCHEMA_PREFIX+v.name+".jdbcDriver");
		v.jdbcUrl=c.getProperty(DirigentConfig.SCHEMA_PREFIX+v.name+".jdbcUrl");
		v.userName=c.getProperty(DirigentConfig.SCHEMA_PREFIX+v.name+".userName");
		v.password=c.getProperty(DirigentConfig.SCHEMA_PREFIX+v.name+".password");
		v.schema=c.getProperty(DirigentConfig.SCHEMA_PREFIX+v.name+".schema");		
		return v;
	}

}
