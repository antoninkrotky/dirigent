package org.dirigent.metafacade;

import org.dirigent.metafacade.builder.vo.VO;

public interface IElement {
	public String getUri();
	public String getName();
	public VO getValueObject();
}
