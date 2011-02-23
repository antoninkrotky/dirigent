package org.dirigent.metafacade.builder.ea.decorator;

import java.util.Map;

import org.dirigent.metafacade.builder.decorator.ElementDecorator;
import org.dirigent.metafacade.builder.ea.dao.EAObjectPropertyDAO;
import org.dirigent.metafacade.builder.ea.vo.EAElementVO;
import org.dirigent.metafacade.builder.vo.ElementVO;
import org.dirigent.metafacade.builder.vo.ILazyLoader;

public class EAElementDecorator extends ElementDecorator {

	public EAElementDecorator(EAElementVO ea) {
		super(init(ea,new ElementVO()));
	}
	
	public static ElementVO init(final EAElementVO ea,ElementVO v) {		
		v.uri=ea.guid;
		v.id=ea.objectId;
		v.name=ea.name;
		v.type=ea.type;
		v.description=ea.note;
		v.stereotype=ea.stereotype;
		v.packageId=ea.packageId;		
		v.parentUri=ea.parentGuid;
		v.alias = ea.alias;
		v.keywords = ea.keywords;
		v.setPropertiesLazyLoader(new ILazyLoader<Map<String,String>>() {		
			@Override
			public Map<String, String> load() {
				return new EAObjectPropertyDAO().getObjectProperties(ea.objectId);
			}
		});
		return v;
	}

}
