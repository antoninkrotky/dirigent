package org.dirigent.kettle;

import java.util.List;

import org.dirigent.Dirigent;
import org.dirigent.config.DirigentConfig;
import org.pentaho.di.cluster.SlaveServer;
import org.pentaho.di.core.Result;
import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.core.exception.KettleDatabaseException;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.core.exception.KettleXMLException;
import org.pentaho.di.core.logging.LogChannel;
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
	private String model;
	private String uri;
	private LogChannel log = new LogChannel(this);

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

	@Override
	public String getXML() {
		StringBuffer retval = new StringBuffer();

		retval.append(super.getXML());

		retval.append("      " + XMLHandler.addTagValue(MODEL, model));
		retval.append("      " + XMLHandler.addTagValue(URI, uri));
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

		} catch (KettleDatabaseException dbe) {
			throw new KettleException(
					"unable to save jobentry of type 'file exists' to the repository for id_job="
							+ id_job, dbe);
		}
	}

	@Override
	public Result execute(Result prevResult, int nr) throws KettleException {

		Result result = new Result(nr);
		result.setResult(false);
		log.logDetailed("Starting DIRIGENT Job ");

		try {

			System.setProperty(DirigentConfig.MODEL_PATH, model);
			System.setProperty("dirigent.model.type", "EA");
			String[] args = { model, uri };
			Dirigent.main(args);

		} catch (NullPointerException npe) {
			logDetailed(toString(), "Exception encountered: "
					+ npe.getMessage());
			result.setResult(false);
			return result;

		} catch (RuntimeException rex) {
			log
					.logDetailed("RunTime Exception encountered. A problem with model? This maybe an error in your database/schema settings as a result of SQLException. Message: \n"
							+ rex.getMessage());
			log.logDetailed("Finishing DIRIGENT Job with no success");
			result.setResult(false);
			result.setNrErrors(result.getNrErrors() + 1);
			return result;
		}

		result.setResult(true);
		logDetailed(toString(), "Finishing DIRIGENT Job ");
		return result;
	}

}