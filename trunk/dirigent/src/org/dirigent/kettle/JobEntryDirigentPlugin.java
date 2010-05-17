package org.dirigent.kettle;

import java.util.List;
import java.util.logging.Handler;
import java.util.logging.Level;

import java.util.logging.LogRecord;
import java.util.logging.Logger;

import org.dirigent.Dirigent;
import org.dirigent.config.DirigentConfig;

import org.pentaho.di.cluster.SlaveServer;
import org.pentaho.di.core.Result;
import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.core.exception.KettleDatabaseException;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.core.exception.KettleXMLException;
import org.pentaho.di.core.xml.XMLHandler;
import org.pentaho.di.job.entry.JobEntryBase;
import org.pentaho.di.job.entry.JobEntryInterface;
import org.pentaho.di.repository.ObjectId;
import org.pentaho.di.repository.Repository;
import org.w3c.dom.Node;

public class JobEntryDirigentPlugin extends JobEntryBase implements Cloneable,
		JobEntryInterface {

	private static final String MODEL = "model";
	private static final String URI = "uri";
	private static final String MODEL_TYPE = "modelType";
	private String model;
	private String uri;
	private String modelType;

	public JobEntryDirigentPlugin(String n) {
		super(n, "");
		setID(-1L);
	}

	public JobEntryDirigentPlugin() {
		this("");
	}

	public Object clone() {
		JobEntryDirigentPlugin je = (JobEntryDirigentPlugin) super.clone();
		return je;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public Object getValue() {
		return null;
	}

	public String getModelType() {
		return modelType;
	}

	public void setModelType(String modelType) {
		this.modelType = modelType;
	}

	@Override
	public String getXML() {
		StringBuffer retval = new StringBuffer();

		retval.append(super.getXML());

		retval.append("      " + XMLHandler.addTagValue(MODEL, model));
		retval.append("      " + XMLHandler.addTagValue(URI, uri));
		retval.append("      " + XMLHandler.addTagValue(MODEL_TYPE, modelType));
		return retval.toString();
	}

	@Override
	public void loadXML(Node entrynode, List<DatabaseMeta> databases,
			List<SlaveServer> slaveServers, Repository rep)
			throws KettleXMLException {
		try {
			super.loadXML(entrynode, databases, slaveServers);
			model = XMLHandler.getTagValue(entrynode, MODEL);
			uri = XMLHandler.getTagValue(entrynode, URI);
			modelType = XMLHandler.getTagValue(entrynode, MODEL_TYPE);
		} catch (KettleXMLException xe) {
			throw new KettleXMLException(
					"Unable to load file exists job entry from XML node", xe);
		}
	}

	@Override
	public void loadRep(Repository rep, ObjectId id_jobentry,
			List<DatabaseMeta> databases, List<SlaveServer> slaveServers)
			throws KettleException {
		try {
			super.loadRep(rep, id_jobentry, databases, slaveServers);
			model = rep.getJobEntryAttributeString(id_jobentry, MODEL);
			uri = rep.getJobEntryAttributeString(id_jobentry, URI);
			modelType = rep.getJobEntryAttributeString(id_jobentry, MODEL_TYPE);

		} catch (KettleException dbe) {
			throw new KettleException(
					"Unable to load job entry for type file exists from the repository for id_jobentry="
							+ id_jobentry, dbe);
		}
	}

	@Override
	public void saveRep(Repository rep, ObjectId id_job) throws KettleException {
		try {
			super.saveRep(rep, id_job);
			rep.saveJobEntryAttribute(id_job, getObjectId(), MODEL, model);
			rep.saveJobEntryAttribute(id_job, getObjectId(), URI, uri);
			rep.saveJobEntryAttribute(id_job, getObjectId(), MODEL_TYPE,
					modelType);

		} catch (KettleDatabaseException dbe) {
			throw new KettleException(
					"unable to save jobentry of type 'file exists' to the repository for id_job="
							+ id_job, dbe);
		}
	}

	@Override
	public Result execute(Result prevResult, int nr) throws KettleException {

		Logger logger = Logger.getLogger("org.dirigent");
		Handler lmh = new Handler() {

			@Override
			public void publish(LogRecord record) {
				int logLevel = record.getLevel().intValue();
				String message = record.getMessage();
				
				if (logLevel == 1000) {
					logError(message); 
				}
				if (logLevel == 900) {
					logMinimal(message); 
				}
				if (logLevel == 800) {
					logBasic(message); 
				}  
				if (logLevel < 800 && logLevel >= 500) {
					logDetailed(message); 
				}
				if (logLevel < 500 && logLevel >= 300) {
					logDebug(message); 
				}
				if (logLevel < 300) {
					logRowlevel(message);
				}
				

			}

			@Override
			public void flush() {
			}

			@Override
			public void close() throws SecurityException {
			}
		};

		logger.addHandler(lmh);

		Result result = new Result(nr);
		result.setResult(false);

		logger.log(Level.INFO, "Starting DIRIGENT Job ");

		try {

			System.setProperty(DirigentConfig.MODEL_PATH, model);
			System.setProperty("dirigent.model.type", modelType);
			String[] args = { model, uri };

			Dirigent.main(args);

		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage() + " caused by " + e.getCause()); 
			result.setNrErrors(result.getEntryNr() + 1);
			result.setResult(false);
			return result;

		}

		result.setResult(true);
		logger.log(Level.INFO, "Finishing DIRIGENT Job with "
				+ result.getNrErrors() + " errors");
		return result;
	}

}