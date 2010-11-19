/*
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
 *  along with Dirigent.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.dirigent.metafacade.impl;

import org.dirigent.metafacade.IElement;

/**
 * Abstract implementation of {@link org.dirigent.metafacade.IElement}.
 * @author Karel Hubl
 *
 */
public abstract class AbstractElement implements IElement  {

	/**
	 * model element URI (unique resource identifier).
	 */	
	private String uri;
	
	/**
	* Flag indicating that lazy loading of uri was performed.
	*/
	private boolean uriLoadedFlag=false;
	/**
	 * model element type.
	 */	
	private String type;
	
	/**
	* Flag indicating that lazy loading of type was performed.
	*/
	private boolean typeLoadedFlag=false;
	/**
	 * model element stereotype.
	 */	
	private String stereotype;
	
	/**
	* Flag indicating that lazy loading of stereotype was performed.
	*/
	private boolean stereotypeLoadedFlag=false;
	/**
	 * model element name.
	 */	
	private String name;
	
	/**
	* Flag indicating that lazy loading of name was performed.
	*/
	private boolean nameLoadedFlag=false;


	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.IElement.getUri()
	 */
	@Override
	public final String getUri(){
		if (!uriLoadedFlag) {
			loadUri();
			uriLoadedFlag=true;
		}
		return uri;
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.IElement.getUri()	 
	 */
	@Override
	public final void setUri(String newUri){
		this.uri=newUri;
		uriLoadedFlag=true;
	}
	
	/**
	* Hook enabling implementation of lazy loading uri.
	*/
	protected void loadUri() {}
	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.IElement.getType()
	 */
	@Override
	public final String getType(){
		if (!typeLoadedFlag) {
			loadType();
			typeLoadedFlag=true;
		}
		return type;
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.IElement.getType()	 
	 */
	@Override
	public final void setType(String newType){
		this.type=newType;
		typeLoadedFlag=true;
	}
	
	/**
	* Hook enabling implementation of lazy loading type.
	*/
	protected void loadType() {}
	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.IElement.getStereotype()
	 */
	@Override
	public final String getStereotype(){
		if (!stereotypeLoadedFlag) {
			loadStereotype();
			stereotypeLoadedFlag=true;
		}
		return stereotype;
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.IElement.getStereotype()	 
	 */
	@Override
	public final void setStereotype(String newStereotype){
		this.stereotype=newStereotype;
		stereotypeLoadedFlag=true;
	}
	
	/**
	* Hook enabling implementation of lazy loading stereotype.
	*/
	protected void loadStereotype() {}
	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.IElement.getName()
	 */
	@Override
	public final String getName(){
		if (!nameLoadedFlag) {
			loadName();
			nameLoadedFlag=true;
		}
		return name;
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.IElement.getName()	 
	 */
	@Override
	public final void setName(String newName){
		this.name=newName;
		nameLoadedFlag=true;
	}
	
	/**
	* Hook enabling implementation of lazy loading name.
	*/
	protected void loadName() {}
}


