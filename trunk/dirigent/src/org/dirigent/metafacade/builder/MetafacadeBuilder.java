package org.dirigent.metafacade.builder;

import java.util.Collection;

import org.dirigent.config.DirigentConfig;
import org.dirigent.metafacade.IElement;
import org.dirigent.metafacade.builder.csv.CsvMetafacadeBuilder;
import org.dirigent.metafacade.builder.ea.EAMetafacadeBuilder;
import org.dirigent.metafacade.builder.vo.DomainVO;

/**
 * Abstract MetafacadeBuilder factory.
 * */
public abstract class MetafacadeBuilder {

	
	
	/**
	 * Gets MetafacadeBuilder implementation.
	 * 
	 * @return MetafacadeBuilder implementation
	 * */
	public static MetafacadeBuilder getMetafacadeBuilder() {
		String modelType = DirigentConfig.getDirigentConfig().getProperty(
				"dirigent.model.type");
		if ("EA".equals(modelType)) {
			return new EAMetafacadeBuilder();
		} else {
			return new CsvMetafacadeBuilder();
		}
	}

	/**
	 * Create Metafacade from model.
	 * 
	 * @param uri
	 *            URI of model element to create metafacade from.
	 * */
	public abstract IElement getMetafacade(String uri);

	/**
	 * Save metafacede to model.
	 * */
	public abstract void save(IElement element);

	public abstract Collection<DomainVO> getDomains();

}
