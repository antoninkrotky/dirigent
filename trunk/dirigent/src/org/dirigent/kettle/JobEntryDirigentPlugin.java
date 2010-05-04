package org.dirigent.kettle;

import java.util.List;

import org.dirigent.config.DirigentConfig;
import org.dirigent.metafacade.builder.MetafacadeBuilder;
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

//	private static final String STEP_NAME = "stepName"; 
	private static final String SOURCE = "source";
	private static final String DELIMITER = "delimiter";
	private static final String ENCLOSURE = "enclosure";

//	private String stepName; 
	private String source;
	private String delimiter;
	private String enclosure;

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

	public String getFilename() {
		return source;
	}

	public void setFilename(String filename) {
		this.source = filename;
	}

	
	public String getDelimiter() {
		return delimiter;
	}

	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}

	public String getEnclosure() {
		return enclosure;
	}

	public void setEnclosure(String enclosure) {
		this.enclosure = enclosure;
	}

	public Object getValue() {
		return null;
	}
	
	@Override
	public String getXML() {
	    StringBuffer retval = new StringBuffer();

	    retval.append(super.getXML());

	    retval.append("      " + XMLHandler.addTagValue(SOURCE, source));
	    retval.append("      " + XMLHandler.addTagValue(DELIMITER, delimiter));
	    retval.append("      " + XMLHandler.addTagValue(ENCLOSURE, enclosure));

	    return retval.toString();	
	}

	@Override
	public void loadXML(Node entrynode, List<DatabaseMeta> databases,
			List<SlaveServer> slaveServers, Repository rep)
			throws KettleXMLException {
		try {
			super.loadXML(entrynode, databases, slaveServers);
			source = XMLHandler.getTagValue(entrynode, SOURCE);
			delimiter = XMLHandler.getTagValue(entrynode, DELIMITER);
			enclosure = XMLHandler.getTagValue(entrynode, ENCLOSURE);
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
			source = rep.getJobEntryAttributeString(id_jobentry, SOURCE);
			delimiter = rep.getJobEntryAttributeString(id_jobentry, DELIMITER);
			enclosure = rep.getJobEntryAttributeString(id_jobentry, ENCLOSURE);

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
			rep.saveJobEntryAttribute(id_job, getObjectId(), SOURCE, source);
			rep.saveJobEntryAttribute(id_job, getObjectId(), DELIMITER,
					delimiter);
			rep.saveJobEntryAttribute(id_job, getObjectId(), ENCLOSURE,
					enclosure);

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
		logDetailed(toString(), "Starting DIRIGENT Job ");
		
		System.setProperty(DirigentConfig.MODEL_PATH, source);
		System.setProperty("dirigent.model.type", "CSV");
		MetafacadeBuilder mfb = MetafacadeBuilder.getMetafacadeBuilder();
		
		DirigentPluginJob job = new DirigentPluginJob();
		
		try {
			job.process();
			result.setResult(true);
		} catch (Exception e) {
			logDetailed(toString(), "Error while executing job");
		}

		logDetailed(toString(), "Finishing DIRIGENT Job ");
		return null;
	}

}