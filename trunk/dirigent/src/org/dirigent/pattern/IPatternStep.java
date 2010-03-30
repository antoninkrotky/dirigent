package org.dirigent.pattern;

public interface IPatternStep {
	public static String FILE="File";
	public static String JDBC_STATEMENT="JDBCStatement";
	public String getName();
	public String getType();
	public String getTemplate();
}
