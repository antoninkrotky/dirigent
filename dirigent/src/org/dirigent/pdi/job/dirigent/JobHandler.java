/**
 *  This file is part of Dirigent - the MDA generator.
 *  Copyright (C) 2010  Karel Hubl http://dirigent.googlecode.com
 *
 *  Dirigent is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Dirigent is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU LesservGeneral Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.dirigent.pdi.job.dirigent;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

/**
 * @author user
 *
 */
public class JobHandler extends Handler {

	private static ThreadLocal<JobEntryDirigentPlugin> dirigentLocal=new ThreadLocal<JobEntryDirigentPlugin>();
	
	public static void setJobDirigentPlugin(JobEntryDirigentPlugin p) {
		JobHandler.dirigentLocal.set(p);
	}
	
	@Override
	public void publish(LogRecord record) {
		JobEntryDirigentPlugin dirigent=dirigentLocal.get();
		if (dirigent==null) {
			return;
		}
		Level level=record.getLevel();
		String message = record.getMessage();		
		if (level.intValue()<Level.INFO.intValue()) {
			dirigent.logDebug(message);
		}
		if (Level.INFO.equals(level)) {
			dirigent.logBasic(message);
		} else {
			dirigent.logError(message);
		}
		

	}

	@Override
	public void flush() {
	}

	@Override
	public void close() throws SecurityException {
	}


}
