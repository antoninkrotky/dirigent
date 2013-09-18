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
package org.dirigent.metafacade.builder.classloader;

import java.util.Collection;
import java.util.Map;

import org.dirigent.metafacade.IElement;
import org.dirigent.metafacade.IPackage;
import org.dirigent.metafacade.IRelation;
import org.dirigent.metafacade.builder.vo.VO;
import org.dirigent.pattern.IPattern;

/**
 * @author Karel
 *
 */
public class PackageDecorator implements IPackage {

	
	private Package modelPackage;
	
	
	/**
	 * @param modelPackage
	 */
	public PackageDecorator(Package modelPackage) {
		super();
		this.modelPackage = modelPackage;
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.IElement#getUri()
	 */
	@Override
	public String getUri() {
		// TODO Auto-generated method stub
		return modelPackage.getName();
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.IElement#getName()
	 */
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return modelPackage.getName();
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.IElement#getType()
	 */
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.IElement#getStereotype()
	 */
	@Override
	public String getStereotype() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.IElement#getProperties()
	 */
	@Override
	public Map<String, String> getProperties() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.IElement#getValueObject()
	 */
	@Override
	public VO getValueObject() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.IElement#getParent()
	 */
	@Override
	public IElement getParent() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.IElement#getStartingRelations()
	 */
	@Override
	public Collection<IRelation> getStartingRelations() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.IElement#getFirstStartingRelation(java.lang.String, java.lang.String)
	 */
	@Override
	public IRelation getFirstStartingRelation(String type, String stereotype) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.IElement#getEndingRelations()
	 */
	@Override
	public Collection<IRelation> getEndingRelations() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.IElement#getDescription()
	 */
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.IElement#getAlias()
	 */
	@Override
	public String getAlias() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.IElement#getKeywords()
	 */
	@Override
	public String getKeywords() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.IGeneratable#getPattern()
	 */
	@Override
	public IPattern getPattern() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.IComposite#getChildElements()
	 */
	@Override
	public Collection<IElement> getChildElements() {
		// TODO Auto-generated method stub
		return null;
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
	 * @see org.dirigent.metafacade.IElement#getStartingRelations(boolean)
	 */
	@Override
	public Collection<IRelation> getStartingRelations(boolean inherit) {
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

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.IElement#getStatus()
	 */
	@Override
	public String getStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
