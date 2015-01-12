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
package org.dirigent.executor;

import java.util.Stack;

/**
 * @author khubl
 * 
 */
public class PatternExecutionStatistics {
	private static ThreadLocal<Stack<StepStatistics>> stepStatistics = new ThreadLocal<Stack<StepStatistics>>(){
		@Override
		protected Stack<StepStatistics> initialValue() {
			return new Stack<StepStatistics>();
		}
	};

	public static void reset() {
		stepStatistics.remove();
	}

	public static Stack<StepStatistics> getStepStatistics() {
		return stepStatistics.get();
	}

	
	public static long getMaxAffectedRows() {
		Stack<StepStatistics> s = getStepStatistics();
		long rowsAffected=0;
		for (StepStatistics i : s) {
			rowsAffected=Math.max(rowsAffected, i.getAffectedRows());
		}
		return rowsAffected;
	}
}
