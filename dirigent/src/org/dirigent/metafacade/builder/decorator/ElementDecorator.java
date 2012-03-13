package org.dirigent.metafacade.builder.decorator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.dirigent.config.DirigentConfig;
import org.dirigent.metafacade.IElement;
import org.dirigent.metafacade.IGeneratable;
import org.dirigent.metafacade.IRelation;
import org.dirigent.metafacade.builder.MetafacadeBuilder;
import org.dirigent.metafacade.builder.vo.ElementVO;
import org.dirigent.metafacade.builder.vo.VO;
import org.dirigent.pattern.IPattern;
import org.dirigent.pattern.builder.PatternBuilder;

public abstract class ElementDecorator implements IElement, IGeneratable {
	private static Logger l = Logger.getLogger(ElementDecorator.class.getName());
	
	private ElementVO element;
	private Collection<IRelation> startingRelations;
	private Collection<IRelation> endingRelations;
	
	

	public abstract IElement getGeneralizedParent();
	
	public ElementDecorator(ElementVO v) {
		this.element=v;
	}
	
	@Override
	public String getName() {
		return element.name;
	}

	@Override
	public Map<String, String> getProperties() {
		return element.getProperties();
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
		String pattern=null;
 		if ("true".equals(DirigentConfig.getDirigentConfig().getProperty(DirigentConfig.PATTERN_OVERRIDE,"true"))) {
 			pattern=element.getProperties().get("pattern");
 			//Try to inherit pattern from generalized parent.
 			if (pattern==null) {
 				IElement g=this.getGeneralizedParent();
 				if (g!=null && g instanceof IGeneratable) {
 					IPattern p=((IGeneratable)g).getPattern();
 					if (p!=null) {
 						return p;
 					}
 				}
 			}
 		}
 		if (pattern==null) {
 			String confPattern=DirigentConfig.DEFAULT_PATTERN_ELEMENT;
 			if (getStereotype()!=null) {
 				confPattern=confPattern+ "." + getStereotype().toLowerCase();
 				pattern=DirigentConfig.getDirigentConfig().getProperty(confPattern);
 				if (pattern==null) {
 					pattern=DirigentConfig.getDirigentConfig().getProperty(DirigentConfig.DEFAULT_PATTERN_ELEMENT);
 				}
 			} else {
 				pattern=DirigentConfig.getDirigentConfig().getProperty(confPattern);
 			}
 			if (pattern==null) {
 				ElementDecorator.l.log(Level.WARNING, "Element " + getName() + " skipped. Pattern definition missing in configuration file for pattern " + confPattern);
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

	private Map<String, IRelation> inheritedStartingrelationMap;
	
	protected Map<String, IRelation> getStartingInheritedRelationMap() {
		if (inheritedStartingrelationMap==null) {
			inheritedStartingrelationMap=new HashMap<String, IRelation>();
			if (getGeneralizedParent()!=null) {
				//add inherited relations to map
				Iterator<IRelation> i=getGeneralizedParent().getStartingRelations(true).iterator();
				while (i.hasNext()) {
					IRelation r=i.next();
					inheritedStartingrelationMap.put(r.getName(), r);
				}
			}
			//add element relations to map
			Iterator<IRelation> i=getStartingRelations().iterator();
			while (i.hasNext()) {
				IRelation r=i.next();
				inheritedStartingrelationMap.put(r.getName(), r);
			}			
		}
		return inheritedStartingrelationMap;
	}
	
	public Collection<IRelation> getStartingRelations(boolean inheritRelations) {
		if (!inheritRelations || getGeneralizedParent()==null) {
			return getStartingRelations();
		}		
		TreeSet<IRelation> res=new TreeSet<IRelation>(new RelationComparator());
		res.addAll(getStartingInheritedRelationMap().values());
		return res;
	}
	
	@Override
	public Collection<IRelation> getStartingRelations() {
		if(startingRelations == null){
			startingRelations = new TreeSet<IRelation>(new RelationComparator());
			startingRelations.addAll(MetafacadeBuilder.getMetafacadeBuilder().getStartingRelations(getUri()));
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
	public String getKeywords() {
		return element.keywords;
	}
	
	@Override
	public String toString() {	
		return getName()+((getStereotype()!=null)?("<"+getStereotype()+">"):"")+" ["+getClass().getName()+"]";
	}

	@Override
	public String getType() {
		return element.type;
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.IElement#getFirstStartingRelation(java.lang.String, java.lang.String)
	 */
	@Override
	public IRelation getFirstStartingRelation(String type, String stereotype) {
		Collection<IRelation> c=getStartingRelations();
		if (c!=null) {
			for (IRelation iRelation : c) {
				if ((type==null || type.equals(iRelation.getType())) && (stereotype==null || stereotype.equals(iRelation.getStereotype()))) {
					return iRelation;
				}
			}
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.IElement#getStartingRelations(java.lang.String, java.lang.String, boolean)
	 */
	@Override
	public Collection<IRelation> getStartingRelations(String type,
			String stereotype, boolean includeGeneralizedRelations) {
		Collection<IRelation> res=new ArrayList<IRelation>();
		Collection<IRelation> c=getStartingRelations();
		if (c!=null) {
			for (IRelation iRelation : c) {
				if ((type==null || type.equals(iRelation.getType())) && (stereotype==null || stereotype.equals(iRelation.getStereotype()))) {
					res.add(iRelation);
				}
			}
		}
		return res;
	}
	
	
	
	public String getStatus() {
		if (element.status!=null) {
			return element.status;
		} else if (getParent()!=null) {
			return getParent().getStatus();
		}
		return null;
	}

}
