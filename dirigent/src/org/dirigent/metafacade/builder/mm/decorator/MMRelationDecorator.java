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
package org.dirigent.metafacade.builder.mm.decorator;

import java.util.Map;

import org.dirigent.metafacade.IElement;
import org.dirigent.metafacade.IRelation;
import org.dirigent.metafacade.builder.MetafacadeBuilder;
import org.dirigent.metafacade.builder.mm.vo.ConnectorVO;
import org.dirigent.metafacade.builder.vo.VO;

/**
 * @author khubl
 *
 */
public class MMRelationDecorator implements IRelation {

	
	private ConnectorVO connector;
	public MMRelationDecorator(ConnectorVO vo) {
		this.connector=vo;
	}
	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.IRelation#getUri()
	 */
	@Override
	public String getUri() {
		return connector.uri;
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.IRelation#getName()
	 */
	@Override
	public String getName() {
		return connector.name;
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.IRelation#getType()
	 */
	@Override
	public String getType() {
		return connector.typeName;
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.IRelation#getStereotype()
	 */
	@Override
	public String getStereotype() {
		return connector.typeName;
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.IRelation#getDescription()
	 */
	@Override
	public String getDescription() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.IRelation#getProperties()
	 */
	@Override
	public Map<String, String> getProperties() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.IRelation#getAlias()
	 */
	@Override
	public String getAlias() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.IRelation#getStartElementUri()
	 */
	@Override
	public String getStartElementUri() {
		return connector.sourceArtefactUri;
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.IRelation#getEndElementUri()
	 */
	@Override
	public String getEndElementUri() {
		return connector.targetArtefactUri;
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.IRelation#getStartElement()
	 */
	@Override
	public IElement getStartElement() {
		return MetafacadeBuilder.getMetafacadeBuilder().getMetafacade(getStartElementUri());
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.IRelation#getEndElement()
	 */
	@Override
	public IElement getEndElement() {
		return MetafacadeBuilder.getMetafacadeBuilder().getMetafacade(getEndElementUri());
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.IRelation#getValueObject()
	 */
	@Override
	public VO getValueObject() {
		return connector;
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.IRelation#getLineColor()
	 */
	@Override
	public int getLineColor() {
		return 0;
	}

}
