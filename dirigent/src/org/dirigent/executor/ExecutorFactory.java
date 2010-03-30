package org.dirigent.executor;

import org.dirigent.pattern.IPatternStep;


public class ExecutorFactory {
	
	private static ExecutorFactory factory = new ExecutorFactory();

	private ExecutorFactory() {
	}
	
	public static IStepExecutor getStepExecutor(String type) {
		return factory.createStepExecutor(type);
	}

	private IStepExecutor createStepExecutor(String type) {
		if (IPatternStep.FILE.equals(type)) {
			return new FileExecutor();
		} else if (IPatternStep.JDBC_STATEMENT.equals(type)) {
			return new JDBCStatementExecutor();
		}
		throw new IllegalArgumentException("StepExecutor type "+type+" is not supported.");
	}
}
