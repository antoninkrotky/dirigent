package org.dirigent.metafacade;

import java.util.Collection;
import java.util.Map;

import org.dirigent.metafacade.builder.vo.VO;

public interface IElement {
	public String getUri();
	public String getName();
	public String getStereotype();
	public Map<String, String> getProperties();
	public Collection<IAttribute> getAttributes();
	public VO getValueObject();
	public IElement getParent();
}
