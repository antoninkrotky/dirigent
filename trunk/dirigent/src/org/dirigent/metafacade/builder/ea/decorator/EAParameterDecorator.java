/**
 *  This file is part of Dirigent - the MDA generator.
 *  Copyright (C) 2010  Karel Hubl http://dirigent.googlecode.com
 *
 *  Dirigent is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Dirigent is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU LesservGeneral Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.dirigent.metafacade.builder.ea.decorator;

import java.util.Map;

import org.dirigent.metafacade.IElement;
import org.dirigent.metafacade.IParameter;
import org.dirigent.metafacade.builder.MetafacadeBuilder;
import org.dirigent.metafacade.builder.ea.dao.EAObjectDao;
import org.dirigent.metafacade.builder.ea.dao.EATaggedValueDAO;
import org.dirigent.metafacade.builder.ea.vo.EAParameterVO;
import org.dirigent.metafacade.builder.vo.ObjectVO;

/**
 * @author khubl
 *
 */
public class EAParameterDecorator implements IParameter {

	
	private EAParameterVO v;
	private IElement classifier;
	
	public EAParameterDecorator(EAParameterVO parameter) {
		v=parameter;
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.IParameter#getName()
	 */
	@Override
	public String getName() {
		return v.name;
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.IParameter#getStereotype()
	 */
	@Override
	public String getStereotype() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.IParameter#getDescription()
	 */
	@Override
	public String getDescription() {
		return v.notes;
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.IParameter#getType()
	 */
	@Override
	public String getType() {
		return v.type;
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.IParameter#getClassifier()
	 */
	@Override
	public IElement getClassifier() {
		if (classifier == null) {
			if (v.classifier != null) {
				long classifierId = Long.parseLong(v.classifier);
				if (classifierId > 0) {
					ObjectVO o = new EAObjectDao()
							.getObjectById(classifierId);
					if (o != null) {
						String classifierURI = o.uri;
						classifier = MetafacadeBuilder.getMetafacadeBuilder()
								.getMetafacade(classifierURI);
					}
				}
			}
		}
		return classifier;
	}

	
	private Map<String, String> properties;
	
	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.IParameter#getProperties()
	 */
	@Override
	public Map<String, String> getProperties() {
		if (properties==null) {
			properties=new EATaggedValueDAO().getObjectProperties(v.ea_guid);
		}
		return properties;
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.IParameter#isArray()
	 */
	@Override
	public boolean isArray() {
		return "true".equals(getProperties().get("isArray"));
	}
}
