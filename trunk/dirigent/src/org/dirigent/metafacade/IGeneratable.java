package org.dirigent.metafacade;

import org.dirigent.pattern.IPattern;

/**
 * Generatable metafacade. Generatable metafacade has assigned Pattern and thus can be generated using Generator class.
 * */
public interface IGeneratable extends IElement {
	public IPattern getPattern();
}
