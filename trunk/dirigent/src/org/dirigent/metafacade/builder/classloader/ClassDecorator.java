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

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

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
 * @author Karel
 * 
 */
public class ClassDecorator implements IClass, IGeneratable {

	private Class<?> modelClass;

	/**
	 * @param modelClass
	 */
	public ClassDecorator(Class<?> modelClass) {
		super();
		this.modelClass = modelClass;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dirigent.metafacade.IElement#getUri()
	 */
	@Override
	public String getUri() {
		return modelClass.getName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dirigent.metafacade.IElement#getName()
	 */
	@Override
	public String getName() {
		return modelClass.getSimpleName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dirigent.metafacade.IElement#getType()
	 */
	@Override
	public String getType() {
		return modelClass.getName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dirigent.metafacade.IElement#getStereotype()
	 */
	@Override
	public String getStereotype() {
		Annotation stereotypedAnnotation = getStereotypedAnnotation();
		return stereotypedAnnotation.annotationType().getSimpleName();
	}

	private Annotation getStereotypedAnnotation() {
		for (Annotation annotation : modelClass.getDeclaredAnnotations()) {
			if (annotation.annotationType().getAnnotation(Stereotype.class) != null) {
				return annotation;
			}
		}
		throw new RuntimeException("Class " + modelClass.getName()
				+ " is not anotated by Stereotype.");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dirigent.metafacade.IElement#getProperties()
	 */
	@Override
	public Map<String, String> getProperties() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dirigent.metafacade.IElement#getValueObject()
	 */
	@Override
	public VO getValueObject() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dirigent.metafacade.IElement#getParent()
	 */
	@Override
	public IElement getParent() {
		return new PackageDecorator(modelClass.getPackage());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dirigent.metafacade.IElement#getStartingRelations()
	 */
	@Override
	public Collection<IRelation> getStartingRelations() {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dirigent.metafacade.IElement#getEndingRelations()
	 */
	@Override
	public Collection<IRelation> getEndingRelations() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dirigent.metafacade.IElement#getDescription()
	 */
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
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
	 * @see org.dirigent.metafacade.IClass#getAttributes()
	 */
	@Override
	public Collection<IAttribute> getAttributes() {
		ArrayList<IAttribute> attributes = new ArrayList<IAttribute>();
		for (Field field : modelClass.getDeclaredFields()) {
			if (!Modifier.isStatic(field.getModifiers())) {
			attributes.add(new AttributeDecorator(this, field));
			}
		}
		return attributes;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dirigent.metafacade.IClass#getOperations()
	 */
	@Override
	public Collection<IOperation> getOperations() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dirigent.metafacade.IGeneratable#getPattern()
	 */
	@Override
	public IPattern getPattern() {
		String patternUri = getStereotypedAnnotation().annotationType()
				.getAnnotation(Stereotype.class).pattern();
		return PatternBuilder.getPatternBuilder().getPattern(patternUri);
	}

	/**
	 * @return
	 */
	public String getPackagePath() {
		return modelClass.getPackage().getName().replace('.', '/');
	}

	/**
	 * @return
	 */
	public IClass getSuperclass() {
		if (modelClass.getSuperclass() == null) {
			return null;
		}
		return new ClassDecorator(modelClass.getSuperclass());
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

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.IClass#getOperations(java.lang.String)
	 */
	@Override
	public Collection<IOperation> getOperations(String stereotype) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.modelClass.getName();
	}

}
