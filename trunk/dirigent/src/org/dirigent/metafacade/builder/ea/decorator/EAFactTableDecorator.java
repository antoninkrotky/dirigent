package org.dirigent.metafacade.builder.ea.decorator;

import java.util.Collection;
import java.util.Collections;

import org.dirigent.metafacade.IColumn;
import org.dirigent.metafacade.IDimension;
import org.dirigent.metafacade.IFactTable;
import org.dirigent.metafacade.IRelation;
import org.dirigent.metafacade.builder.MetafacadeBuilder;
import org.dirigent.metafacade.builder.ea.vo.EAElementVO;

public class EAFactTableDecorator extends EATableDecorator implements IFactTable {
	Collection<IRelation> irels;
	Collection<IDimension> idems;

	public EAFactTableDecorator(EAElementVO ea) {
		super(ea);
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public Collection<IColumn> getColumns() {
				return Collections.checkedCollection((Collection)getAttributes(), IColumn.class);
		
	}
	
	@Override
	public Collection<IDimension> getRelatedDimensions() {
		irels=this.getStartingRelations();
		for (IRelation k : irels){
		if(k.getStereotype()=="BIRelation"){
			idems.add((IDimension)MetafacadeBuilder.getMetafacadeBuilder().getMetafacade(k.getEndElementUri()));			
		}	
			
		}
		return idems;		
		
	}

}
