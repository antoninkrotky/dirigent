package org.dirigent.pdi.job.dirigent;

import java.util.Iterator;
import java.util.List;

import org.dirigent.config.DirigentConfig;
import org.dirigent.executor.PatternExecutionStatistics;
import org.dirigent.executor.StepStatistics;
import org.dirigent.generator.Generator;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pentaho.di.job.entry.JobEntryBase#resetErrorsBeforeExecution()
	 */
	@Override
	public boolean resetErrorsBeforeExecution() {
		return false;
	}

	/*
	 * This job supports conditional true/false results.
	 * 
	 * @see org.pentaho.di.job.entry.JobEntryBase#evaluates()
	 */
	@Override
	public boolean evaluates() {
		return true;
	}

	@Override
	public Result execute(Result prevResult, int nr) throws KettleException {

		Result result = prevResult;

		this.log.logBasic("Starting DIRIGENT Job " + getName());

		try {
			if (model != null) {
				System.setProperty(DirigentConfig.MODEL_PATH, model);
			}
			if (modelType != null) {
				System.setProperty(DirigentConfig.MODEL_TYPE, modelType);
			}
			String dirigentConfigName = getVariable("dirigent.config.name");
			if (dirigentConfigName != null) {
				DirigentConfig.setConfigName(dirigentConfigName);
			}
			Generator.generate(uri);
			result.setResult(true);
			long affectedRows=PatternExecutionStatistics.getMaxAffectedRows();
			result.setNrLinesUpdated(affectedRows);
			result.setNrLinesWritten(affectedRows);
			this.log.logBasic("DIRIGENT Job " + getName()
					+ " finished sucefully.");
			logStepStatistics();
		} catch (Throwable t) {
			result.setNrErrors(1);
			logException("Exception executing Dirigent.", t);
		}
		if (result.getNrErrors() == 0) {
			result.setResult(true);
		} else {
			result.setResult(false);
		}
		return result;
	}

	private void logException(String message, Throwable t) {
		this.log.logError(message, t);
		while (t.getCause() != null) {
			t = t.getCause();
			this.log.logError("Caused by:", t);
		}
	}

	/**
	 * 
	 */
	private void logStepStatistics() {
		this.log.logBasic("Dirigent steps statistics:");
		Iterator<StepStatistics> i = PatternExecutionStatistics
				.getStepStatistics().iterator();
		while (i.hasNext()) {
			this.log.logBasic(i.next().getExecutionSummary());
		}
	}
}