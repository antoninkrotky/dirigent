package org.dirigent.executor;

import java.lang.reflect.Method;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.dirigent.metafacade.IAttribute;
import org.dirigent.metafacade.IElement;
import org.dirigent.metafacade.IOperation;
import org.dirigent.metafacade.IParameter;
import org.dirigent.metafacade.IRelation;
import org.dirigent.metafacade.builder.MetafacadeBuilder;

/**
 * Utility class containing static helper methods to be used in Dirigent
 * templates.
 * */
public class TemplateUtils {

	private static Logger l = Logger.getLogger(TemplateUtils.class.getName());

	/**
	 * Normalize the string.
	 * */
	public static String normalizeString(String text) {
		String temp = Normalizer.normalize(removeExtraWhitespaces(text),
				Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
		return temp.toUpperCase().replace(" ", "_");
	}

	public static String removeExtraWhitespaces(String text) {
		String str = null;
		if (text != null) {
			Pattern pattern = Pattern.compile("\\s+");
			Matcher matcher = pattern.matcher(text.trim());
			str = matcher.replaceAll(" ");
		}
		return str;
	}

	public static String lpad(String text, String lpadChar, int length) {
		return String.format("%1$#" + length + "s", text).replace(' ',
				lpadChar.charAt(0));
	}

	/**
	 * Remove brackets ({, }) from string.
	 * */
	public static String removeBrackets(String text) {
		if (text == null) {
			return null;
		}
		return text.replace("{", "").replace("}", "");
	}

	/**
	 * Get reference to Apache StringUtils class.
	 * */
	public static Class<StringUtils> getStringUtils() {
		return StringUtils.class;
	}

	/**
	 * Get reference to Apache StringEscapeUtils class.
	 * */
	public static Class<StringEscapeUtils> getStringEscapeUtils() {
		return StringEscapeUtils.class;
	}

	/**
	 * Creates SQL Where clause that compares two tables with the equally named
	 * columns
	 * 
	 * @param elements
	 *            List of columns
	 * @param compSign
	 *            sign which will be used for comparison, e.g. "=" or "<>"
	 * @param joinString
	 *            string that will join single terms, e.g. "AND" or "OR"
	 * @param firstTableName
	 *            alias/name of the first table
	 * @param secondTableName
	 *            alias/name of the second table
	 * @return String that can be added into the SQL Where clause
	 */
	public static String getWhereClausule(Collection<IAttribute> elements,
			String compSign, String joinString, String firstTableName,
			String secondTableName) {
		return getWhereClausule(elements, compSign, joinString, firstTableName,
				secondTableName, false);
	}

	/**
	 * Creates SQL Where clause that compares two tables with the equally named
	 * columns
	 * 
	 * @param elements
	 *            List of columns
	 * @param compSign
	 *            sign which will be used for comparison, e.g. "=" or "<>"
	 * @param joinString
	 *            string that will join single terms, e.g. "AND" or "OR"
	 * @param firstTableName
	 *            alias/name of the first table
	 * @param secondTableName
	 *            alias/name of the second table
	 * @param joinImmediately
	 *            if joinString should be at the beginning too
	 * @return String that can be added into the SQL Where clause
	 */
	public static String getWhereClausule(Collection<IAttribute> elements,
			String compSign, String joinString, String firstTableName,
			String secondTableName, boolean joinImmediatelly) {
		if (elements == null) {
			l.warning("No elements passed to getWhereClausule method.");
			return null;
		}
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
	 * joins str1 and str2 by joinSign whether both str1 and str2 are not empty.
	 * Otherwise return non empty string (or empty string if are both empty)
	 * 
	 * @param str1
	 *            first String to be joined
	 * @param str2
	 *            second String to be joined
	 * @param joinSign
	 *            String which will be between str1 and str2
	 * @return joined string
	 */
	public static String joinStrings(String str1, String str2, String joinSign) {
		StringBuilder sb = new StringBuilder(str1.length() + str2.length()
				+ joinSign.length());
		if (str1.isEmpty() || str2.isEmpty()) {
			if (!str1.isEmpty()) {
				sb.append(str1);
			} else if (!str2.isEmpty()) {
				sb.append(str2);
			}
		} else {
			sb.append(str1).append(" ").append(joinSign).append(" ").append(
					str2);
		}
		return sb.toString();
	}

	/**
	 * Capitalize string.
	 * */
	public static String capitalize(String string) {
		StringBuffer sb = new StringBuffer();
		sb.append(Character.toUpperCase(string.charAt(0)));
		sb.append(string.substring(1));
		return sb.toString();
	}

	/**
	 * Converts string in camel case notation to database form. E.g.
	 * CamelCaseString becomes camel_case_string.
	 * 
	 * @param s
	 *            input parameter
	 * @param uppercase
	 *            upper case the result
	 * @return reformatted string
	 */
	public static String camelToDb(String s, boolean uppercase) {
		if (s == null)
			throw new NullPointerException();

		StringBuilder sb = new StringBuilder();
		int length = s.length();
		for (int i = 0; i < length; i++) {
			char ch = s.charAt(i);
			if (Character.isUpperCase(ch)) {
				if (i > 0 && (i + 1) < length
						&& !Character.isUpperCase(s.charAt(i + 1))) {
					sb.append('_');
				}
				sb.append(Character.toLowerCase(ch));
			} else {
				sb.append(ch);
			}
		}

		if (sb.length() > 30)
			throw new IllegalArgumentException("Result '" + sb.toString()
					+ "' is too long (" + sb.length()
					+ " chars, maximum is 30)");

		String result = sb.toString();
		if (uppercase)
			result = result.toUpperCase();
		return result;
	}

	/**
	 * Joins element names provided by the collection to single string. Result
	 * of element's getName() method is used as element name. If the element
	 * does not implement getName() method, result of toString() method is used
	 * as element name.
	 * */
	public static String joinNames(Collection<?> col, String separator) {
		ArrayList<String> c = new ArrayList<String>();
		Iterator<?> i = col.iterator();
		while (i.hasNext()) {
			Object o = i.next();
			String value = null;
			if (o != null) {
				try {
					Method methodGetName = o.getClass().getMethod("getName");
					if (String.class.equals(methodGetName.getReturnType())) {
						c.add((String) methodGetName.invoke(o));
					} else {
						value = o.toString();
					}
				} catch (Exception e) {
					value = o.toString();
				}
			}
			if (value != null) {
				c.add(value);
			}
		}
		return StringUtils.join(c, separator);
	}

	/**
	 * Get java service method signature for specified operation.
	 * */
	public static String getJavaServiceMethodSignature(IOperation operation) {
		StringBuffer sb = new StringBuffer();
		String returnType = getJavaServiceParameterTypeFromClassifier(operation
				.getReturnClassifier(), operation.isReturningArray());
		if (returnType == null) {
			returnType = operation.getReturnType();
			if (returnType != null && !"".equals(returnType)
					&& !Character.isLowerCase(returnType.charAt(0))
					&& !"String".equals(returnType)) {
				l
						.warning("No classifier associated with return type "
								+ " (type "
								+ returnType
								+ "). This is OK for generic types like int or String. For types defined in model ensure, that parameter type is defined by reference in model (not by string name of type).");
			}
			if (returnType == null || "".equals(returnType)) {
				returnType = "void";
			} else if (operation.isReturningArray()) {
				returnType = "Collection<" + returnType + ">";
			}

		}
		sb.append(returnType);
		sb.append(' ');
		sb.append(operation.getName());
		sb.append('(');
		Iterator<IParameter> i = operation.getParameters().iterator();
		while (i.hasNext()) {
			IParameter p = i.next();
			String type = getJavaServiceParameterTypeFromClassifier(p
					.getClassifier(), p.isArray());
			if (type == null || "".equals(type)) {
				type = p.getType();
				if (type != null && !"".equals(type)
						&& !Character.isLowerCase(type.charAt(0))
						&& !"String".equals(type)) {
					l
							.warning("No classifier associated with parameter "
									+ p.getName()
									+ " (type "
									+ type
									+ "). This is OK for generic types like int or String. For types defined in model ensure, that parameter type is defined by reference in model (not by string name of type).");
				}
			}
			sb.append(type);
			sb.append(' ');
			sb.append(p.getName());
			if (i.hasNext()) {
				sb.append(", ");
			}
		}
		sb.append(')');

		return sb.toString();
	}

	/**
	 * Get flex service method signature for specified operation.
	 * */
	public static String getFlexServiceMethodSignature(IOperation operation) {
		StringBuffer sb = new StringBuffer();
		String returnType = getFlexServiceParameterTypeFromClassifier(operation
				.getReturnClassifier(), operation.isReturningArray());
		if (returnType == null) {
			returnType = operation.getReturnType();
			if (returnType != null && !"".equals(returnType)
					&& !Character.isLowerCase(returnType.charAt(0))
					&& !"String".equals(returnType)) {
				l
						.warning("No classifier associated with return type "
								+ " (type "
								+ returnType
								+ "). This is OK for generic types like int or String. For types defined in model ensure, that parameter type is defined by reference in model (not by string name of type).");
			}
			if (returnType == null || "".equals(returnType)) {
				returnType = "void";
			} else if (operation.isReturningArray()) {
				returnType = "IList";
			}

		}

		sb.append(operation.getName());
		sb.append('(');
		sb.append(getFlexServiceMethodParameterList(operation));
		sb.append(')');
		sb.append(':');
		sb.append(returnType);
		return sb.toString();
	}

	/**
	 * Get service method parameter list in flex (ActionScript) syntax.
	 */
	public static String getFlexServiceMethodParameterList(IOperation operation) {
		StringBuffer sb = new StringBuffer();
		Iterator<IParameter> i = operation.getParameters().iterator();
		while (i.hasNext()) {
			IParameter p = i.next();
			String type = getFlexServiceParameterTypeFromClassifier(p
					.getClassifier(), p.isArray());
			if (type == null || "".equals(type)) {
				type = p.getType();
				if (type != null && !"".equals(type)
						&& !Character.isLowerCase(type.charAt(0))
						&& !"String".equals(type)) {
					l
							.warning("No classifier associated with parameter "
									+ p.getName()
									+ " (type "
									+ type
									+ "). This is OK for generic types like int or String. For types defined in model ensure, that parameter type is defined by reference in model (not by string name of type).");
				}
			}
			sb.append(p.getName());
			sb.append(':');
			sb.append(type);
			if (i.hasNext()) {
				sb.append(", ");
			}
		}
		return sb.toString();
	}

	private static String getJavaServiceParameterTypeFromClassifier(
			IElement element, boolean isCollection) {
		if (element == null) {
			return null;
		}
		String type;
		if ("MDADomainObject".equals(element.getStereotype())
				|| "MDAValueObject".equals(element.getStereotype())) {
			type = element.getName() + "VO";
		} else if ("MDADomainType".equals(element.getStereotype())) {
			type = element.getProperties().get("java.dataType");
		} else {
			type = element.getName();
		}
		if (isCollection) {
			type = "Collection<" + type + ">";
		}
		return type;
	}

	private static String getFlexServiceParameterTypeFromClassifier(
			IElement element, boolean isCollection) {
		if (element == null) {
			return null;
		}
		String type;
		if ("MDADomainObject".equals(element.getStereotype())
				|| "MDAValueObject".equals(element.getStereotype())) {
			type = element.getName() + "VO";
		} else if ("MDADomainType".equals(element.getStereotype())) {
			type = element.getProperties().get("flex.dataType");
		} else {
			type = element.getName();
		}
		if (isCollection) {
			type = "IList";
		}
		return type;
	}
	
	public static String getPropertyFromParent(IElement element, String property) {
		String result = null;
		if (element.getProperties() != null) {
			result = element.getProperties().get(property);
		}
		while (result == null && element.getParent() != null) {
			element = element.getParent();
			if (element.getProperties() != null) {
				result = element.getProperties().get(property);
			}
		}
		return result;
	}
	
	public static IElement getMetafacade(String uri) {
		return MetafacadeBuilder.getMetafacadeBuilder().getMetafacade(uri);
	}
	
	public static String getPath(IElement e, String rootStereotype,String separator) {
		if (e == null) {
			return "";
		}
		if ((rootStereotype != null) && rootStereotype.equals(e.getStereotype())) {
			return e.getName();
		} else {
			String parent = getPath(e.getParent(), rootStereotype,separator);
			if ("".equals(parent)) {
				return e.getName();
			} else {
				return parent + separator + e.getName();
			}
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List sort(Collection c) {
		ArrayList res=new ArrayList(c);
		Collections.sort(res);
		return res;
	}
	
	public static IRelation findFirstRelationByType(Collection<IRelation> relations,String type) {
		if (relations==null) {
			return null;
		}
		for (IRelation iRelation : relations) {
			if (iRelation.getType().equals(type)) {
				return iRelation;
			}
		}
		return null;
	}
	
}
