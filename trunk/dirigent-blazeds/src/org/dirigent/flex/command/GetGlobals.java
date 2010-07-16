package org.dirigent.flex.command;

import org.dirigent.flex.ICommand;

public class GetGlobals implements ICommand{

	
	@Override
	public Object execute() {
		GlobalsVO v=new GlobalsVO();
		//TODO: Do not use EADomainDao
		v.domains=new EADomainDao().getDomains();
		return v;
	}

}
