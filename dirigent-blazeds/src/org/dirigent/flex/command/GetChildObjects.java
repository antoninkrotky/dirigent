package org.dirigent.flex.command;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.dirigent.config.DirigentConfig;
import org.dirigent.flex.ICommand;
import org.dirigent.metafacade.IComposite;
import org.dirigent.metafacade.IElement;
import org.dirigent.metafacade.IMetafacadeBase;
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
		List<ObjectVO> res;
		if (uri != null) {
			IMetafacadeBase element = MetafacadeBuilder.getMetafacadeBuilder()
					.getMetafacade(uri);
			if (element instanceof IComposite) {
				res = cleanCollection(((IComposite) element).getChildElements());
			} else {
				return null;
			}
		} else {
			res = cleanCollection(MetafacadeBuilder.getMetafacadeBuilder()
					.getChildElements(uri));
		}
		Collections.sort(res, new Comparator<ObjectVO>() {
			@Override
			public int compare(ObjectVO o1, ObjectVO o2) {
				if (o1.name == null) {
					return -1;
				}
				return o1.name.compareTo(o2.name);
			}
		});
		return res;

	}

	private List<ObjectVO> cleanCollection(Collection<IElement> c) {
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
