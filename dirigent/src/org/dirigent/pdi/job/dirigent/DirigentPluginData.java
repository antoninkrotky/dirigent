package org.dirigent.pdi.job.dirigent;

import org.pentaho.di.core.row.RowMetaInterface;
import org.pentaho.di.trans.step.BaseStepData;
import org.pentaho.di.trans.step.StepDataInterface;

public class DirigentPluginData extends BaseStepData implements StepDataInterface
{
	public RowMetaInterface outputRowMeta;

    public DirigentPluginData()
	{
		super();
	}
}
