package org.dirigent.metafacade.builder.ea.decorator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import org.dirigent.metafacade.IAttribute;
import org.dirigent.metafacade.IColumnMapping;
import org.dirigent.metafacade.IMapping;
import org.dirigent.metafacade.IRelation;
import org.dirigent.metafacade.builder.MetafacadeBuilder;
import org.dirigent.metafacade.builder.decorator.ColumnMappingDecorator;
import org.dirigent.metafacade.builder.decorator.MappingDecorator;
import org.dirigent.metafacade.builder.ea.EAMetafacadeBuilder;
import org.dirigent.metafacade.builder.ea.dao.EAConnectorTagDAO;
import org.dirigent.metafacade.builder.ea.vo.EAElementVO;
import org.dirigent.metafacade.builder.vo.MappingSourceVO;
import org.dirigent.metafacade.builder.vo.MappingVO;

public class EAMappingDecorator extends MappingDecorator implements IMapping {

	public EAMappingDecorator(EAElementVO ea) {
		super(init(ea, new MappingVO()));

	}

	@Override
	public Collection<IColumnMapping> getColumnMappings() {
		if (columnMappings == null) {
			columnMappings = new ArrayList<IColumnMapping>();
			for (IAttribute a : getAttributes()) {
				EAColumnMappingDecorator m = new EAColumnMappingDecorator(
						((EAAttributteDecorator) a).getEAAttribute());
				m.setMapping(this);
				columnMappings.add(m);

			}
		}
		return super.getColumnMappings();
	}

	private static Collection<MappingSourceVO> getMapingSourceVOs(
			String elementUri) {
				Iterator<IRelation> i = ((EAMetafacadeBuilder) MetafacadeBuilder
						.getMetafacadeBuilder()).getStartingRelations(elementUri)
						.iterator();
		Collection<MappingSourceVO> res = new ArrayList<MappingSourceVO>();
		while (i.hasNext()) {
			EARelationDecorator v = (EARelationDecorator)i.next();
			if ("Dependency".equals(v.getType())
					&& "BIMappingSource".equals(v.getStereotype())) {
				MappingSourceVO s = new MappingSourceVO();
				s.alias = v.getName();
				s.id = v.getValueObject().id;
				Map<String, String> properties = new EAConnectorTagDAO()
						.getObjectProperties(s.id);
				s.joinCondition = properties.get("joinCondition");
				s.joinType = properties.get("joinType");
				s.joinOrder = properties.get("joinOrder");
				s.mappingUri = v.getStartElementUri();
				s.sourceUri = v.getEndElementUri();
				s.uri = v.getUri();
				res.add(s);
			}

		}
		return res;
	}

	private static MappingVO init(EAElementVO ea, MappingVO v) {
		EAElementDecorator.init(ea, v);
		v.businessRule = ea.note;
		v.schemaUri = "schema:default";
		v.sources = getMapingSourceVOs(v.uri);
		v.targetTableUri = getTargetTableUri(v.uri);
		v.filterCondition=v.properties.get("filterCondition");
		v.pattern=v.properties.get("pattern");
		return v;
	}

	private static String getTargetTableUri(String elementUri) {
		Iterator<IRelation> i = ((EAMetafacadeBuilder) MetafacadeBuilder
				.getMetafacadeBuilder()).getStartingRelations(elementUri)
				.iterator();
		while (i.hasNext()) {
			IRelation v = i.next();
			if ("Dependency".equals(v.getType())
					&& "BIMappingTarget".equals(v.getStereotype())) {
				return v.getEndElementUri();
			}
		}
		return null;
	}

	protected void initColumnMappings() {
		columnMappings = new ArrayList<IColumnMapping>();
		for (IAttribute a : getAttributes()) {
			if (a instanceof ColumnMappingDecorator) {
				ColumnMappingDecorator cm = (ColumnMappingDecorator) a;
				cm.setMapping(this);
				columnMappings.add(cm);
			}
		}
	}
}
