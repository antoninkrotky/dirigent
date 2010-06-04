package org.dirigent.flex.command.test;

import junit.framework.TestCase;

import org.dirigent.flex.command.GetSQLQuery;
import org.dirigent.flex.command.GetSQLQueryResult;
import org.dirigent.test.utils.FileComparator;

public class TestGetSQLQuery extends TestCase {

	
	public void testCommand() {
		System.setProperty("dirigent.model.type", "EA");
		System.setProperty("dirigent.model.path", "DIRIGENT");
		GetSQLQuery c=new GetSQLQuery();
		c.uri="{FAB19831-4645-4d43-B301-1D441A7EFD35}";
		FileComparator.assertEquals("results/flex/GetSQLQuery.sql",((GetSQLQueryResult)c.execute()).query);
	}
}
