package org.dirigent.metafacade.builder.decorator;

import java.util.Comparator;

import org.dirigent.metafacade.builder.vo.MappingSourceVO;

public class MappingSourceComparator implements Comparator<MappingSourceVO> {

	@Override
	public int compare(MappingSourceVO o1, MappingSourceVO o2) {
		int res=getOrder(o1)-getOrder(o2);
		if (res==0) {
			res=o1.uri.compareTo(o2.uri);
		}
		return res; 
	}
	
	private int getOrder(MappingSourceVO v) {
		if ("inner".equals(v.joinType) && (v.joinCondition==null || "".equals(v.joinCondition.trim()))) {
			return 1;
		} else if ("inner".equals(v.joinType)) {
			return 2;
		} 
		return 3;
		
	}



}
