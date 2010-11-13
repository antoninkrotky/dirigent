package org.dirigent.flex.command;



import java.util.Collection;
import java.util.Iterator;

import org.dirigent.config.DirigentConfig;
import org.dirigent.flex.ICommand;
import org.dirigent.metafacade.IComposite;
import org.dirigent.metafacade.IElement;
import org.dirigent.metafacade.builder.MetafacadeBuilder;
import org.dirigent.metafacade.builder.vo.ObjectVO;

public class GetChildObjects implements ICommand {
	public ObjectVO object;


	
	@Override
	public Object execute() {
		String uri;
		if (object == null) {
			uri = DirigentConfig.getDirigentConfig().getProperty(
					DirigentConfig.DIRIGENT_BROWSER_ROOT_URI);
		} else {
			uri = object.uri;
		}

		if (uri != null) {
			IElement element = MetafacadeBuilder.getMetafacadeBuilder()
					.getMetafacade(uri);
			if (element instanceof IComposite) {
				return cleanCollection(((IComposite) element)
						.getChildElements());
			} else {
				return null;
			}
		} 
		
			return cleanCollection(MetafacadeBuilder.getMetafacadeBuilder().getChildElements(uri));
		
	}

	private Collection<ObjectVO> cleanCollection(Collection<IElement> c) {
		Iterator<IElement> i = c.iterator();
		java.util.ArrayList<ObjectVO> res = new java.util.ArrayList<ObjectVO>();
		while (i.hasNext()) {
			IElement v = i.next();
			if ("Package".equals(v.getType()) || "Class".equals(v.getType())
					|| "Diagram".equals(v.getType())) {
				ObjectVO o = new ObjectVO();
				o.uri = v.getUri();
				o.name = v.getName();
				o.type = v.getType();
				o.stereotype = v.getStereotype();
				res.add(o);
			}
		}
		return res;
	}

}
