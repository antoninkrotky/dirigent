package org.dirigent.metafacade.builder.ea.decorator;

import java.util.Collection;
import java.util.Collections;

import org.dirigent.metafacade.IColumn;
import org.dirigent.metafacade.builder.decorator.TableDecorator;
import org.dirigent.metafacade.builder.ea.vo.EAElementVO;
import org.dirigent.metafacade.builder.vo.TableVO;

public class EATableDecorator extends TableDecorator {

	public EATableDecorator(EAElementVO ea) {
		super(init(ea, new TableVO()));
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<IColumn> getColumns() {
		return Collections.checkedCollection((Collection)getAttributes(), IColumn.class);
	}

	public static TableVO init(EAElementVO ea, TableVO v) {
		EAElementDecorator.init(ea, v);
		v.codeName = ea.alias;
		v.schemaUri = "schema:default";
		return v;
	}

}
