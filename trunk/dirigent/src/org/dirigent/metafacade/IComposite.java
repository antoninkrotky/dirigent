package org.dirigent.metafacade;

import java.util.Collection;

/**
 * Composite metafacade.
 * */
public interface IComposite {
	public Collection<IElement> getChildElements();
}
