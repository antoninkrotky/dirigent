package org.dirigent.metafacade;

import java.util.Map;

import org.dirigent.metafacade.builder.vo.VO;

public interface IRelation {
	public String getUri();
	public String getName();
	public String getType();
	public String getStereotype();
	public String getDescription();
	public Map<String, String> getProperties();
	public String getAlias();
	public String getStartElementUri();
	public String getEndElementUri();	
	public IElement getStartElement();
	public IElement getEndElement();
	public VO getValueObject();
	//TODO Ask Karel
	public int getLineColor();
}
