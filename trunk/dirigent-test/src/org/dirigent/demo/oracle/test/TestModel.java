package org.dirigent.demo.oracle.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import junit.framework.TestCase;

import org.dirigent.config.DirigentConfig;
import org.dirigent.generator.Generator;

/**
 * Tests of dirigent-demo/oracle model.
 * */
public class TestModel extends TestCase {
	
	protected final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	protected final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521/xe";
	protected final String USERNAME = "dirigent_demo_dm";
	protected final String PASSWORD = "dirigent_demo_dm";
	protected final boolean OUTPUT = true;
	protected Connection connection = null;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		System.setProperty("dirigent.home", "../dirigent-demo/oracle/config");
		System.setProperty("dirigent.model.type", "EA");
		DirigentConfig.resetConfig();
		
		// create tables
		//executeFile("results/SCD/sql/createTables.sql");
		// insert data into tables
		//executeFile("results/SCD/sql/insertIntoTablesRun1.sql");
		executeFile("../dirigent-demo/oracle/data-model/L0/create.sql");
		executeFile("../dirigent-demo/oracle/data-model/L0/data01.sql");
		executeFile("../dirigent-demo/oracle/data-model/L2/D_CUSTOMER.create.sql");
		executeFile("../dirigent-demo/oracle/data-model/L2/D_INVOICE.create.sql");
		executeFile("../dirigent-demo/oracle/data-model/L2/D_PRODUCT.create.sql");
		executeFile("../dirigent-demo/oracle/data-model/L2/D_TIME.create.sql");
	}
	
	@Override
	protected void tearDown() throws Exception {
		
		// delete from tables
		//executeFile("results/SCD/sql/deleteFromTables.sql");
		// drop all tables
		//executeFile("results/SCD/sql/dropTables.sql");
		executeFile("../dirigent-demo/oracle/data-model/L2/D_TIME.drop.sql");
		executeFile("../dirigent-demo/oracle/data-model/L2/D_PRODUCT.drop.sql");
		executeFile("../dirigent-demo/oracle/data-model/L2/D_INVOICE.drop.sql");
		executeFile("../dirigent-demo/oracle/data-model/L2/D_CUSTOMER.drop.sql");
		executeFile("../dirigent-demo/oracle/data-model/L0/drop.sql");
		
		super.tearDown();
	}

	public void testCustomer() {
		Generator.generate("{A0848078-956D-4833-AF60-E8FE9794BE1B}");

		executeFile("data-model/SCD/CUSTOMER.doscd.sql");
		exportIntoCSV("results/SCD/d_customer.csv", "D_CUSTOMER");
		
		/*try {
			FileInputStream expected = new FileInputStream(new File("results/SCD/expected/d_customerExpected.csv"));
			FileInputStream generated = new FileInputStream(new File("results/SCD/d_customer.csv"));
			
			FileComparator.assertEquals(expected, generated);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}*/
	}
	
	protected void exportIntoCSV(String filename, String table) {
		File file = new File(filename);
		
		Connection conn = getConnection();
		Statement stmt = null;
		FileWriter fw = null;
		try {
			fw = new FileWriter(file);
			stmt = conn.createStatement();
			String query = "SELECT * FROM "+ table +"";
			ResultSet rs = stmt.executeQuery(query);
			ResultSetMetaData rsMetaData = rs.getMetaData();
			int numberOfColumns = rsMetaData.getColumnCount();
			
			for (int i = 1; i < numberOfColumns + 1; i++) {
				String columnName = rsMetaData.getColumnName(i);
				if (!columnName.equals("CUST_VALID_FROM_DATETIME") && 
					!columnName.equals("CUST_VALID_TO_DATETIME") &&
					!columnName.equals("CUST_UPDATED_DATETIME")) {
					fw.append("\""+ columnName +"\",");
				}
			}
			fw.append("\n");
			
			while (rs.next()) {
				for (int i = 1; i < numberOfColumns + 1; i++) {
					String columnName = rs.getMetaData().getColumnName(i); 
					if (!columnName.equals("CUST_VALID_FROM_DATETIME") && 
						!columnName.equals("CUST_VALID_TO_DATETIME") &&
						!columnName.equals("CUST_UPDATED_DATETIME")) {
						fw.append("\""+ rs.getString(i) +"\",");
					}
				}
				fw.append("\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fw != null) {
				try {
					fw.flush();
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		System.out.println("Generated file: "+ filename);
	}
	
	protected Connection getConnection() {
		if (connection == null) {
			try {
				Class.forName(JDBC_DRIVER);
				connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return connection;
	}
	
	protected void executeFile(String file) {
		StringBuffer commands = new StringBuffer();
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(new File(file));
			LineNumberReader lineReader = new LineNumberReader(fileReader);
			String line = null;
			while ((line = lineReader.readLine()) != null) {
				line = line.trim();
				if (line.startsWith("--") || line.startsWith("//")) {
					// Do nothing
				} else {
					commands.append(line);
					commands.append(" ");
				}
			}
			String strCommands = commands.toString();
			strCommands = strCommands.replaceAll("(?:/\\*(?:[^*]|(?:\\*+[^*/]))*\\*+/)|(?://.*)", ""); // Remove /* */ comments
			strCommands = strCommands.replaceAll("\\s+", " "); // remove multiple spaces
			
			String[] commandsArray= strCommands.toString().split(";");  
			
			Connection conn = getConnection();
			Statement stmt = conn.createStatement();
			
			for(int i = 0; i < commandsArray.length; i++) {
				String command = commandsArray[i]; 
				if (!command.trim().equals("")) {
					if (this.OUTPUT) {
						System.out.println(command +";");
					}
					stmt.execute(command);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (fileReader != null) {
				try {
					fileReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
