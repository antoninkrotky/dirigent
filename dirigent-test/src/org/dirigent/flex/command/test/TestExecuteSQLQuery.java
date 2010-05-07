package org.dirigent.flex.command.test;

import junit.framework.TestCase;

import org.dirigent.flex.command.ExecuteSQLQuery;

public class TestExecuteSQLQuery extends TestCase{
	public void testExecuteSQLQuery() {
		System.setProperty("dirigent.model.type", "EA");
		ExecuteSQLQuery command=new ExecuteSQLQuery();
		command.query="select * from EMP";
		command.schemaUri="schema:default";
		command.execute();
	}
}
