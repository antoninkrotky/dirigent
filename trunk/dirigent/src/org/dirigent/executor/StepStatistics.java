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

/**
 * @author khubl
 *
 */
public class StepStatistics {
	private String stepName;
	private String stepType;
	private boolean succes;
	private long executionTime;
	private long affectedRows=-1;
	public String getStepName() {
		return stepName;
	}


	public void setStepName(String stepName) {
		this.stepName = stepName;
	}


	public String getStepType() {
		return stepType;
	}


	public void setStepType(String stepType) {
		this.stepType = stepType;
	}


	public boolean isSucces() {
		return succes;
	}


	public void setSucces(boolean succes) {
		this.succes = succes;
	}


	public long getExecutionTime() {
		return executionTime;
	}


	public void setExecutionTime(long executionTime) {
		this.executionTime = executionTime;
	}


	public long getAffectedRows() {
		return affectedRows;
	}


	public void setAffectedRows(long updatedRows) {
		this.affectedRows = updatedRows;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	private String message;
	
	
	public String getExecutionSummary() {
		StringBuffer sb=new StringBuffer();
		sb.append("Step ");
		sb.append(stepName);
		sb.append(" of type ");
		sb.append(stepType);
		sb.append(" executed in ");
		sb.append(executionTime);
		sb.append(" ms. ");
		if (affectedRows>-1) {
			sb.append(affectedRows);
			sb.append(" rows were affected.");
		}
		if (message!=null) {
			sb.append(message);
		}
		return sb.toString();
	}
}
