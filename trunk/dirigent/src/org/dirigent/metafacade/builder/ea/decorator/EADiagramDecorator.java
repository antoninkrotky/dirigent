package org.dirigent.metafacade.builder.ea.decorator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.dirigent.config.DirigentConfig;
import org.dirigent.metafacade.IAttribute;
import org.dirigent.metafacade.IDiagram;
import org.dirigent.metafacade.IElement;
import org.dirigent.metafacade.IRelation;
import org.dirigent.metafacade.builder.MetafacadeBuilder;
import org.dirigent.metafacade.builder.ea.dao.EAObjectDao;
import org.dirigent.metafacade.builder.ea.vo.EADiagramVO;
import org.dirigent.metafacade.builder.vo.ObjectVO;
import org.dirigent.metafacade.builder.vo.VO;
import org.dirigent.pattern.IPattern;
import org.dirigent.pattern.builder.PatternBuilder;

public class EADiagramDecorator implements IDiagram {

	private EADiagramVO diagram;
	private static Logger l=Logger.getLogger(EADiagramDecorator.class.getName());
	private ArrayList<IElement> childElements;

	public EADiagramDecorator(EADiagramVO d) {
		this.diagram = d;
	}

	@Override
	public IPattern getPattern() {
		String pattern=null;
 		if (pattern==null) {
 			String confPattern=DirigentConfig.DEFAULT_PATTERN_DIAGRAM;
 			if (getStereotype()!=null) {
 				confPattern=confPattern+ "." + getStereotype().toLowerCase();
 				pattern=DirigentConfig.getDirigentConfig().getProperty(confPattern);
 				if (pattern==null) {
 					pattern=DirigentConfig.getDirigentConfig().getProperty(DirigentConfig.DEFAULT_PATTERN_DIAGRAM); 				}
 			} else {
 				pattern=DirigentConfig.getDirigentConfig().getProperty(confPattern);
 			}
 			if (pattern==null) {
 				EADiagramDecorator.l.log(Level.WARNING, "Element " + getName() + " skipped. Pattern definition missing in configuration file for pattern " + confPattern);
 				return null;	
 			}
  		}		
		return PatternBuilder.getPatternBuilder().getPattern(
					pattern + ".pattern.xml");
	}
	
	@Override
	public Collection<IAttribute> getAttributes() {
		return null;
	}

	@Override
	public String getName() {
		return diagram.name;
	}

	@Override
	public Map<String, String> getProperties() {
		return null;
	}

	@Override
	public String getStereotype() {
		return diagram.stereotype;
	}

	@Override
	public String getUri() {
		return diagram.ea_guid;
	}

	@Override
	public VO getValueObject() {
		return null;
	}

	@Override
	public Collection<IElement> getChildElements() {
		if (childElements == null) {
			EAObjectDao objectDao = new EAObjectDao();
			Iterator<ObjectVO> i = objectDao.getDiagramObjects(
					diagram.diagramId).iterator();
			childElements = new ArrayList<IElement>();
			while (i.hasNext()) {
				ObjectVO o = i.next();
				childElements.add(MetafacadeBuilder.getMetafacadeBuilder().getMetafacade(
						o.uri));
			}
		}
		return childElements;
	}


	
	@Override
	public IElement getParent() {
		return null;
	}

	@Override
	public Collection<IRelation> getEndingRelations() {
		// TODO ASK KAREL !!!! Auto-generated method stub
		return null;
	}

	@Override
	public Collection<IRelation> getStartingRelations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAlias() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getKeywords() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getType() {
		return "Diagram";
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.IElement#getFirstStartingRelation(java.lang.String, java.lang.String)
	 */
	@Override
	public IRelation getFirstStartingRelation(String type, String stereotype) {
		// TODO Auto-generated method stub
		return null;
	}

}
