package org.dirigent.metafacade.builder.decorator;

import java.util.Collection;

import org.dirigent.metafacade.IColumn;
import org.dirigent.metafacade.ISchema;
import org.dirigent.metafacade.ITable;
import org.dirigent.metafacade.builder.MetafacadeBuilder;
import org.dirigent.metafacade.builder.vo.TableVO;
import org.dirigent.metafacade.builder.vo.VO;

public class TableDecorator implements ITable {

	private TableVO table;
	
	public TableDecorator(TableVO table) {
		this.table=table;
	}
	
	@Override
	public Collection<IColumn> getColumns() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ISchema getSchema() {
		return (ISchema)MetafacadeBuilder.getMetafacadeBuilder().getMetafacade(table.schemaUri);
	}

	@Override
	public String getName() {
		return table.name;
	}

	@Override
	public String getUri() {
		return table.uri;
	}

	@Override
	public String getFullName() {
		return getSchema().getSchema()+'.'+getName();
	}

	@Override
	public String getCodeName() {
		return table.name;
	}

	@Override
	public VO getValueObject() {
		return table;
	}

}
