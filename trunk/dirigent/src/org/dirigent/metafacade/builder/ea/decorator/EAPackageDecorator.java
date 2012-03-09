package org.dirigent.metafacade.builder.ea.decorator;

import java.util.Collection;

import org.dirigent.metafacade.IElement;
import org.dirigent.metafacade.IRelation;
import org.dirigent.metafacade.builder.decorator.PackageDecorator;
import org.dirigent.metafacade.builder.ea.vo.EAElementVO;
import org.dirigent.metafacade.builder.vo.ElementVO;

public class EAPackageDecorator extends PackageDecorator {
	public EAPackageDecorator(EAElementVO ea) {
		super(init(ea,new ElementVO()));
	}

	private static ElementVO init(EAElementVO ea, ElementVO v) {
		EAClassDecorator.init(ea, v);
		//for package id is stored in PDATA1 field.
		if (ea.pdata1!=null) {
			v.id=Long.parseLong(ea.pdata1);
		}
		return v;
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.IElement#getGeneralizedParent()
	 */
	@Override
	public IElement getGeneralizedParent() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.IElement#getStartingRelations(java.lang.String, java.lang.String, boolean)
	 */
	@Override
	public Collection<IRelation> getStartingRelations(String type,
			String stereotype, boolean includeGeneralizedRelations) {
		// TODO Auto-generated method stub
		return null;
	}
}
