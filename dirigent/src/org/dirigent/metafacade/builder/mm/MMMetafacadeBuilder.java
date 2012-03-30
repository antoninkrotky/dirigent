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
package org.dirigent.metafacade.builder.mm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;

import org.dirigent.metafacade.IElement;
import org.dirigent.metafacade.IRelation;
import org.dirigent.metafacade.builder.MetafacadeBuilder;
import org.dirigent.metafacade.builder.mm.dao.ArtefactDAO;
import org.dirigent.metafacade.builder.mm.dao.ConnectorDAO;
import org.dirigent.metafacade.builder.mm.decorator.MMArtefactDecorator;
import org.dirigent.metafacade.builder.mm.decorator.MMRelationDecorator;
import org.dirigent.metafacade.builder.mm.vo.ArtefactVO;
import org.dirigent.metafacade.builder.mm.vo.ConnectorVO;
import org.dirigent.metafacade.builder.vo.ObjectVO;

/**
 * @author khubl
 *
 */
public class MMMetafacadeBuilder extends MetafacadeBuilder {

	private ArtefactDAO artefactDao=new ArtefactDAO();
	private ConnectorDAO connectorDao=new ConnectorDAO();
	
	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.builder.MetafacadeBuilder#getMetafacade(java.lang.String)
	 */
	@Override
	public IElement getMetafacade(String uri) {
		if (uri==null) {
			return null;
		}
		return new MMArtefactDecorator(artefactDao.findByUri(uri));		
	}
	
	public IElement getMetafacade(long key) {
		//return new MMArtefactDecorator(artefactDao.findByKey(key));
		return null;
	}
	

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.builder.MetafacadeBuilder#save(org.dirigent.metafacade.IElement)
	 */
	@Override
	public void save(IElement element) {
		throw new RuntimeException("Not implemented.");
		
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.builder.MetafacadeBuilder#getChildElements(java.lang.String)
	 */
	@Override
	public Vector<IElement> getChildElements(String uri) {
		Vector<IElement> res=new Vector<IElement>();
		for (ArtefactVO a : artefactDao.findByParentUri(uri)) {
			res.add(new MMArtefactDecorator(a));			
		}
		return res;
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
		return null;
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.builder.MetafacadeBuilder#getStartingRelations(java.lang.String)
	 */
	@Override
	public Collection<IRelation> getStartingRelations(String elementUri) {
		Collection<ConnectorVO> c=connectorDao.findStartingRelations(elementUri);
		ArrayList<IRelation> r=new ArrayList<IRelation>(c.size());
		for (ConnectorVO connectorVO : c) {
			r.add(new MMRelationDecorator(connectorVO));
		}
		return r;
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.builder.MetafacadeBuilder#getEndingRelations(java.lang.String)
	 */
	@Override
	public Collection<IRelation> getEndingRelations(String elementUri) {
		Collection<ConnectorVO> c=connectorDao.findEndingRelations(elementUri);
		ArrayList<IRelation> r=new ArrayList<IRelation>(c.size());
		for (ConnectorVO connectorVO : c) {
			r.add(new MMRelationDecorator(connectorVO));
		}
		return r;
	}

}
