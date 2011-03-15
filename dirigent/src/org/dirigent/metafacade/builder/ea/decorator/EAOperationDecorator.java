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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import org.dirigent.metafacade.IElement;
import org.dirigent.metafacade.IOperation;
import org.dirigent.metafacade.IParameter;
import org.dirigent.metafacade.builder.MetafacadeBuilder;
import org.dirigent.metafacade.builder.ea.dao.EAObjectDao;
import org.dirigent.metafacade.builder.ea.dao.EAOperationTagDAO;
import org.dirigent.metafacade.builder.ea.dao.EAParameterDAO;
import org.dirigent.metafacade.builder.ea.vo.EAOperationVO;
import org.dirigent.metafacade.builder.ea.vo.EAParameterVO;
import org.dirigent.metafacade.builder.vo.ObjectVO;

/**
 * @author khubl
 * 
 */
public class EAOperationDecorator implements IOperation {

	private EAOperationVO v;

	public EAOperationDecorator(EAOperationVO operation) {
		this.v = operation;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dirigent.metafacade.IOperation#getName()
	 */
	@Override
	public String getName() {
		return v.name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dirigent.metafacade.IOperation#getStereotype()
	 */
	@Override
	public String getStereotype() {
		return v.stereotype;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dirigent.metafacade.IOperation#getDescription()
	 */
	@Override
	public String getDescription() {
		return v.notes;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dirigent.metafacade.IOperation#getReturnType()
	 */
	@Override
	public String getReturnType() {
		return v.type;
	}

	private IElement classifier;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dirigent.metafacade.IOperation#getReturnClassifier()
	 */
	@Override
	public IElement getReturnClassifier() {
		// We need one more query to get classifier URI (ea_guid) so override
		// lazy loading here.
		if (classifier == null) {
			if (v.classifier != null) {
				long classifierId = Long.parseLong(v.classifier);
				if (classifierId > 0) {
					ObjectVO o = new EAObjectDao().getObjectById(classifierId);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dirigent.metafacade.IOperation#getProperties()
	 */
	@Override
	public Map<String, String> getProperties() {
		if (properties == null) {
			properties = new EAOperationTagDAO().getObjectProperties(v.id);
		}
		return properties;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dirigent.metafacade.IOperation#isReturningArray()
	 */
	@Override
	public boolean isReturningArray() {
		return "1".equals(v.returnArray);
	}

	private Collection<IParameter> parameters;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dirigent.metafacade.IOperation#getParameters()
	 */
	@Override
	public Collection<IParameter> getParameters() {
		if (parameters == null) {
			parameters = new ArrayList<IParameter>();
			Iterator<EAParameterVO> i = new EAParameterDAO()
					.getParameters(v.id).iterator();
			while (i.hasNext()) {
				parameters.add(new EAParameterDecorator(i.next()));
			}
		}
		return parameters;
	}

}
