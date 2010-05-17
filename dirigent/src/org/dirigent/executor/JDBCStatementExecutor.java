package org.dirigent.executor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.dirigent.metafacade.IGeneratable;
import org.dirigent.metafacade.ISchema;
import org.dirigent.metafacade.ISchemaProvider;
import org.dirigent.pattern.IPatternStep;

public class JDBCStatementExecutor implements IStepExecutor {

	private Logger l = Logger.getLogger(JDBCStatementExecutor.class.getName());

	@Override
	public void execute(IGeneratable gen, IPatternStep step) {
		if (!(gen instanceof ISchemaProvider)) {
			throw new IllegalArgumentException(
					"Instance of ISchemaProvider is required in argument gen.");
		}
		Connection c = getConnection(((ISchemaProvider) gen).getSchema());
		//set autocommit to false 
		try {
			c.setAutoCommit(false);
		} catch (SQLException e1) {
			l.log(Level.WARNING, "Unable to set autocommit to false. Check your DB settings.", e1);
		} 
		String sql = TemplateHelper.generateTemplate(gen, step);
		try {
			PreparedStatement stmt = c.prepareStatement(sql);
			int rowsAffected = stmt.executeUpdate();
			stmt.close();
			c.commit();
			l.info("Step " + step.getName() + " executed sucessfully. "
					+ rowsAffected + " rows affected.");
		} catch (SQLException e) {
			try {
				c.rollback();
			} catch (SQLException ee) {
				l.log(Level.WARNING, "Unable to rollback transaction.", ee);
			}

			throw new RuntimeException("SQLException while executing step "
					+ step.getName(), e);

		}

	}

	private Connection getConnection(ISchema schema) {
		try {
			Class.forName(schema.getJdbcDriver());
			return DriverManager.getConnection(schema.getJdbcUrl(), schema
					.getUsername(), schema.getPassword());
		} catch (Exception e) {
			throw new RuntimeException(
					"Unable to create connection for schema URI: "
							+ schema.getUri() + "/n details: " + e.getMessage());
		}
	}

}
