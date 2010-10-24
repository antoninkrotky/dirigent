package org.dirigent.metafacade.builder.decorator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.dirigent.config.DirigentConfig;
import org.dirigent.metafacade.IAttribute;
import org.dirigent.metafacade.IElement;
import org.dirigent.metafacade.IGeneratable;
import org.dirigent.metafacade.IRelation;
import org.dirigent.metafacade.builder.MetafacadeBuilder;
import org.dirigent.metafacade.builder.vo.ElementVO;
import org.dirigent.metafacade.builder.vo.VO;
import org.dirigent.pattern.IPattern;
import org.dirigent.pattern.builder.PatternBuilder;

public class ElementDecorator implements IElement, IGeneratable {
	private static Logger l = Logger.getLogger(ElementDecorator.class.getName());
	
	private ElementVO element;
	private Collection<IRelation> startingRelations;
	private Collection<IRelation> endingRelations;
	
	
	public ElementDecorator(ElementVO v) {
		this.element=v;
	}
	
	@Override
	public String getName() {
		return element.name;
	}

	@Override
	public Map<String, String> getProperties() {
		return element.properties;
	}

	@Override
	public String getUri() {
		return element.uri;
	}

	@Override
	public VO getValueObject() {
		return element;
	}

	@Override
	public String getStereotype() {		
		return element.stereotype;
	}

	private Collection<IAttribute> attributes;
	
	@Override
	public Collection<IAttribute> getAttributes() {
		//Lazy loading
		if (attributes==null) {
			attributes=new ArrayList<IAttribute>(MetafacadeBuilder.getMetafacadeBuilder().getAttributes(element.uri));
		}
		return attributes;
	}
	
	@Override
	public IElement getParent() {
		if ( ! element.parentUri.equals("") ) {
 			return MetafacadeBuilder.getMetafacadeBuilder().getMetafacade(element.parentUri);
		} else {
			l.warning("element " + element.name + " (" + element.uri + ") don't have any parent!");
			return null;
		}
	}
	
	@Override
	public IPattern getPattern() {				
 		String pattern=element.properties.get("pattern");
 		if (pattern==null) {
 			String confPattern=DirigentConfig.DEFAULT_PATTERN_ELEMENT;
 			if (getStereotype()!=null) {
 				confPattern=confPattern+ "." + getStereotype().toLowerCase();
 			}
 			pattern=DirigentConfig.getDirigentConfig().getProperty(confPattern); 			
 			if (pattern==null) {
 				l.log(Level.WARNING, "Element " + getName() + " skipped. Pattern definition missing in configuration file for pattern " + confPattern);
 				return null;	
 			}
  		}		
		return PatternBuilder.getPatternBuilder().getPattern(
					pattern + ".pattern.xml");
	}

	@Override
	public Collection<IRelation> getEndingRelations() {
		// Lazy load
		if(endingRelations == null){
			endingRelations = MetafacadeBuilder.getMetafacadeBuilder().getEndingRelations(getUri());
		}
		return endingRelations;
	}

	@Override
	public Collection<IRelation> getStartingRelations() {
		if(startingRelations == null){
			startingRelations = MetafacadeBuilder.getMetafacadeBuilder().getStartingRelations(getUri());
		}
		return startingRelations;
	}

	@Override
	public String getDescription() {
		return element.description;
	}

	@Override
	public String getAlias() {
		return element.alias;
	}
	
	@Override
	public String toString() {	
		return getName()+((getStereotype()!=null)?("<"+getStereotype()+">"):"")+" ["+getClass().getName()+"]";
	}
	
}
