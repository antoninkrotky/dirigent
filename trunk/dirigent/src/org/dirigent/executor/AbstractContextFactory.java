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
package org.dirigent.executor;

import java.util.Map;
import java.util.Set;

import org.apache.velocity.VelocityContext;
import org.dirigent.config.DirigentConfig;
import org.dirigent.metafacade.IElement;
import org.dirigent.metafacade.ILibraryImport;

/**
 * @author bgrekov
 * 
 */
public abstract class AbstractContextFactory {
	private static AbstractContextFactory instance;
	
	public abstract VelocityContext createVelocityContext(IElement gen);

	public static AbstractContextFactory getContextFactory() {
		if (instance==null) {
			instance=createInstance();
		}
		return instance;
	}
	
	private static AbstractContextFactory createInstance() {
		String contextFactoryName = System
				.getProperty("dirigent.context.factory");
		if (contextFactoryName != null) {
			try {
				return (AbstractContextFactory) Class.forName(
						contextFactoryName).newInstance();
			} catch (InstantiationException e) {
				throw new RuntimeException(
						"InstantiationException when creating instance of "
								+ contextFactoryName + ". "
								+ contextFactoryName
								+ " is either abstract or interface. ");
			} catch (IllegalAccessException e) {
				throw new RuntimeException(
						"IllegalAccessException when creating instance of "
								+ contextFactoryName, e);
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Class " + contextFactoryName
						+ " not found.");
			}
		} else {
			return new DefaultContextFactory();
		}
	}

	static class DefaultContextFactory extends AbstractContextFactory {
		
		private final VelocityContext prototype=createPrototype();
		
		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.dirigent.executor.AbstractContextFactory#createVelocityContext
		 * (org.dirigent.metafacade.IGeneratable)
		 */
		@Override
		public VelocityContext createVelocityContext(IElement gen) {			
			VelocityContext vCtx=(VelocityContext)prototype.clone();
			vCtx.put("element", gen);
			addElementLibraries(vCtx, gen);		
			return vCtx;
		}

		/**
		 * @return
		 */
		private VelocityContext createPrototype() {
			VelocityContext vCtx = new VelocityContext();			
			addGlobalLibraties(vCtx);
			vCtx.put("config", DirigentConfig.getDirigentConfig());
			vCtx.put("utils", TemplateUtils.class);			
			return vCtx;
		}

		/**
		 * @param vCtx
		 */
		private void addGlobalLibraties(VelocityContext vCtx) {
			Set<String> globalLibraryKeys=DirigentConfig.getDirigentConfig().getProperties(DirigentConfig.GLOBAL_LIBRARY_PREFIX);
			for (String key : globalLibraryKeys) {
				vCtx.put(getLibraryContextKey(key), createGlobalLibrary(key));				
			}
		}

		/**
		 * @param key
		 * @return
		 */
		protected String getLibraryContextKey(String key) {
			return key.substring(DirigentConfig.GLOBAL_LIBRARY_PREFIX.length()+1);
		}

		/**
		 * @param library
		 * @return
		 */
		private Object createGlobalLibrary(String libraryKey) {
			String libraryClassName=DirigentConfig.getDirigentConfig().getProperty(libraryKey);
			return getLibraryInstance(libraryClassName);
		}

		/**
		 * 
		 */
		

		/**
		 * @param vCtx
		 * @param gen
		 */
		private void addElementLibraries(VelocityContext vCtx, IElement gen) {
			if (!(gen instanceof ILibraryImport)) {
				return;
			}
			Map<String, String> librariesMap = ((ILibraryImport) gen)
					.getLibraries();
			for (String key : librariesMap.keySet()) {
				vCtx.put(key, getLibraryInstance(librariesMap.get(key)));
			}

		}

		/**
		 * @param object
		 * @return
		 */
		private Object getLibraryInstance(String className) {
			try {
				return Class.forName(className).newInstance();
			} catch (Exception e) {
				throw new RuntimeException(
						"Exception instantiating library class " + className, e);
			}
		}
	}

}
