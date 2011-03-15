package org.dirigent.metafacade.builder.ea.decorator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

import org.dirigent.metafacade.IColumn;
import org.dirigent.metafacade.IDimension;
import org.dirigent.metafacade.IFactTable;
import org.dirigent.metafacade.IRelation;
import org.dirigent.metafacade.builder.MetafacadeBuilder;
import org.dirigent.metafacade.builder.ea.vo.EAElementVO;

public class EAFactTableDecorator extends EATableDecorator implements IFactTable {
	private static Logger LOG = Logger.getLogger(EAFactTableDecorator.class.getName()); 
	
	private Collection<IDimension> idems= null;

	public EAFactTableDecorator(EAElementVO ea) {
		super(ea);
		
	}

	@Override
	public Collection<IColumn> getColumns() {
		return EACommon.copyAssignable(getAttributes(), IColumn.class);
	}
	
	@Override
	public Collection<IDimension> getRelatedDimensions() {
		//lazy load
		if (idems == null){
			idems = new ArrayList<IDimension>();	
			for (IRelation k : this.getStartingRelations()){
				if(k.getStereotype().equals("BIRelation")){
					LOG.info("Adding related dimension");
					idems.add((IDimension)MetafacadeBuilder.getMetafacadeBuilder().getMetafacade(k.getEndElementUri()));			
				}
			}
		}
		return idems;		
		
	}

}
