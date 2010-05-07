package org.dirigent.flex.command;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.dirigent.flex.ICommand;
import org.dirigent.metafacade.ISchema;
import org.dirigent.metafacade.builder.MetafacadeBuilder;

import flex.messaging.io.ArrayCollection;

public class ExecuteSQLQuery implements ICommand {
	public String query;
	public String schemaUri;

	private Connection getConnection(ISchema schema) {
		try {
			Class.forName(schema.getJdbcDriver());
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("JDBC driver " + schema.getJdbcDriver()
					+ " not found.", e);
		}
		try {
			return DriverManager.getConnection(schema.getJdbcUrl(), schema
					.getUsername(), schema.getPassword());
		} catch (SQLException e) {
			throw new RuntimeException("Cannot connect to database.", e);
		}

	}

	@Override
	public Object execute() {
		ISchema schema = (ISchema) MetafacadeBuilder.getMetafacadeBuilder()
				.getMetafacade(schemaUri);
		Connection c = getConnection(schema);
		try {
			PreparedStatement stmt = c.prepareStatement(query);
			ResultSet res = stmt.executeQuery();
			int columnCount=res.getMetaData().getColumnCount();
			ExecuteSQLQueryResult result=new ExecuteSQLQueryResult();
			result.columnNames=new String[columnCount];
			result.rows=new ArrayList<Object[]>(100);
			for (int i = 0; i <result.columnNames.length ; i++) {
				result.columnNames[i]=res.getMetaData().getColumnLabel(i+1);
			}
			
			while (res.next()) {
				Object[] row=new Object[columnCount];
				for (int i = 0; i < row.length; i++) {
					row[i]=res.getObject(i+1);
				}
				result.rows.add(row);
				if (result.rows.size()==100) {
					break;
				}
			}
			return result;
		} catch (SQLException e) {
			throw new RuntimeException("Cannot execute query.", e);
		}
	}

}
