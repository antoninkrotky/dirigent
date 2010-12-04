package org.dirigent.demo.oracle.test;

import java.io.File;
import java.io.FileInputStream;
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
import org.dirigent.test.utils.FileComparator;

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
		executeFile("../dirigent-demo/oracle/data-model/L2/F_SALES.create.sql");
	}
	
	@Override
	protected void tearDown() throws Exception {
		
		// delete from tables
		//executeFile("results/SCD/sql/deleteFromTables.sql");
		// drop all tables
		//executeFile("results/SCD/sql/dropTables.sql");
		executeFile("../dirigent-demo/oracle/data-model/L2/F_SALES.drop.sql");
		executeFile("../dirigent-demo/oracle/data-model/L2/D_TIME.drop.sql");
		executeFile("../dirigent-demo/oracle/data-model/L2/D_PRODUCT.drop.sql");
		executeFile("../dirigent-demo/oracle/data-model/L2/D_INVOICE.drop.sql");
		executeFile("../dirigent-demo/oracle/data-model/L2/D_CUSTOMER.drop.sql");
		executeFile("../dirigent-demo/oracle/data-model/L0/drop.sql");
		closeConnection();
		
		super.tearDown();
	}

	public void testCustomerCreate() {
		Generator.generate("{A0848078-956D-4833-AF60-E8FE9794BE1B}");
		exportIntoCSV("results/SCD/d_customer.csv", "D_CUSTOMER");
		
		try {
			FileInputStream expected = new FileInputStream(new File("results/SCD/expected/d_customerExpected.csv"));
			FileInputStream generated = new FileInputStream(new File("results/SCD/d_customer.csv"));
			
			FileComparator.assertEquals(expected, generated);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		// update and delete
		executeFile("results/SCD/sql/data2.sql");
		Generator.generate("{A0848078-956D-4833-AF60-E8FE9794BE1B}");
		exportIntoCSV("results/SCD/d_customer2.csv", "D_CUSTOMER");
		
		try {
			FileInputStream expected = new FileInputStream(new File("results/SCD/expected/d_customer2Expected.csv"));
			FileInputStream generated = new FileInputStream(new File("results/SCD/d_customer2.csv"));
			
			FileComparator.assertEquals(expected, generated);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void testInvoice() {
		Generator.generate("{62D7D3A6-90F4-4056-90F0-BA9F41A097EF}");
		exportIntoCSV("results/SCD/d_invoice.csv", "D_INVOICE");
		
		try {
			FileInputStream expected = new FileInputStream(new File("results/SCD/expected/d_invoiceExpected.csv"));
			FileInputStream generated = new FileInputStream(new File("results/SCD/d_invoice.csv"));
			
			FileComparator.assertEquals(expected, generated);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		// update and delete
		executeFile("results/SCD/sql/data2.sql");
		Generator.generate("{62D7D3A6-90F4-4056-90F0-BA9F41A097EF}");
		exportIntoCSV("results/SCD/d_invoice2.csv", "D_INVOICE");
		
		try {
			FileInputStream expected = new FileInputStream(new File("results/SCD/expected/d_invoice2Expected.csv"));
			FileInputStream generated = new FileInputStream(new File("results/SCD/d_invoice2.csv"));
			
			FileComparator.assertEquals(expected, generated);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void testProduct() {
		Generator.generate("{4746A82F-EAE7-4477-8B77-F05C71BE08B8}");
		exportIntoCSV("results/SCD/d_product.csv", "D_PRODUCT");
		
		try {
			FileInputStream expected = new FileInputStream(new File("results/SCD/expected/d_productExpected.csv"));
			FileInputStream generated = new FileInputStream(new File("results/SCD/d_product.csv"));
			
			FileComparator.assertEquals(expected, generated);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		// update and delete
		executeFile("results/SCD/sql/data2.sql");
		Generator.generate("{4746A82F-EAE7-4477-8B77-F05C71BE08B8}");
		exportIntoCSV("results/SCD/d_product2.csv", "D_PRODUCT");
		
		try {
			FileInputStream expected = new FileInputStream(new File("results/SCD/expected/d_product2Expected.csv"));
			FileInputStream generated = new FileInputStream(new File("results/SCD/d_product2.csv"));
			
			FileComparator.assertEquals(expected, generated);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void testTime() {
		Generator.generate("{D11D5BA3-61BF-4f2a-98A5-34DCA8B877A8}");
		exportIntoCSV("results/SCD/d_time.csv", "D_TIME");
		
		try {
			FileInputStream expected = new FileInputStream(new File("results/SCD/expected/d_timeExpected.csv"));
			FileInputStream generated = new FileInputStream(new File("results/SCD/d_time.csv"));
			
			FileComparator.assertEquals(expected, generated);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		// update and delete
		executeFile("results/SCD/sql/data2.sql");
		Generator.generate("{D11D5BA3-61BF-4f2a-98A5-34DCA8B877A8}");
		exportIntoCSV("results/SCD/d_time2.csv", "D_TIME");
		
		try {
			FileInputStream expected = new FileInputStream(new File("results/SCD/expected/d_time2Expected.csv"));
			FileInputStream generated = new FileInputStream(new File("results/SCD/d_time2.csv"));
			
			FileComparator.assertEquals(expected, generated);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void testSales() {
		// makde dimensions
		Generator.generate("{A0848078-956D-4833-AF60-E8FE9794BE1B}");
		Generator.generate("{62D7D3A6-90F4-4056-90F0-BA9F41A097EF}");
		Generator.generate("{4746A82F-EAE7-4477-8B77-F05C71BE08B8}");
		Generator.generate("{D11D5BA3-61BF-4f2a-98A5-34DCA8B877A8}");
		
		Generator.generate("{6C513D3D-0C0A-4664-B505-64226C5FC01B}");
		exportIntoCSV("results/SCD/f_sales.csv", "F_SALES");
		
		try {
			FileInputStream expected = new FileInputStream(new File("results/SCD/expected/f_salesExpected.csv"));
			FileInputStream generated = new FileInputStream(new File("results/SCD/f_sales.csv"));
			
			FileComparator.assertEquals(expected, generated);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		// update and delete
		executeFile("results/SCD/sql/data2.sql");
		// makde dimensions
		Generator.generate("{A0848078-956D-4833-AF60-E8FE9794BE1B}");
		Generator.generate("{62D7D3A6-90F4-4056-90F0-BA9F41A097EF}");
		Generator.generate("{4746A82F-EAE7-4477-8B77-F05C71BE08B8}");
		Generator.generate("{D11D5BA3-61BF-4f2a-98A5-34DCA8B877A8}");
		
		Generator.generate("{6C513D3D-0C0A-4664-B505-64226C5FC01B}");
		exportIntoCSV("results/SCD/f_sales2.csv", "F_SALES");
		
		try {
			FileInputStream expected = new FileInputStream(new File("results/SCD/expected/f_sales2Expected.csv"));
			FileInputStream generated = new FileInputStream(new File("results/SCD/f_sales2.csv"));
			
			FileComparator.assertEquals(expected, generated);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	
	protected void delay() {
		try {
			Thread.sleep(1000);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
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
				if ( (!columnName.endsWith("_VALID_FROM_DATETIME") && 
						!columnName.endsWith("_VALID_TO_DATETIME") && 
						!columnName.endsWith("_UPDATED_DATETIME"))) {
					fw.append("\""+ columnName +"\",");
				}
			}
			fw.append("\n");
			
			while (rs.next()) {
				for (int i = 1; i < numberOfColumns + 1; i++) {
					String columnName = rs.getMetaData().getColumnName(i); 
					if (!columnName.endsWith("_VALID_FROM_DATETIME") && 
						!columnName.endsWith("_VALID_TO_DATETIME") &&
						!columnName.endsWith("_UPDATED_DATETIME")) {
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
			//System.out.println("----------------------------\ncreate connection\n----------------------------");
			try {
				while ( connection == null ) {
					try {
						Class.forName(JDBC_DRIVER);
						connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
					} catch (SQLException e) {
						//System.out.println(e.getMessage());
						//System.out.println(e.getClass());
						//System.exit(1);
						if (e.getMessage().equals("Io exception: The Network Adapter could not establish the connection")) {
							// System.exit(1);
							// delay();
							continue;
						} else {
							throw e;
						}
					}
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return connection;
	}
	
	protected void closeConnection() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
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
