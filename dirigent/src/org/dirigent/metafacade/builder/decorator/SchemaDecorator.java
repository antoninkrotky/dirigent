package org.dirigent.metafacade.builder.decorator;

import org.dirigent.metafacade.ISchema;
import org.dirigent.metafacade.builder.vo.SchemaVO;
import org.dirigent.metafacade.builder.vo.VO;

public abstract class SchemaDecorator extends ElementDecorator implements ISchema {

	private SchemaVO schema;
	public SchemaDecorator(SchemaVO schemaVO) {
		super(schemaVO);
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

	@Override
	public VO getValueObject() {
		return schema;
	}

}
