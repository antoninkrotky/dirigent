package org.dirigent.flex.command;

import java.util.Collection;
import java.util.Iterator;

import org.dirigent.config.DirigentConfig;
import org.dirigent.flex.DirigentGUI;
import org.dirigent.flex.ICommand;
import org.dirigent.metafacade.builder.MetafacadeBuilder;
import org.dirigent.metafacade.builder.vo.ObjectVO;


public class GetChildObjects  implements ICommand {
	public ObjectVO object;

	@Override
	public Object execute() {
		if (object==null) {
			return cleanCollection(MetafacadeBuilder.getMetafacadeBuilder().getChildObjects(DirigentConfig.getDirigentConfig().getProperty(DirigentGUI.DIRIGENT_BROWSER_ROOT_URI)));
		}
		return cleanCollection(MetafacadeBuilder.getMetafacadeBuilder().getChildObjects(object.uri));
	}
	
	private Collection<ObjectVO> cleanCollection(Collection<ObjectVO> c) {
		
		Iterator<ObjectVO> i=c.iterator();
		c=new java.util.ArrayList<ObjectVO>(c);
		while (i.hasNext()) {
			ObjectVO v=i.next();
			if (!"Package".equals(v.type) && !"Class".equals(v.type)) {
				c.remove(v);
			}
		}
		return c;
	}
	

}
