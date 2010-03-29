package org.dirigent.metafacade.builder;

import org.dirigent.metafacade.IElement;
import org.dirigent.metafacade.builder.csv.CsvMetafacadeBuilder;
/**
 * Abstract MetafacadeBuilder factory.
 * */
public abstract class MetafacadeBuilder {

	/**
	 * Gets MetafacadeBuilder implementation. Currently CSVMetafacadeBuilder is the only implementation.
	 * @return MetafacadeBuilder implementation
	 * */
	public static MetafacadeBuilder getMetafacadeBuilder(){
		return new CsvMetafacadeBuilder();
	}
	
	/**
	 * Create Metafacade from model.
	 * @param uri URI of model element to create metafacade from.
	 * */
	public abstract IElement getMetafacade(String uri);
	
}
