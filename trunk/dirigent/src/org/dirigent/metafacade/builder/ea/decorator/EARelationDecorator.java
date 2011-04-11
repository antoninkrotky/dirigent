package org.dirigent.metafacade.builder.ea.decorator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.dirigent.metafacade.IRelation;
import org.dirigent.metafacade.builder.decorator.RelationDecorator;
import org.dirigent.metafacade.builder.ea.dao.EAConnectorTagDAO;
import org.dirigent.metafacade.builder.ea.vo.EAConnectorVO;
import org.dirigent.metafacade.builder.vo.RelationVO;


public class EARelationDecorator extends RelationDecorator {

	public EARelationDecorator(EAConnectorVO ea) {
		super(init(ea, new RelationVO()));
	}

	public static RelationVO init(EAConnectorVO ea, RelationVO v) {
		v.uri = ea.ea_guid;
		v.id = ea.id;
		v.name = ea.name;
		v.type = ea.type;
		v.description = ea.note;
		v.stereotype = ea.stereotype;
		v.properties = new EAConnectorTagDAO().getObjectProperties(ea.id);
		v.startElementId = ea.startObjectId;
		v.endElementId = ea.endObjectId;
		v.startElementUri = ea.startObjectGuid;
		v.endElementUri = ea.endObjectGuid;
		v.lineColor = ea.lineColor;
		v.alias = ea.alias;
		v.sourceRole = ea.sourceRole;
		v.destRole = ea.destRole;
		
		return v;
	}

	public static Collection<IRelation> convertCollection(
			Collection<EAConnectorVO> connectors) {
		Collection<IRelation> res = new ArrayList<IRelation>(connectors.size());
		Iterator<EAConnectorVO> i = connectors.iterator();
		while (i.hasNext()) {
			EAConnectorVO connector = i.next();
			res.add(new EARelationDecorator(connector));
		}
		return res;
	}

}
