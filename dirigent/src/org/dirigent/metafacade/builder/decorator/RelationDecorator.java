package org.dirigent.metafacade.builder.decorator;

import java.util.Map;

import org.dirigent.metafacade.IElement;
import org.dirigent.metafacade.IRelation;
import org.dirigent.metafacade.builder.MetafacadeBuilder;
import org.dirigent.metafacade.builder.vo.RelationVO;
import org.dirigent.metafacade.builder.vo.VO;

public class RelationDecorator implements IRelation {

	private RelationVO relation;
	private IElement startElement;
	private IElement endElement;

	public RelationDecorator(RelationVO vo) {
		this.relation = vo;
	}

	@Override
	public String getName() {
		return relation.name;
	}

	@Override
	public Map<String, String> getProperties() {
		return relation.properties;
	}

	@Override
	public String getStereotype() {

		return relation.stereotype;
	}

	@Override
	public String getUri() {
		return relation.uri;
	}

	@Override
	public VO getValueObject() {
		return relation;
	}

	@Override
	public String getDescription() {
		return relation.description;
	}

	@Override
	public IElement getEndElement() {
		// Lazy load
		if (endElement == null) {
			endElement = MetafacadeBuilder.getMetafacadeBuilder().getMetafacade(
					relation.endElementUri);
		}
		return endElement;
	}

	@Override
	public IElement getStartElement() {
		// Lazy load
		if (startElement == null) {
			startElement = MetafacadeBuilder.getMetafacadeBuilder()
					.getMetafacade(relation.startElementUri);
		}
		return startElement;
	}

	@Override
	public String getType() {
		return relation.type;
	}

	public int getLineColor(){
		return relation.lineColor; 
	}

	@Override
	public String getAlias() {
		
		return relation.alias;
	}
	
}
