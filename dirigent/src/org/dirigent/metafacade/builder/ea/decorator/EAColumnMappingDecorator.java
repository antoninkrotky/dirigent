package org.dirigent.metafacade.builder.ea.decorator;

import org.apache.commons.lang.StringEscapeUtils;
import org.dirigent.metafacade.builder.decorator.ColumnMappingDecorator;
import org.dirigent.metafacade.builder.ea.vo.EAAttributeVO;
import org.dirigent.metafacade.builder.vo.ColumnMappingVO;

public class EAColumnMappingDecorator extends ColumnMappingDecorator {

	public EAColumnMappingDecorator(EAAttributeVO ea) {
		super(EAColumnMappingDecorator.init(ea,new ColumnMappingVO()));
		
	}

	private static ColumnMappingVO init(EAAttributeVO ea, ColumnMappingVO v) {
		EAAttributteDecorator.init(ea, v);
		v.columnName=ea.name;
		//Unescape HTML entities used by EA
		v.expression=StringEscapeUtils.unescapeHtml(ea.notes);
		return v;
	}

}
