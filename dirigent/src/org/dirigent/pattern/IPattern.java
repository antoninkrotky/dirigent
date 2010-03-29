package org.dirigent.pattern;

import java.util.Collection;

public interface IPattern {
	public String getName();
	public Collection<IPatternStep> getSteps();
}
