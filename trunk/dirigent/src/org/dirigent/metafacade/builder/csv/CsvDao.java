package org.dirigent.metafacade.builder.csv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

public abstract class CsvDao<V> {

	private Connection connection;

	protected Connection getConnection() {
		if (connection == null) {
			try {
				Class.forName("org.relique.jdbc.csv.CsvDriver");
				connection = DriverManager
						.getConnection("jdbc:relique:csv:resources/model");
			} catch (Exception e) {
				throw new RuntimeException("Unable to create jdbc connection.",
						e);
			}
		}
		return connection;
	}
	
	protected abstract V createVO(ResultSet res) throws SQLException;
	
	protected V findVO(String query) {
		try {
			Statement stmt = getConnection().createStatement();
			ResultSet res = stmt.executeQuery(query);
			V v = null;
			if (res.next()) {
				v = createVO(res);
			}
			res.close();
			stmt.close();
			return v;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}	
	}
	
	protected Collection<V> findVOs(String query) {

		try {
			Statement stmt = getConnection().createStatement();
			ResultSet res = stmt.executeQuery(query);
			ArrayList<V> r = new ArrayList<V>();
			while (res.next()) {				
				r.add(createVO(res));
			}
			res.close();
			stmt.close();
			return r;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
