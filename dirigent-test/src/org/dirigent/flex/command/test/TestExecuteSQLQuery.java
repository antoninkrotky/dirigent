package org.dirigent.flex.command.test;

import junit.framework.TestCase;

import org.dirigent.config.DirigentConfig;
import org.dirigent.flex.command.ExecuteSQLQuery;

public class TestExecuteSQLQuery extends TestCase{
	
	@Override
	protected void setUp() throws Exception {
		DirigentConfig.resetConfig();
		System.getProperties().remove("dirigent.home");
		System.setProperty("dirigent.model.type", "EA");
		super.setUp();
	}
	public void testExecuteSQLQuery() {		
		ExecuteSQLQuery command=new ExecuteSQLQuery();
		command.query="select * from EMP";
		command.schemaUri="schema:default";
		command.execute();
	}
}
