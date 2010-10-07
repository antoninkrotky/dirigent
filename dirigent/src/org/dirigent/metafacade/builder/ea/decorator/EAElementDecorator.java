package org.dirigent.metafacade.builder.ea.decorator;

import org.dirigent.metafacade.builder.decorator.ElementDecorator;
import org.dirigent.metafacade.builder.ea.dao.EAObjectPropertyDAO;
import org.dirigent.metafacade.builder.ea.vo.EAElementVO;
import org.dirigent.metafacade.builder.vo.ElementVO;

public class EAElementDecorator extends ElementDecorator {

	public EAElementDecorator(EAElementVO ea) {
		super(init(ea,new ElementVO()));
	}
	
	public static ElementVO init(EAElementVO ea,ElementVO v) {		
		v.uri=ea.guid;
		v.id=ea.objectId;
		v.name=ea.name;
		v.type=ea.type;
		v.description=ea.note;
		v.stereotype=ea.stereotype;
		v.packageId=ea.packageId;
		v.properties=new EAObjectPropertyDAO().getObjectProperties(ea.objectId);
		v.parentUri=ea.parentGuid;
		return v;
	}

}
