package org.dirigent.metafacade.builder.ea.decorator;

import org.dirigent.metafacade.builder.decorator.AttributeDecorator;
import org.dirigent.metafacade.builder.ea.dao.EAAttributeTagDAO;
import org.dirigent.metafacade.builder.ea.vo.EAAttributeVO;
import org.dirigent.metafacade.builder.vo.AttributeVO;

public class EAAttributteDecorator extends AttributeDecorator {
	private EAAttributeVO eaAttribute;
	public EAAttributteDecorator(EAAttributeVO ea) {
		super(init(ea,new AttributeVO()));
		eaAttribute=ea;
	}

	public EAAttributeVO getEAAttribute() {
		return eaAttribute;
	}
	public static AttributeVO init(EAAttributeVO ea, AttributeVO v) {
		v.name=ea.name;
		v.uri=ea.ea_guid;
		v.description=ea.notes;
		v.id=ea.id;
		v.type=ea.type;
		v.stereotype=ea.stereotype;
		v.properties=new EAAttributeTagDAO().getObjectProperties(v.id);		
		return v;
	}
}
