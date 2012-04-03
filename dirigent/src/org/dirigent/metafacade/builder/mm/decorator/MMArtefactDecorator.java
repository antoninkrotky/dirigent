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

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.dirigent.config.DirigentConfig;
import org.dirigent.metafacade.IComposite;
import org.dirigent.metafacade.IElement;
import org.dirigent.metafacade.IGeneratable;
import org.dirigent.metafacade.IRelation;
import org.dirigent.metafacade.builder.MetafacadeBuilder;
import org.dirigent.metafacade.builder.mm.vo.ArtefactVO;
import org.dirigent.metafacade.builder.vo.VO;
import org.dirigent.pattern.IPattern;
import org.dirigent.pattern.builder.PatternBuilder;

/**
 * @author khubl
 * 
 */
public class MMArtefactDecorator implements IElement, IComposite, IGeneratable, Comparable<MMArtefactDecorator> {

	private static Logger l = Logger.getLogger(MMArtefactDecorator.class
			.getName());
	private ArtefactVO artefact;

	public MMArtefactDecorator(ArtefactVO v) {
		artefact = v;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dirigent.metafacade.IElement#getUri()
	 */
	@Override
	public String getUri() {
		return artefact.uri;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dirigent.metafacade.IElement#getName()
	 */
	@Override
	public String getName() {
		return artefact.name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dirigent.metafacade.IElement#getType()
	 */
	@Override
	public String getType() {
		return artefact.typeName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dirigent.metafacade.IElement#getStereotype()
	 */
	@Override
	public String getStereotype() {
		return artefact.typeName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dirigent.metafacade.IElement#getProperties()
	 */
	@Override
	public Map<String, String> getProperties() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dirigent.metafacade.IElement#getValueObject()
	 */
	@Override
	public VO getValueObject() {
		return artefact;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dirigent.metafacade.IElement#getParent()
	 */
	@Override
	public IElement getParent() {
		return MetafacadeBuilder.getMetafacadeBuilder().getMetafacade(
				artefact.parentUri);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dirigent.metafacade.IElement#getGeneralizedParent()
	 */
	@Override
	public IElement getGeneralizedParent() {
		return null;
	}

	private Collection<IRelation> startingRelations;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dirigent.metafacade.IElement#getStartingRelations()
	 */
	@Override
	public Collection<IRelation> getStartingRelations() {
		if (startingRelations == null) {
			startingRelations = MetafacadeBuilder.getMetafacadeBuilder().getStartingRelations(getUri());
		}
		return startingRelations;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dirigent.metafacade.IElement#getStartingRelations(boolean)
	 */
	@Override
	public Collection<IRelation> getStartingRelations(boolean inherit) {
		// Inheritance is not aplicable for metamart artefacts
		return getStartingRelations();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dirigent.metafacade.IElement#getStartingRelations(java.lang.String,
	 * java.lang.String, boolean)
	 */
	@Override
	public Collection<IRelation> getStartingRelations(String type,
			String stereotype, boolean includeGeneralizedRelations) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dirigent.metafacade.IElement#getFirstStartingRelation(java.lang.String
	 * , java.lang.String)
	 */
	@Override
	public IRelation getFirstStartingRelation(String type, String stereotype) {
		// TODO Auto-generated method stub
		return null;
	}

	private Collection<IRelation> endingRelations;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dirigent.metafacade.IElement#getEndingRelations()
	 */
	@Override
	public Collection<IRelation> getEndingRelations() {
		if (endingRelations == null) {
			endingRelations = MetafacadeBuilder.getMetafacadeBuilder()
					.getEndingRelations(getUri());
		}
		return endingRelations;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dirigent.metafacade.IElement#getDescription()
	 */
	@Override
	public String getDescription() {
		return artefact.description;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dirigent.metafacade.IElement#getAlias()
	 */
	@Override
	public String getAlias() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dirigent.metafacade.IElement#getKeywords()
	 */
	@Override
	public String getKeywords() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dirigent.metafacade.IElement#getStatus()
	 */
	@Override
	public String getStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	private Collection<IElement> childElements;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dirigent.metafacade.IComposite#getChildElements()
	 */
	@Override
	public Collection<IElement> getChildElements() {
		if (childElements == null) {
			childElements = MetafacadeBuilder.getMetafacadeBuilder()
					.getChildElements(artefact.uri);
		}
		return childElements;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dirigent.metafacade.IGeneratable#getPattern()
	 */
	@Override
	public IPattern getPattern() {
		String pattern = null;
		if (pattern == null) {
			String confPattern = DirigentConfig.DEFAULT_PATTERN_ELEMENT;
			if (getStereotype() != null) {
				confPattern = confPattern + "." + getStereotype().toLowerCase();
				pattern = DirigentConfig.getDirigentConfig().getProperty(
						confPattern);
				if (pattern == null) {
					pattern = DirigentConfig.getDirigentConfig().getProperty(
							DirigentConfig.DEFAULT_PATTERN_ELEMENT);
				}
			} else {
				pattern = DirigentConfig.getDirigentConfig().getProperty(
						confPattern);
			}
			if (pattern == null) {
				MMArtefactDecorator.l
						.log(Level.WARNING,
								"Element "
										+ getName()
										+ " skipped. Pattern definition missing in configuration file for pattern "
										+ confPattern);
				return null;
			}
		}
		return PatternBuilder.getPatternBuilder().getPattern(
				pattern + ".pattern.xml");
	}

	public String getExpression() {
		return artefact.expression;
	}

	public String getDataType() {
		return artefact.dataType;
	}

	public String getSourceName() {
		return artefact.sourceName;
	}

	public String getJoinType() {
		return artefact.joinType;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(MMArtefactDecorator o) {
		return this.getUri().compareTo(o.getUri());
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return getUri().hashCode();
	}
}
