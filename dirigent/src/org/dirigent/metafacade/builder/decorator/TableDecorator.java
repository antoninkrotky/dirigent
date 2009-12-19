package org.dirigent.metafacade.builder.decorator;

import java.util.Collection;

import org.dirigent.metafacade.Column;
import org.dirigent.metafacade.Schema;
import org.dirigent.metafacade.Table;
import org.dirigent.metafacade.builder.MetafacadeBuilder;
import org.dirigent.metafacade.builder.vo.TableVO;

public class TableDecorator implements Table {

	private TableVO table;
	
	public TableDecorator(TableVO table) {
		this.table=table;
	}
	
	@Override
	public Collection<Column> getColumns() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Schema getSchema() {
		return (Schema)MetafacadeBuilder.getMetafacadeBuilder().getMetafacade(table.schemaUri);
	}

	@Override
	public String getName() {
		return table.name;
	}

	@Override
	public String getUri() {
		return table.uri;
	}

}
