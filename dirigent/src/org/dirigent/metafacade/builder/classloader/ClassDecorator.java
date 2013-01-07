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

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.dirigent.config.DirigentConfig;
import org.dirigent.metafacade.IAttribute;
import org.dirigent.metafacade.IClass;
import org.dirigent.metafacade.IElement;
import org.dirigent.metafacade.IGeneratable;
import org.dirigent.metafacade.IOperation;
import org.dirigent.metafacade.IRelation;
import org.dirigent.metafacade.builder.vo.VO;
import org.dirigent.pattern.IPattern;
import org.dirigent.pattern.builder.PatternBuilder;

/**
 * @author khubl
 *
 */
public class ClassDecorator implements IClass, IGeneratable {

	private Class<?> type;
	private Collection<IAttribute> staticAttributes;
	private Collection<IAttribute> instanceAttributes;
	private Collection<IAttribute> attributes;
	
	public ClassDecorator(Class<?> type) {
		this.type=type;
	}
	
	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.IElement#getUri()
	 */
	@Override
	public String getUri() {
		return type.getName();
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.IElement#getName()
	 */
	@Override
	public String getName() {
		return type.getSimpleName();
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.IElement#getType()
	 */
	@Override
	public String getType() {
		return type.getName();
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.IElement#getStereotype()
	 */
	@Override
	public String getStereotype() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.IElement#getProperties()
	 */
	@Override
	public Map<String, String> getProperties() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.IElement#getValueObject()
	 */
	@Override
	public VO getValueObject() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.IElement#getParent()
	 */
	@Override
	public IElement getParent() {
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
	 * @see org.dirigent.metafacade.IElement#getStartingRelations()
	 */
	@Override
	public Collection<IRelation> getStartingRelations() {
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
	 * @see org.dirigent.metafacade.IElement#getStatus()
	 */
	@Override
	public String getStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.IClass#getAttributes()
	 */
	@Override
	public Collection<IAttribute> getAttributes() {
		if (this.attributes==null) {
			this.attributes=new ArrayList<IAttribute>();
			this.attributes.addAll(getStaticAttributes());
			this.attributes.addAll(getInstanceAttributes());
		}
		return this.attributes;
	}


	private void createStaticAttributes() {
		this.staticAttributes=new ArrayList<IAttribute>();
		
		for (Field field  : type.getDeclaredFields()) {
			if (Modifier.isStatic(field.getModifiers())) {
				this.staticAttributes.add(new FieldDecorator(this,field));
			}
		}
		
	}
	
	public Collection<IAttribute> getStaticAttributes() {
		if (staticAttributes==null) {
			createStaticAttributes();
		}
		return staticAttributes;
	}

	public Collection<IAttribute> getInstanceAttributes() {
		if (instanceAttributes==null) {
			createInstanceAttributes();
		}
		return instanceAttributes;
	}

	private void createInstanceAttributes() {
		this.instanceAttributes=new ArrayList<IAttribute>();
		
		for (Field field  : type.getDeclaredFields()) {
			if (!(Modifier.isStatic(field.getModifiers()))) {
				this.instanceAttributes.add(new FieldDecorator(this,field));
			}
		}
		
	}
	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.IClass#getOperations()
	 */
	@Override
	public Collection<IOperation> getOperations() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.IClass#getOperations(java.lang.String)
	 */
	@Override
	public Collection<IOperation> getOperations(String stereotype) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.IGeneratable#getPattern()
	 */
	@Override
	public IPattern getPattern() {
		String patternUri=getPatternUri();		
		return PatternBuilder.getPatternBuilder().getPattern(patternUri);
	}

	/**
	 * @return
	 */
	private String getPatternUri() {
		return DirigentConfig.getDirigentConfig().getProperty(
				DirigentConfig.DEFAULT_PATTERN_ELEMENT)+".pattern.xml";
	}

	public String getPackageName() {
		return this.type.getPackage().getName();
	}


	public String getPackagePath() {
		return getPackageName().replace('.', '/');
	}

}
