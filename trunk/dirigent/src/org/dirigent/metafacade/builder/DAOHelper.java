package org.dirigent.metafacade.builder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.dirigent.config.DirigentConfig;
/**
 * Helper for JDBC DAO implementations.
 * */
public class DAOHelper {

	private static Logger l = Logger.getLogger(DAOHelper.class.getName());
	private static DAOHelper instance = new DAOHelper();

	private Map<String, Connection> connectionPool=new HashMap<String, Connection>();

	public Connection getConnection() {
		String name=DirigentConfig.getDirigentConfig().getProperty(DirigentConfig.MODEL_PATH);
		Connection c = connectionPool.get(name);
		try {
			if (c != null && !c.isClosed()) {
				return c;
			}
		} catch (SQLException e) {
			l.log(Level.WARNING, "Connection " + name + " is invalid.", e);
		}
		c = createConnection(name);
		connectionPool.put(name, c);
		return c;
	}

	private Connection createConnection(String name) {
		try {	
			String driver=DirigentConfig.getDirigentConfig().getProperty(DirigentConfig.MODEL_DRIVER);
			String url=DirigentConfig.getDirigentConfig().getProperty(DirigentConfig.MODEL_URL);
			String user=DirigentConfig.getDirigentConfig().getProperty(DirigentConfig.MODEL_USER);
			String password=DirigentConfig.getDirigentConfig().getProperty(DirigentConfig.MODEL_PASSWORD);
			
			//Create ODBC connection for backward compatibility
			if (url==null) {
				l.info("Creating model repository connection: "+"jdbc:odbc:"+name);
			return DriverManager.getConnection("jdbc:odbc:"+name);
			}
			
			l.info("Creating model repository connection:" +url);
			Class.forName(driver);
			return DriverManager.getConnection(url, user, password);
			
		} catch (Exception e) {
			throw new RuntimeException("Unable to create jdbc connection.", e);
		}
	}

	public static DAOHelper instance() {
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
