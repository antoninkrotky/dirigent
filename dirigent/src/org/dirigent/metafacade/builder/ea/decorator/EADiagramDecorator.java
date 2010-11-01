package org.dirigent.metafacade.builder.ea.decorator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

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
	private ArrayList<IElement> childElements;

	public EADiagramDecorator(EADiagramVO d) {
		this.diagram = d;
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
	public IPattern getPattern() {
		return PatternBuilder.getPatternBuilder().getPattern(
				DirigentConfig.getDirigentConfig().getProperty(
						DirigentConfig.DEFAULT_PATTERN_DIAGRAM)
						+ ".pattern.xml");
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

}
