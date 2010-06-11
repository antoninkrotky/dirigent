package org.dirigent.metafacade.builder;

import java.util.Collection;
import java.util.Vector;

import org.dirigent.config.DirigentConfig;
import org.dirigent.metafacade.IAttribute;
import org.dirigent.metafacade.IElement;
import org.dirigent.metafacade.builder.csv.CsvMetafacadeBuilder;
import org.dirigent.metafacade.builder.ea.EAMetafacadeBuilder;
import org.dirigent.metafacade.builder.vo.DomainVO;
import org.dirigent.metafacade.builder.vo.ObjectVO;

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
	 * Get attributes for specified element.
	 * */
	public abstract Collection<IAttribute> getAttributes(String elementURI);

	/**
	 * Save metafacede to model.
	 * */
	public abstract void save(IElement element);

	public abstract Collection<DomainVO> getDomains();

	public abstract Vector<IElement> getChildElements(String uri);
	
	@Deprecated
	public abstract Collection<ObjectVO> getChildObjects(String uri) ;

}
