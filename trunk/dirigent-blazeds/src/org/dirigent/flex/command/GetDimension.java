package org.dirigent.flex.command;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.dirigent.flex.ICommand;
import org.dirigent.metafacade.IDimension;
import org.dirigent.metafacade.IDimensionColumn;
import org.dirigent.metafacade.builder.MetafacadeBuilder;
import org.dirigent.metafacade.builder.vo.DimensionColumnVO;
import org.dirigent.metafacade.builder.vo.DimensionVO;
import org.dirigent.metafacade.builder.vo.DomainVO;


public class GetDimension implements ICommand {

	public String uri;
	
	
	public GetDimension(){}
	@Override
	public Object execute() {	
		IDimension d=(IDimension)MetafacadeBuilder.getMetafacadeBuilder().getMetafacade(uri);
		DimensionVO vo=new DimensionVO();
		vo.alias=d.getAlias();
		vo.codeName=d.getAlias();		
		vo.columns=getColumnsVO(d);
		vo.description=d.getDescription();
		vo.id=d.getValueObject().id;
		vo.properties=d.getProperties();
		vo.scdType=d.getSCDType();
		vo.stereotype=d.getStereotype();		
		vo.name=d.getName();
		return vo;
	}
	
	private Collection<DimensionColumnVO> getColumnsVO(IDimension d) {
		Iterator<IDimensionColumn> i=d.getDimensionColumns().iterator();
		ArrayList<DimensionColumnVO> res=new ArrayList<DimensionColumnVO>();
		while (i.hasNext()) {
			IDimensionColumn c=i.next();
			DimensionColumnVO v=new DimensionColumnVO();
			v.name=c.getName();
			v.scdColumnType=c.getHistoryType();
			v.type=c.getType();
			v.domain=new DomainVO();
			v.domain.name=c.getDomain().getName();
			v.domain.dataType=c.getDomain().getDataType();
			v.description=c.getDescription();
			res.add(v);
		}
		return res;
	}

}
