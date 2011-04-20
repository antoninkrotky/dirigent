package org.dirigent.metafacade.builder.ea.decorator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import org.dirigent.metafacade.IAttribute;
import org.dirigent.metafacade.IClass;
import org.dirigent.metafacade.IOperation;
import org.dirigent.metafacade.builder.decorator.ElementDecorator;
import org.dirigent.metafacade.builder.ea.dao.EAAttributeDAO;
import org.dirigent.metafacade.builder.ea.dao.EAObjectPropertyDAO;
import org.dirigent.metafacade.builder.ea.dao.EAOperationDAO;
import org.dirigent.metafacade.builder.ea.vo.EAAttributeVO;
import org.dirigent.metafacade.builder.ea.vo.EAElementVO;
import org.dirigent.metafacade.builder.ea.vo.EAOperationVO;
import org.dirigent.metafacade.builder.vo.ElementVO;
import org.dirigent.metafacade.builder.vo.ILazyLoader;

public class EAClassDecorator extends ElementDecorator implements IClass {

	public EAClassDecorator(EAElementVO ea) {
		super(init(ea, new ElementVO()));
	}

	public static ElementVO init(final EAElementVO ea, ElementVO v) {
		v.uri = ea.guid;
		v.id = ea.objectId;
		v.name = ea.name;
		v.type = ea.type;
		v.description = ea.note;
		v.stereotype = ea.stereotype;
		v.packageId = ea.packageId;
		v.parentUri = ea.parentGuid;
		v.alias = ea.alias;
		v.keywords = ea.keywords;
		v.setPropertiesLazyLoader(new ILazyLoader<Map<String, String>>() {
			@Override
			public Map<String, String> load() {
				return new EAObjectPropertyDAO()
						.getObjectProperties(ea.objectId);
			}
		});
		return v;
	}

	private Collection<IAttribute> attributes;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dirigent.metafacade.IClass#getAttributes()
	 */
	@Override
	public Collection<IAttribute> getAttributes() {
		if (attributes == null) {
			Collection<EAAttributeVO> c = new EAAttributeDAO()
					.getAttributes(super.getUri());
			attributes = new ArrayList<IAttribute>(c.size());
			Iterator<EAAttributeVO> i = c.iterator();
			while (i.hasNext()) {
				EAAttributeVO a = i.next();
				if ("column".equals(a.stereotype)) {
					attributes.add(new EAColumnDecorator(a));
				} else if ("BIColumn".equals(a.stereotype)) {
					attributes.add(new EADimensionColumnDecorator(a));
				} else if ("BIDimensionColumn".equals(a.stereotype)) {
					attributes.add(new EADimensionColumnDecorator(a));
				} else if ("BIMappingColumn".equals(a.stereotype)) {
					attributes.add(new EAColumnMappingDecorator(a));
				} else {
					attributes.add(new EAAttributteDecorator(a));
				}
			}
		}
		return attributes;
	}

	private Collection<IOperation> operations;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dirigent.metafacade.IClass#getOperations()
	 */
	@Override
	public Collection<IOperation> getOperations() {
		if (operations==null) {
			operations=new ArrayList<IOperation>();
			Iterator<EAOperationVO> i=new EAOperationDAO().getOperations(getUri()).iterator();
			while (i.hasNext()) {
				operations.add(new EAOperationDecorator(i.next()));
			}
		}
		return operations;
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.IClass#getOperations(java.lang.String)
	 */
	@Override
	public Collection<IOperation> getOperations(String stereotype) {
		ArrayList<IOperation> res=new ArrayList<IOperation>();
		for (Iterator<IOperation> iterator = getOperations().iterator(); iterator.hasNext();) {
			IOperation iOperation =  iterator.next();
			if (stereotype.equals(iOperation.getStereotype())) {
				res.add(iOperation);
			}
		}
		return res;
	}

}
