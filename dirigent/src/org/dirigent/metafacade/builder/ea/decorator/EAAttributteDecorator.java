package org.dirigent.metafacade.builder.ea.decorator;

import org.dirigent.metafacade.IElement;
import org.dirigent.metafacade.builder.MetafacadeBuilder;
import org.dirigent.metafacade.builder.decorator.AttributeDecorator;
import org.dirigent.metafacade.builder.ea.dao.EAAttributeTagDAO;
import org.dirigent.metafacade.builder.ea.dao.EAObjectDao;
import org.dirigent.metafacade.builder.ea.vo.EAAttributeVO;
import org.dirigent.metafacade.builder.vo.AttributeVO;
import org.dirigent.metafacade.builder.vo.ObjectVO;

public class EAAttributteDecorator extends AttributeDecorator {
	private EAAttributeVO eaAttribute;
	private IElement classifier;
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
		v.initialValue=ea.initialValue;
		return v;
	}
	
	
	
	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.builder.decorator.AttributeDecorator#getClassifier()
	 */
	@Override
	public IElement getClassifier() {
		//We need one more query to get classifier URI (ea_guid) so override lazy loading here.
		if (classifier==null) {
			if (eaAttribute.classifier>0) {
				ObjectVO o=new EAObjectDao().getObjectById(eaAttribute.classifier);
				if (o!=null) {
					String classifierURI=o.uri;
					classifier=MetafacadeBuilder.getMetafacadeBuilder().getMetafacade(classifierURI);
				}
			} else {
				
			}
		}
		return classifier;
	}
}
