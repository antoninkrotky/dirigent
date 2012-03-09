package org.dirigent.metafacade.builder.decorator;

import java.util.Collection;

import org.dirigent.metafacade.IColumn;
import org.dirigent.metafacade.ISchema;
import org.dirigent.metafacade.ITable;
import org.dirigent.metafacade.builder.MetafacadeBuilder;
import org.dirigent.metafacade.builder.vo.TableVO;
import org.dirigent.metafacade.builder.vo.VO;

public abstract class TableDecorator extends ElementDecorator implements ITable {

	private TableVO table;

	public TableDecorator(TableVO table) {
		super(table);
		this.table = table;
	}

	@Override
	public Collection<IColumn> getColumns() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ISchema getSchema() {
		return (ISchema) MetafacadeBuilder.getMetafacadeBuilder()
				.getMetafacade(table.schemaUri);
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
		if (getSchema().getSchema()!=null) {
			return getSchema().getSchema() + '.' + getName();
		} else {
		  return getName();
		}
	}

	@Override
	public VO getValueObject() {
		return table;
	}

	@Override
	public String getSQLQuery() {
		return "SELECT * from " + getFullName();
	}

	@Override
	public String getSQLQuery(int offset) {
		return getSQLQuery();
	}


}
