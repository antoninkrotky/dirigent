package org.dirigent.flex.command.test;

import junit.framework.TestCase;

import org.dirigent.flex.command.Generate;

public class TestGenerate extends TestCase {
	public void testGenerate(){
		System.setProperty("dirigent.model.type", "EA");
		Generate c=new Generate();
		c.uri="{FAB19831-4645-4d43-B301-1D441A7EFD35}";
		c.execute();
	}
}
