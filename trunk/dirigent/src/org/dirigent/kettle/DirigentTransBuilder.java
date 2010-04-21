package org.dirigent.kettle;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.pentaho.di.core.Const;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.core.logging.LogWriter;
import org.pentaho.di.core.plugins.PluginRegistry;
import org.pentaho.di.core.plugins.StepPluginType;
import org.pentaho.di.trans.TransMeta;
import org.pentaho.di.trans.step.StepMeta;
import org.pentaho.di.trans.step.StepMetaInterface;
import org.pentaho.di.trans.steps.tableinput.TableInputMeta;

public class DirigentTransBuilder {
	public static final String[] databasesXML = {
        "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
        "<connection>" +
            "<name>target</name>" +
            "<server>localhost</server>" +
            "<type>MSSQL</type>" +
            "<access>Native</access>" +
            "<database>test</database>" +
            "<port>1433</port>" +
            "<username>matt</username>" +
            "<password>abcd</password>" +
          "</connection>",
          
          "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
          "<connection>" +
              "<name>source</name>" +
              "<server>localhost</server>" +
              "<type>MYSQL</type>" +
              "<access>Native</access>" +
              "<database>test</database>" +
              "<port>3306</port>" +
              "<username>matt</username>" +
              "<password>abcd</password>" +
            "</connection>"  
    };
	
	public static void run() throws KettleException {
		KettleEnvironment.init();
        
    	// Init the logging...
        LogWriter.getInstance("TransBuilder.log", true, LogWriter.LOG_LEVEL_DETAILED);
                
        
        
        // The parameters we want, optionally this can be 
        String fileName = "NewTrans.xml";
        String transformationName = "Test Transformation";
        String sourceDatabaseName = "source";
        String sourceTableName = "Customer";
        String sourceFields[] = { 
                "customernr",
                "Name",
                "firstname",
                "lang",
                "sex",
                "street",
                "housnr",
                "bus",
                "zipcode",
                "location",
                "country",
                "date_of_birth"
            };

        String targetDatabaseName = "target";
        String targetTableName = "Cust";
        String targetFields[] = { 
                "CustNo",
                "LastName",
                "FirstName",
                "Lang",
                "gender",
                "Street",
                "Housno",
                "busno",
                "ZipCode",
                "City",
                "Country",
                "BirthDate"
            };
        
        TransMeta transMeta = buildTransformation(transformationName, sourceDatabaseName, targetDatabaseName, sourceTableName, sourceFields, targetTableName, targetFields);
        
        String xml = transMeta.getXML();
        DataOutputStream dos;
		try {
			dos = new DataOutputStream(new FileOutputStream(new File(fileName)));
			dos.write(xml.getBytes("UTF-8"));
			dos.close();
        System.out.println("Saved transformation to file: "+fileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	
	/**
	 * creates new transformation 
	 * @param transformationName
	 * @throws KettleException 
	 */
	private static TransMeta buildTransformation(String transformationName, String sourceDatabaseName, String targetDatabaseName, String sourceTableName, String[] sourceFields,  String targetTableName, String[] targetFields) throws KettleException {
		TransMeta transMeta = new TransMeta(); 
		transMeta.setName(transformationName); 
		
		// Add the database connections
        for (int i=0;i<databasesXML.length;i++)
        {
            DatabaseMeta databaseMeta = new DatabaseMeta(databasesXML[i]);
            transMeta.addDatabase(databaseMeta);
        }
        
        DatabaseMeta sourceDBInfo = transMeta.findDatabase(sourceDatabaseName);
        DatabaseMeta targetDBInfo = transMeta.findDatabase(targetDatabaseName);
		        
        // Create source step and draw it 
        String fromstepname = "read from [" + sourceTableName + "]";
        TableInputMeta tii = new TableInputMeta();
        tii.setDatabaseMeta(sourceDBInfo);
        String selectSQL = "SELECT "+Const.CR;
        for (int i=0;i<sourceFields.length;i++)
        {
            if (i>0) selectSQL+=", "; else selectSQL+="  ";
            selectSQL+=sourceFields[i]+Const.CR;
        }
        selectSQL+="FROM "+sourceTableName;
        tii.setSQL(selectSQL);
        
        PluginRegistry registry = PluginRegistry.getInstance();
        
        String fromstepid = registry.getPluginId(StepPluginType.class, tii);
        StepMeta fromstep = new StepMeta(fromstepid, fromstepname, (StepMetaInterface) tii);
        fromstep.setLocation(150, 100);
        fromstep.setDraw(true);
        fromstep.setDescription("Reads information from table [" + sourceTableName + "] on database [" + sourceDBInfo + "]");
        transMeta.addStep(fromstep);
        
		return transMeta; 
	}
	

}
