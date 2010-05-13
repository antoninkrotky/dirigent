package org.dirigent.flex.command;

import org.dirigent.flex.ICommand;
import org.dirigent.generator.Generator;

public class Generate implements ICommand {
	public String uri;
	
	@Override
	public Object execute() {
		Generator.generate(uri);
		return null;
	}

}
