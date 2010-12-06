package org.dirigent.executor;

import java.text.Normalizer;
import java.util.Collection;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.dirigent.metafacade.IAttribute;

public class TemplateUtils {

	public static String normalizeString(String text) {
		String temp = Normalizer.normalize(text, Normalizer.Form.NFD)
				.replaceAll("[^\\p{ASCII}]", "");
		return temp.toUpperCase().replace(" ", "_");
	}
	
	public static String removeBrackets(String text) {
		return text.replace("{","").replace("}","");
	}
	
	public static Class<StringUtils> getStringUtils() {		
		return StringUtils.class;
	}
	

	public static Class<StringEscapeUtils> getStringEscapeUtils() {		
		return StringEscapeUtils.class;
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
	
	/**
	 * joins str1 and str2 by joinSign whether both str1 and str2 are not empty. Otherwise return
	 * non empty string (or empty string if are both empty)
	 * @param str1 first String to be joined
	 * @param str2 second String to be joined
	 * @param joinSign String which will be between str1 and str2
	 * @return joined string
	 */
	public static String joinStrings(String str1, String str2, String joinSign) {
		StringBuilder sb = new StringBuilder(str1.length() + str2.length() + joinSign.length());
		if (str1.isEmpty() || str2.isEmpty()) {
			if (!str1.isEmpty()) {
				sb.append(str1);
			} else if (!str2.isEmpty()) {
				sb.append(str2);
			}
		} else {
			sb.append(str1).append(" ").append(joinSign).append(" ").append(str2);
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
