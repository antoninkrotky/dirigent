package org.dirigent.metafacade;

import java.util.Collection;

public interface Pattern {
	public String getName();
	public Collection<PatternStep> getSteps();
}
