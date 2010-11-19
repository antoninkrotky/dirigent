package org.dirigent.executor;

import java.text.Normalizer;
import java.util.Collection;

import org.dirigent.metafacade.IAttribute;

public class TemplateUtils {

	public static String normalizeString(String text) {
		String temp = Normalizer.normalize(text, Normalizer.Form.NFD)
				.replaceAll("[^\\p{ASCII}]", "");
		return temp.toUpperCase().replace(" ", "_");
	}

	/**
	 * Creates SQL Where clause that compares two tables with the equally named columns
	 * @param elements List of columns
	 * @param compSign sign which will be used for comparison, e.g. "=" or "<>"
	 * @param joinString string that will join single terms, e.g. "AND" or "OR" 
	 * @param firstTableName alias/name of the first table
	 * @param secondTableName alias/name of the second table
	 * @return String that can be added into the SQL Where clause
	 */
	public static String getWhereClausule(Collection<IAttribute> elements,
			String compSign, String joinString, String firstTableName,
			String secondTableName) {
		return getWhereClausule(elements, compSign, joinString, firstTableName, secondTableName, false);
	}
	
	/**
	 * Creates SQL Where clause that compares two tables with the equally named columns
	 * @param elements List of columns
	 * @param compSign sign which will be used for comparison, e.g. "=" or "<>"
	 * @param joinString string that will join single terms, e.g. "AND" or "OR" 
	 * @param firstTableName alias/name of the first table
	 * @param secondTableName alias/name of the second table
	 * @param joinImmediately if joinString should be at the beginning too
	 * @return String that can be added into the SQL Where clause
	 */
	public static String getWhereClausule(Collection<IAttribute> elements,
			String compSign, String joinString, String firstTableName,
			String secondTableName, boolean joinImmediatelly) {
		boolean connectSign = joinImmediatelly;
		StringBuilder sb = new StringBuilder(50);
		for (IAttribute el : elements) {
			if (connectSign) {
				sb.append(" ").append(joinString).append(" ");
			} else {
				connectSign = true;
			}
			
			sb.append(firstTableName).append(".").append(el.getName());
			sb.append(" ").append(compSign).append(" ");
			sb.append(secondTableName).append(".").append(el.getName());
		}
		return sb.toString();
	}
	
	public static String capitalize(String string) {
		StringBuffer sb=new StringBuffer();		
		sb.append(Character.toUpperCase(string.charAt(0)));
		sb.append(string.substring(1));
		return sb.toString();
	}

}
