package org.dirigent.metafacade.builder;

import java.util.Collection;
import java.util.Vector;

import org.dirigent.config.DirigentConfig;
import org.dirigent.metafacade.IElement;
import org.dirigent.metafacade.IRelation;
import org.dirigent.metafacade.builder.classloader.ClassloaderMetafacadeBuilder;
import org.dirigent.metafacade.builder.csv.CsvMetafacadeBuilder;
import org.dirigent.metafacade.builder.ea.EAMetafacadeBuilder;
import org.dirigent.metafacade.builder.mm.MMMetafacadeBuilder;
import org.dirigent.metafacade.builder.odi.ODI10gMetafacadeBuilder;
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
				"dirigent.model.type","CSV");
		if ("EA".equals(modelType)) {
			return new EAMetafacadeBuilder();
		} else if ("CLASSLOADER".equals(modelType)) {
			return new ClassloaderMetafacadeBuilder();
		} else if ("MetaMart".equals(modelType)) {
			return new MMMetafacadeBuilder();
		} else if ("ODI".equals(modelType)) {
			return new ODI10gMetafacadeBuilder();
		} else if ("CSV".equals(modelType)){
			return new CsvMetafacadeBuilder();
		} else {
			try {
				return (MetafacadeBuilder)Class.forName(modelType).newInstance();
			} catch (Exception e) {
				throw new RuntimeException("Unable to create MetafacadeBuilder of type: "+modelType);
			}
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

	public abstract Vector<IElement> getChildElements(String uri);

	public abstract void clearCache();

	@Deprecated
	public abstract Collection<ObjectVO> getChildObjects(String uri);

	/**
	 * Get all relations starting in specified element.
	 * */
	public abstract Collection<IRelation> getStartingRelations(String elementUri);

	/**
	 * Get all connectors ending in specified element.
	 * */
	public abstract Collection<IRelation> getEndingRelations(String elementUri);

}
