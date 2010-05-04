package org.dirigent.kettle;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.dirigent.metafacade.builder.vo.SchemaVO;
import org.pentaho.di.core.Const;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.database.DatabaseInterface;
import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.core.logging.LogChannel;
import org.pentaho.di.core.logging.LogWriter;
import org.pentaho.di.core.plugins.PluginRegistry;
import org.pentaho.di.core.plugins.StepPluginType;
import org.pentaho.di.trans.TransHopMeta;
import org.pentaho.di.trans.TransMeta;
import org.pentaho.di.trans.step.StepMeta;
import org.pentaho.di.trans.step.StepMetaInterface;
import org.pentaho.di.trans.steps.selectvalues.SelectValuesMeta;
import org.pentaho.di.trans.steps.tableinput.TableInputMeta;
import org.pentaho.di.trans.steps.tableoutput.TableOutputMeta;

public class DirigentTransBuilder {
	private String outputFileName = "NewTrans.xml";
	private TransMeta transMeta = new TransMeta();
	private LogChannel log = new LogChannel(this); 
	
	public void createDBConnection(SchemaVO schema) 
	throws KettleException {
		KettleEnvironment.init();
		
		log.logDetailed("Creating database connection " + schema.uri); 
		
		
		DatabaseMeta databaseMeta = new DatabaseMeta(); 
		databaseMeta.setName(schema.uri); 
		databaseMeta.setName(schema.name); 
		databaseMeta.setUsername(schema.userName); 
		databaseMeta.setPassword(schema.password); 
		String[] parsedUrl = parseUrl(schema.jdbcUrl); 
		databaseMeta.setHostname(parsedUrl[0]); 
		databaseMeta.setAccessType(DatabaseMeta.getAccessType(parsedUrl[2])); 
		databaseMeta.setDBPort(parsedUrl[1]);
		databaseMeta.setDatabaseType(parsedUrl[3]); 
		
		transMeta.addDatabase(databaseMeta); 
	}
	
	/**
	 * I need to create regular expression to parse many different urls   
	 * @todo create regular expression   
	 * @param String url
	 * @return String[]
	 */
	private String[] parseUrl(String url) {
		String[] tempArray = {"localhost", "3306", "JDBC", "HSQLDB"}; 
		return tempArray; 
	}

	public void finish() throws KettleException {
		String xml = transMeta.getXML();
		DataOutputStream dos;
		try {
			dos = new DataOutputStream(new FileOutputStream(new File(
					outputFileName)));
			dos.write(xml.getBytes("UTF-8"));
			dos.close();
			System.out.println("Saved transformation to file: "
					+ outputFileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * creates new transformation
	 * 
	 * @param transformationName
	 * @throws KettleException
	 */
	private TransMeta buildTransformation(String transformationName,
			String sourceDatabaseName, String targetDatabaseName,
			String sourceTableName, String[] sourceFields,
			String targetTableName, String[] targetFields)
			throws KettleException {
		TransMeta transMeta = new TransMeta();
		transMeta.setName(transformationName);

		
		DatabaseMeta sourceDBInfo = transMeta.findDatabase(sourceDatabaseName);
		DatabaseMeta targetDBInfo = transMeta.findDatabase(targetDatabaseName);

		// Create source step and draw it
		String fromstepname = "read from [" + sourceTableName + "]";
		TableInputMeta tii = new TableInputMeta();
		tii.setDatabaseMeta(sourceDBInfo);
		String selectSQL = "SELECT " + Const.CR;
		for (int i = 0; i < sourceFields.length; i++) {
			if (i > 0)
				selectSQL += ", ";
			else
				selectSQL += "  ";
			selectSQL += sourceFields[i] + Const.CR;
		}
		selectSQL += "FROM " + sourceTableName;
		tii.setSQL(selectSQL);

		PluginRegistry registry = PluginRegistry.getInstance();

		String fromstepid = registry.getPluginId(StepPluginType.class, tii);
		StepMeta fromstep = new StepMeta(fromstepid, fromstepname,
				(StepMetaInterface) tii);
		fromstep.setLocation(150, 100);
		fromstep.setDraw(true);
		transMeta.addStep(fromstep);

		// add logic to rename fields
		// Use metadata logic in SelectValues, use SelectValueInfo...
		//
		SelectValuesMeta svi = new SelectValuesMeta();
		svi.allocate(0, 0, sourceFields.length);
		svi.setDefault();

		for (int i = 0; i < sourceFields.length; i++) {
			svi.getMeta()[i].setName(sourceFields[i]);
			svi.getMeta()[i].setRename(targetFields[i]);
		}

		String selstepname = "Rename field names";
		String selstepid = registry.getPluginId(StepPluginType.class, svi);
		StepMeta selstep = new StepMeta(selstepid, selstepname,
				(StepMetaInterface) svi);
		selstep.setLocation(350, 100);
		selstep.setDraw(true);
		transMeta.addStep(selstep);

		// hope from source table to selstep
		TransHopMeta shi = new TransHopMeta(fromstep, selstep);
		transMeta.addTransHop(shi);
		fromstep = selstep;

		// Add the TableOutputMeta step...
		//
		String tostepname = "write to [" + targetTableName + "]";
		TableOutputMeta toi = new TableOutputMeta();
		toi.setDatabaseMeta(targetDBInfo);
		toi.setTablename(targetTableName);
		toi.setCommitSize(200);
		toi.setTruncateTable(true);
		String tostepid = registry.getPluginId(StepPluginType.class, toi);
		StepMeta tostep = new StepMeta(tostepid, tostepname,
				(StepMetaInterface) toi);
		tostep.setLocation(550, 100);
		tostep.setDraw(true);
		transMeta.addStep(tostep);

		//
		// Add a hop between the two steps...
		//
		TransHopMeta hi = new TransHopMeta(fromstep, tostep);
		transMeta.addTransHop(hi);

		return transMeta;
	}

}
