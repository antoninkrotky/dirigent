package org.dirigent.metafacade.builder.ea;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.dirigent.config.DirigentConfig;

public class EAHelper {

	private static Logger l = Logger.getLogger(EAHelper.class.getName());
	private static EAHelper instance = new EAHelper();

	private Map<String, Connection> connectionPool=new HashMap<String, Connection>();

	public Connection getConnection() {
		String name=DirigentConfig.getDirigentConfig().getProperty(DirigentConfig.MODEL_PATH);
		Connection c = connectionPool.get(name);
		try {
			if (c != null && !c.isClosed()) {
				return c;
			}
		} catch (SQLException e) {
			l.log(Level.WARNING, "Connectin " + name + " is invalid.", e);
		}
		c = createConnection(name);
		connectionPool.put(name, c);
		return c;
	}

	private Connection createConnection(String name) {
		try {	
			l.info("Creating repository connection: "+"jdbc:odbc:"+name);
			return DriverManager.getConnection("jdbc:odbc:"+name);
		} catch (Exception e) {
			throw new RuntimeException("Unable to create jdbc connection.", e);
		}
	}

	public static EAHelper instance() {
		return instance;
	}
	
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
		Iterator<Connection> i=connectionPool.values().iterator();
		while (i.hasNext()) {
			Connection c=i.next();
			connectionPool.remove(c);
			try {
				c.close();
			} catch (SQLException e) {
				l.log(Level.WARNING,"Unable to close connection.",e);
			}
		}
	}

}
