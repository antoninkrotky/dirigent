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

import org.apache.velocity.VelocityContext;
import org.dirigent.config.DirigentConfig;
import org.dirigent.metafacade.IGeneratable;

/**
 * @author bgrekov
 *
 */
public abstract class AbstractContextFactory {
	
	public abstract VelocityContext createVelocityContext(IGeneratable gen);
	
	private static AbstractContextFactory getContextFactory() {
		return new DefaultContextFactory();
	}
	
	public static VelocityContext getVelocityContext(IGeneratable gen){
		return AbstractContextFactory.getContextFactory().createVelocityContext(gen);
	}
	
	static class DefaultContextFactory extends AbstractContextFactory {
		/* (non-Javadoc)
		 * @see org.dirigent.executor.AbstractContextFactory#createVelocityContext(org.dirigent.metafacade.IGeneratable)
		 */
		@Override
		public VelocityContext createVelocityContext(IGeneratable gen) {
			VelocityContext vCtx = new VelocityContext();
			vCtx.put("element", gen);
			vCtx.put("config", DirigentConfig.getDirigentConfig());
			vCtx.put("utils", TemplateUtils.class);
			return vCtx;
		}
	}

}
