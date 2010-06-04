package org.dirigent.metafacade;

import java.util.Collection;

public interface IPackage extends IElement, IGeneratable	{
	public Collection<IElement> getChildElements();

}
