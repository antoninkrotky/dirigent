package org.dirigent.flex;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CommandExecutor {
	private Logger l=Logger.getLogger(CommandExecutor.class.getName());
	public CommandExecutor(){}
	
	public Object execute(ICommand command) throws Throwable{
		l.info("Executing: "+command.getClass().getName()+": "+command.toString());
		try {
			long t=System.currentTimeMillis();
			Object o=command.execute();
			l.info("Executed in "+(System.currentTimeMillis()-t)+" miliseconds.");
			return o;
		} catch (Throwable t) {			
			l.log(Level.SEVERE, "Exception executing: "+command.getClass().getName()+": "+command.toString(), t);
			throw t;
		}
	}
}
