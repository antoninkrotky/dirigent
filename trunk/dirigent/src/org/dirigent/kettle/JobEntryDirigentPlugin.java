package org.dirigent.kettle;

import java.util.List;

import org.pentaho.di.cluster.SlaveServer;
import org.pentaho.di.core.Result;
import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.core.exception.KettleXMLException;
import org.pentaho.di.job.entry.JobEntryBase;
import org.pentaho.di.job.entry.JobEntryInterface;
import org.pentaho.di.repository.ObjectId;
import org.pentaho.di.repository.Repository;
import org.w3c.dom.Node;

/*
 * Created on 02-jun-2003
 *
 */

public class JobEntryDirigentPlugin extends JobEntryBase implements Cloneable, JobEntryInterface {

	private String filename;
	private boolean includingFilename;
	private boolean headerPresent;
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
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public boolean isIncludingFilename() {
		return includingFilename;
	}

	public void setIncludingFilename(boolean includingFilename) {
		this.includingFilename = includingFilename;
	}

	public boolean isHeaderPresent() {
		return headerPresent;
	}

	public void setHeaderPresent(boolean headerPresent) {
		this.headerPresent = headerPresent;
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

	public void loadXML(Node entrynode, List<DatabaseMeta> databases,
			List<SlaveServer> slaveServers, Repository rep)
			throws KettleXMLException {
		// try {
		// super.loadXML(entrynode, databases, slaveServers);
		// sourceDirectory = XMLHandler
		// .getTagValue(entrynode, SOURCEDIRECTORY);
		// targetDirectory = XMLHandler
		// .getTagValue(entrynode, TARGETDIRECTORY);
		// wildcard = XMLHandler.getTagValue(entrynode, WILDCARD);
		// } catch (KettleXMLException xe) {
		// throw new KettleXMLException(
		// "Unable to load file exists job entry from XML node", xe);
		// }
	}

	@Override
	public void loadRep(Repository rep, ObjectId id_jobentry,
			List<DatabaseMeta> databases, List<SlaveServer> slaveServers)
			throws KettleException {
		// try {
		// super.loadRep(rep, id_jobentry, databases, slaveServers);
		// sourceDirectory = rep.getJobEntryAttributeString(id_jobentry,
		// SOURCEDIRECTORY);
		// targetDirectory = rep.getJobEntryAttributeString(id_jobentry,
		// TARGETDIRECTORY);
		// wildcard = rep.getJobEntryAttributeString(id_jobentry, WILDCARD);
		// } catch (KettleException dbe) {
		// throw new KettleException(
		// "Unable to load job entry for type file exists from the repository for id_jobentry="
		// + id_jobentry, dbe);
		// }
	}

	public void saveRep(Repository rep, ObjectId id_job) throws KettleException {
		// try {
		// super.saveRep(rep, id_job);
		//
		// rep.saveJobEntryAttribute(id_job, getObjectId(), SOURCEDIRECTORY,
		// sourceDirectory);
		// rep.saveJobEntryAttribute(id_job, getObjectId(), TARGETDIRECTORY,
		// targetDirectory);
		// rep
		// .saveJobEntryAttribute(id_job, getObjectId(), WILDCARD,
		// wildcard);
		// } catch (KettleDatabaseException dbe) {
		// throw new KettleException(
		// "unable to save jobentry of type 'file exists' to the repository for id_job="
		// + id_job, dbe);
		// }
	}

	@Override
	public Result execute(Result prevResult, int nr) throws KettleException {
		// TODO Auto-generated method stub
		return null;
	}


	
	public boolean evaluates(){
	    return true;
	}

	public boolean isUnconditional(){
	    return false;
	}
}