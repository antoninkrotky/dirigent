package org.dirigent.flex.command.test;

import junit.framework.TestCase;

import org.dirigent.config.DirigentConfig;
import org.dirigent.flex.command.Generate;

public class TestGenerate extends TestCase {
	public void testGenerate(){
		DirigentConfig.resetConfig();
		System.setProperty("dirigent.model.type", "EA");
		System.setProperty("dirigent.model.path", "DIRIGENT");
		Generate c=new Generate();
		c.uri="{FAB19831-4645-4d43-B301-1D441A7EFD35}";
		c.execute();
	}
}
