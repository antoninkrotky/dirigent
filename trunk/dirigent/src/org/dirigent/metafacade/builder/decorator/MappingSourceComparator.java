package org.dirigent.metafacade.builder.decorator;

import java.util.Comparator;

import org.dirigent.metafacade.builder.vo.MappingSourceVO;

public class MappingSourceComparator implements Comparator<MappingSourceVO> {

	@Override
	/**
	 * If object has properly sat joinOrder properties, then they are compared by
	 * this.
	 */
	public int compare(MappingSourceVO o1, MappingSourceVO o2) {
		int res;
		Integer ord1 = this.getOrderNumber(o1.joinOrder);
		Integer ord2 = this.getOrderNumber(o2.joinOrder);
		
		if (ord1 != null && ord2 != null) {
			res = ord1 - ord2;
		} else {
			res = getOrder(o1) - getOrder(o2);
			if (res == 0) {
				res = o1.uri.compareTo(o2.uri);
			}
		}
		return res;
	}

	private int getOrder(MappingSourceVO v) {
		if ("inner".equals(v.joinType)
				&& (v.joinCondition == null || ""
						.equals(v.joinCondition.trim()))) {
			return 1;
		} else if ("inner".equals(v.joinType)) {
			return 2;
		}
		return 3;

	}

	/**
	 * Return an Integer instance from the String. Whether the order is not a
	 * number (e.g. null or something else) then return null.
	 * 
	 * @param order order of the mapping source (should contain an integer)
	 * @return Integer from order attribute or null if there is no integer
	 */
	private Integer getOrderNumber(String order) {
		Integer res = null;
		try {
			res = new Integer(order);
		} catch (NumberFormatException ex) {

		}
		return res;
	}

}
