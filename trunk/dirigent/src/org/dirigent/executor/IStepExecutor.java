package org.dirigent.executor;

import org.dirigent.metafacade.IGeneratable;
import org.dirigent.pattern.IPatternStep;

public interface IStepExecutor {
	public void execute(IGeneratable gen, IPatternStep step);
}
