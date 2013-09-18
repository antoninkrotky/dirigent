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
import java.util.Vector;

import org.dirigent.metafacade.IElement;
import org.dirigent.metafacade.IRelation;
import org.dirigent.metafacade.builder.MetafacadeBuilder;
import org.dirigent.metafacade.builder.vo.ObjectVO;

/**
 * @author Karel
 * 
 */
public class ClassloaderMetafacadeBuilder extends MetafacadeBuilder {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dirigent.metafacade.builder.MetafacadeBuilder#getMetafacade(java.
	 * lang.String)
	 */
	@Override
	public IElement getMetafacade(String uri) {
		String className=getClassName(uri);
		try {
			Class<?> type = Class.forName(className);
			return new ClassDecorator(type);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Model class "+className+" not found.",e);
		}
	}
	
	protected String getClassName(String uri) {
		String className=uri;
	
		if (isJavaSourcePath(className)) {
			className=className.substring(0,className.length()-".java".length());
			className=className.replace('/', '.');
		}		
		return className;
	}

	private boolean isJavaSourcePath(String uri) {
		return uri.endsWith(".java");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dirigent.metafacade.builder.MetafacadeBuilder#save(org.dirigent.
	 * metafacade.IElement)
	 */
	@Override
	public void save(IElement element) {
		throw new UnsupportedOperationException();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dirigent.metafacade.builder.MetafacadeBuilder#getChildElements(java
	 * .lang.String)
	 */
	@Override
	public Vector<IElement> getChildElements(String uri) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dirigent.metafacade.builder.MetafacadeBuilder#clearCache()
	 */
	@Override
	public void clearCache() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dirigent.metafacade.builder.MetafacadeBuilder#getChildObjects(java
	 * .lang.String)
	 */
	@Override
	@Deprecated
	public Collection<ObjectVO> getChildObjects(String uri) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dirigent.metafacade.builder.MetafacadeBuilder#getStartingRelations
	 * (java.lang.String)
	 */
	@Override
	public Collection<IRelation> getStartingRelations(String elementUri) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dirigent.metafacade.builder.MetafacadeBuilder#getEndingRelations(
	 * java.lang.String)
	 */
	@Override
	public Collection<IRelation> getEndingRelations(String elementUri) {
		// TODO Auto-generated method stub
		return null;
	}

}
