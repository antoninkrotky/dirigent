package org.dirigent.metafacade;

import java.util.Collection;

public interface Pattern extends Element {
	public Collection<PatternStep> getSteps();
}
