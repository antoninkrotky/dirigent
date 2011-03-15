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

import org.dirigent.metafacade.IElement;
import org.dirigent.metafacade.ISchema;
import org.dirigent.metafacade.builder.MetafacadeBuilder;

/**
 * @author khubl
 * 
 */
public class EACommon {
	private static final String DEFAULT_SCHEMA = "schema:default";

	public static ISchema getSchema(IElement element) {
		String schemaOverride = element.getProperties().get("schema");
		while (schemaOverride == null && element.getParent() != null) {
			element = element.getParent();
			schemaOverride = element.getProperties().get("schema");
		}
		if (schemaOverride != null) {
			return (ISchema) MetafacadeBuilder.getMetafacadeBuilder()
					.getMetafacade("schema:"+schemaOverride);
		}
		return (ISchema) MetafacadeBuilder.getMetafacadeBuilder()
				.getMetafacade(DEFAULT_SCHEMA);
	}
	
	/**
	 * Create new colection conatining references to all assignable elements from original collection. 
	 * */
	@SuppressWarnings("unchecked")
	public static <E> Collection<E> copyAssignable(Collection<?> c,Class<E> clazz) {
		Collection<E> res=new ArrayList<E>(c.size());
		Iterator<?> i=c.iterator();
		while (i.hasNext()) {
			Object o=i.next();
			if (clazz.isInstance(o)) {
				res.add((E)o);
			}
		}
		return res;
	}
}
