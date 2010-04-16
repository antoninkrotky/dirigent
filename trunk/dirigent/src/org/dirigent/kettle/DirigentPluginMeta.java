package org.dirigent.kettle; 

import java.util.List;
import java.util.Map;

import org.pentaho.di.core.CheckResultInterface;
import org.pentaho.di.core.Counter;
import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.core.exception.KettleXMLException;
import org.pentaho.di.core.row.RowMetaInterface;
import org.pentaho.di.repository.ObjectId;
import org.pentaho.di.repository.Repository;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;
import org.pentaho.di.trans.step.BaseStepMeta;
import org.pentaho.di.trans.step.StepDataInterface;
import org.pentaho.di.trans.step.StepInterface;
import org.pentaho.di.trans.step.StepMeta;
import org.pentaho.di.trans.step.StepMetaInterface;
import org.w3c.dom.Node;

/*
 * Created on 02-jun-2003
 *
 */

public class DirigentPluginMeta extends BaseStepMeta implements StepMetaInterface
{

	private String filename;
	private boolean includingFilename; 
	private boolean headerPresent;
	private String delimiter;
	private String enclosure;

	
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

	@Override
	public void check(List<CheckResultInterface> arg0, TransMeta arg1,
			StepMeta arg2, RowMetaInterface arg3, String[] arg4, String[] arg5,
			RowMetaInterface arg6) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public StepInterface getStep(StepMeta arg0, StepDataInterface arg1,
			int arg2, TransMeta arg3, Trans arg4) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StepDataInterface getStepData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void loadXML(Node arg0, List<DatabaseMeta> arg1,
			Map<String, Counter> arg2) throws KettleXMLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void readRep(Repository arg0, ObjectId arg1,
			List<DatabaseMeta> arg2, Map<String, Counter> arg3)
			throws KettleException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveRep(Repository arg0, ObjectId arg1, ObjectId arg2)
			throws KettleException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDefault() {
		// TODO Auto-generated method stub
		
	}
	


}
