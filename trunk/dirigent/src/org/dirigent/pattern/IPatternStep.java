package org.dirigent.pattern;

public interface IPatternStep {
	public static String FILE="File";
	public static String JDBC_STATEMENT="JDBCStatement";
	public String getName();
	public String getType();
	public String getTemplate();
	public String getCondition();
	public boolean isIgnoreErrors();
	public String getParameter(String string);
	public String getParameter(String string, String defaultValue);
}
