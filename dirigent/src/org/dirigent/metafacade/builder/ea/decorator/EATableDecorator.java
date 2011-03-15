package org.dirigent.metafacade.builder.ea.decorator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.dirigent.metafacade.IAttribute;
import org.dirigent.metafacade.IColumn;
import org.dirigent.metafacade.ISchema;
import org.dirigent.metafacade.ITable;
import org.dirigent.metafacade.builder.ea.vo.EAElementVO;
import org.dirigent.metafacade.builder.vo.TableVO;

public class EATableDecorator extends EAClassDecorator implements ITable {

	public EATableDecorator(EAElementVO ea) {
		super(ea);
	}

	@Override
	public Collection<IColumn> getColumns() {
		Collection<IColumn> c=new ArrayList<IColumn>();
		Iterator<IAttribute> i=getAttributes().iterator();
		while (i.hasNext()) {
			IAttribute a=i.next();
			if (a instanceof IColumn) {
				c.add((IColumn)a);
			}
		}
		return c;
	}

	public static TableVO init(EAElementVO ea, TableVO v) {
		EAClassDecorator.init(ea, v);
		v.codeName = ea.alias;
		v.schemaUri = "schema:default";
		return v;
	}
	
	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.builder.decorator.TableDecorator#getSchema()
	 */
	@Override
	public ISchema getSchema() {
		return EACommon.getSchema(this);
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.IQueriable#getSQLQuery()
	 */
	@Override
	public String getSQLQuery() {
		return "SELECT * from " + getFullName();
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.IQueriable#getSQLQuery(int)
	 */
	@Override
	public String getSQLQuery(int offset) {
		return getSQLQuery();
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.ITable#getFullName()
	 */
	@Override
	public String getFullName() {
		if (getSchema().getSchema()!=null) {
			return getSchema().getSchema() + '.' + getName();
		} else {
		  return getName();
		}
	}

}
