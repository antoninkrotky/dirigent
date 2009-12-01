package org.dirigent.metafacade.builder;

import org.dirigent.metafacade.Element;
/**
 * Abstract MetafacadeBuilder factory.
 * */
public abstract class MetafacadeBuilder {

	/**
	 * Gets MetafacadeBuilder implementation. Currently CSVMetafacadeBuilder is the only implementation.
	 * @return MetafacadeBuilder implementation
	 * */
	public static MetafacadeBuilder getMetafacadeBuilder(){
		return new CSVMetafacadeBuilder();
	}
	
	/**
	 * Create Metafacade from model.
	 * @param uri URI of model element to create metafacade from.
	 * */
	public abstract Element getMetafacade(String uri);
	
}
