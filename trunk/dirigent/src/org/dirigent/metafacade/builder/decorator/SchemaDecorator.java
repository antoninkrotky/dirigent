package org.dirigent.metafacade.builder.decorator;

import org.dirigent.metafacade.ISchema;
import org.dirigent.metafacade.builder.vo.SchemaVO;

public class SchemaDecorator implements ISchema {

	private SchemaVO schema;
	public SchemaDecorator(SchemaVO schemaVO) {
		this.schema=schemaVO;
		
	}
	
	@Override
	public String getJdbcDriver() {
		return schema.jdbcDriver;
	}

	@Override
	public String getJdbcUrl() {
		return schema.jdbcUrl;
	}

	@Override
	public String getName() {
		return schema.name;
	}

	@Override
	public String getPassword() {
		return schema.password;
	}

	@Override
	public String getSchema() {
		return schema.schema;
	}

	@Override
	public String getUsername() {
		return schema.userName;
	}

	@Override
	public String getUri() {
		return schema.uri;
	}
}
