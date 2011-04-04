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
import org.dirigent.metafacade.builder.MetafacadeBuilder;
import org.dirigent.pattern.IPatternStep;

public class JDBCStatementExecutor implements IStepExecutor {

	private Logger l = Logger.getLogger(this.getClass().getName());

	@Override
	public void execute(IGeneratable gen, IPatternStep step) {
		if (!(gen instanceof ISchemaProvider)) {
			throw new IllegalArgumentException(
					"Instance of ISchemaProvider is required in argument gen.");
		}

		ISchema schema;

		if (step.getParameter("schema") != null) {
			schema = (ISchema) MetafacadeBuilder.getMetafacadeBuilder()
					.getMetafacade(step.getParameter("schema"));
		} else {
			schema = ((ISchemaProvider) gen).getSchema();
		}

		Connection c = getConnection(schema);
		// set autocommit to false
		try {
			c.setAutoCommit(false);
		} catch (SQLException e1) {
			l.log(Level.WARNING,
					"Unable to set autocommit to false. Check your DB settings.",
					e1);
		}
		String sql = TemplateHelper.generateTemplate(gen, step);
		try {
			PreparedStatement stmt = c.prepareStatement(sql);
			l.info("Executing: " + sql);
			// Choose execution type based on optional parameter "executionType"
			String executionType = step.getParameter("executionType");
			if (executionType != null && "executeQuery".equals(executionType)) {
				stmt.executeQuery();
				stmt.close();
				c.commit();
			}
			// default
			else {
				int rowsAffected = stmt.executeUpdate();
				stmt.close();
				c.commit();
				PatternExecutionStatistics.getStepStatistics().lastElement()
						.setAffectedRows(rowsAffected);
			}
		} catch (SQLException e) {
			try {
				c.rollback();
			} catch (SQLException ee) {
				l.log(Level.WARNING, "Unable to rollback transaction.", ee);
			}
			l.severe("SQLException while executing step " + step.getName()
					+ ": " + e.getMessage());
			throw new RuntimeException(e);

		} finally {
			this.closeConnection(c);
		}
	}

	private Connection getConnection(ISchema schema) {
		try {
			Class.forName(schema.getJdbcDriver());
			return DriverManager.getConnection(schema.getJdbcUrl(),
					schema.getUsername(), schema.getPassword());
		} catch (Exception e) {
			throw new RuntimeException(
					"Unable to create connection for schema URI: "
							+ schema.getUri() + "\n details: " + e.getMessage(),
					e);
		}
	}

	private void closeConnection(Connection c) {
		try {
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			l.severe("SQLException while closing connection ");
		}
	}

}
