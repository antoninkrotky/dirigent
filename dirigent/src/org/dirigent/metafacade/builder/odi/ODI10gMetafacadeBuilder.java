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
package org.dirigent.metafacade.builder.odi;

import java.util.Collection;
import java.util.Vector;

import org.dirigent.metafacade.IElement;
import org.dirigent.metafacade.IRelation;
import org.dirigent.metafacade.builder.MetafacadeBuilder;
import org.dirigent.metafacade.builder.odi.decorator.ODIMappingDecorator;
import org.dirigent.metafacade.builder.odi.v10g.dao.SnpPopDao;
import org.dirigent.metafacade.builder.odi.vo.ODIMappingVO;
import org.dirigent.metafacade.builder.vo.ObjectVO;

/**
 * @author khubl
 *
 */
public class ODI10gMetafacadeBuilder extends MetafacadeBuilder {

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.builder.MetafacadeBuilder#getMetafacade(java.lang.String)
	 */
	@Override
	public IElement getMetafacade(String uri) {
		if (uri.startsWith("POP_")) {
			ODIMappingVO m=new SnpPopDao().findByUri(uri);
			return new ODIMappingDecorator(m);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.builder.MetafacadeBuilder#save(org.dirigent.metafacade.IElement)
	 */
	@Override
	public void save(IElement element) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.builder.MetafacadeBuilder#getChildElements(java.lang.String)
	 */
	@Override
	public Vector<IElement> getChildElements(String uri) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.builder.MetafacadeBuilder#clearCache()
	 */
	@Override
	public void clearCache() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.builder.MetafacadeBuilder#getChildObjects(java.lang.String)
	 */
	@Override
	public Collection<ObjectVO> getChildObjects(String uri) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.builder.MetafacadeBuilder#getStartingRelations(java.lang.String)
	 */
	@Override
	public Collection<IRelation> getStartingRelations(String elementUri) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.builder.MetafacadeBuilder#getEndingRelations(java.lang.String)
	 */
	@Override
	public Collection<IRelation> getEndingRelations(String elementUri) {
		// TODO Auto-generated method stub
		return null;
	}

}
