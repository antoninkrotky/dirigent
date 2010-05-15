package org.dirigent.kettle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.pentaho.di.core.Const;
import org.pentaho.di.job.JobMeta;
import org.pentaho.di.job.entry.JobEntryDialogInterface;
import org.pentaho.di.job.entry.JobEntryInterface;
import org.pentaho.di.repository.Repository;
import org.pentaho.di.ui.core.PropsUI;
import org.pentaho.di.ui.core.gui.WindowProperty;
import org.pentaho.di.ui.core.widget.TextVar;
import org.pentaho.di.ui.job.dialog.JobDialog;
import org.pentaho.di.ui.job.entry.JobEntryDialog;
import org.pentaho.di.ui.trans.step.BaseStepDialog;

public class JobEntryDirigentPluginDialog extends JobEntryDialog implements JobEntryDialogInterface {

	private Label wlStepname; 
	private FormData fdlStepname, fdStepname; 
	private Text wStepname; 
	private TextVar wModel, wURI, wOutputFilename;
	private Button wbbModel, wOK, wCancel; 
	private Listener lsOK, lsCancel;
	private SelectionAdapter lsDef; 

	
	
	private boolean changed; 
	private JobEntryDirigentPlugin jobEntry; 

	public JobEntryDirigentPluginDialog(Shell parent, JobEntryInterface jobEntryInt, Repository rep, JobMeta jobMeta)
	{
			super(parent, jobEntryInt, rep, jobMeta);
			props=PropsUI.getInstance();
			this.jobEntry=(JobEntryDirigentPlugin) jobEntryInt;
	
			if (this.jobEntry.getName() == null) this.jobEntry.setName(jobEntryInt.getName());
	}

	@Override
	public JobEntryInterface open() {
		Shell parent = getParent();
		Display display = parent.getDisplay();

		shell = new Shell(parent, SWT.DIALOG_TRIM | SWT.RESIZE | SWT.MIN
				| SWT.MAX);
		props.setLook(shell);
		JobDialog.setShellImage(shell, jobEntry);

		ModifyListener lsMod = new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				jobEntry.setChanged();
			}
		};
		changed = jobEntry.hasChanged();

		FormLayout formLayout = new FormLayout();
		formLayout.marginWidth = Const.FORM_MARGIN;
		formLayout.marginHeight = Const.FORM_MARGIN;

		shell.setLayout(formLayout);
		shell.setText(Messages.getString("DirigentPluginDialog.Shell.Title")); //$NON-NLS-1$
		int middle = props.getMiddlePct();
		int margin = Const.MARGIN;

		//stepname 
		wlStepname=new Label(shell, SWT.RIGHT);
		wlStepname.setText(Messages.getString("DirigentPluginDialog.Stepname.Label")); //$NON-NLS-1$
 		props.setLook(wlStepname);
		fdlStepname=new FormData();
		fdlStepname.left = new FormAttachment(0, 0);
		fdlStepname.right= new FormAttachment(middle, -margin);
		fdlStepname.top  = new FormAttachment(0, margin);
		wlStepname.setLayoutData(fdlStepname);
		wStepname=new Text(shell, SWT.SINGLE | SWT.LEFT | SWT.BORDER);
 		props.setLook(wStepname);
		wStepname.addModifyListener(lsMod);
		fdStepname=new FormData();
		fdStepname.left = new FormAttachment(middle, 0);
		fdStepname.top  = new FormAttachment(0, margin);
		fdStepname.right= new FormAttachment(100, 0);
		wStepname.setLayoutData(fdStepname);
		Control lastControl = wStepname;
		
		// source folder label
		Label wlModel = new Label(shell, SWT.RIGHT);
		wlModel.setText(Messages
				.getString("DirigentPluginDialog.InputFile.Label")); //$NON-NLS-1$
		props.setLook(wlModel);
		FormData fdlModel = new FormData();
		fdlModel.top = new FormAttachment(lastControl, margin);
		fdlModel.left = new FormAttachment(0, 0);
		fdlModel.right = new FormAttachment(middle, -margin);
		wlModel.setLayoutData(fdlModel);
		
		wbbModel = new Button(shell, SWT.PUSH | SWT.CENTER);
		props.setLook(wbbModel);
		
		wbbModel.setText(Messages.getString("System.Button.Browse"));
		wbbModel.setToolTipText(Messages
				.getString("System.Tooltip.BrowseForFileOrDirAndAdd"));
		FormData fdbModel = new FormData();
		fdbModel.top = new FormAttachment(lastControl, margin);
		fdbModel.right = new FormAttachment(100, 0);
		wbbModel.setLayoutData(fdbModel);
		
		wModel = new TextVar(jobEntry, shell, SWT.SINGLE | SWT.LEFT
				| SWT.BORDER);
		props.setLook(wModel);
		wModel.addModifyListener(lsMod);
		FormData fdFilename = new FormData();
		fdFilename.top = new FormAttachment(lastControl, margin);
		fdFilename.left = new FormAttachment(middle, 0);
		fdFilename.right = new FormAttachment(wbbModel, 0);
		wModel.setLayoutData(fdFilename);
		lastControl = wModel;

		Label wlURI = new Label(shell, SWT.RIGHT);
		wlURI.setText(Messages
				.getString("DirigentPluginDialog.URI.Label")); //$NON-NLS-1$ 
		props.setLook(wlURI);
		FormData fdlURI = new FormData();
		fdlURI.top = new FormAttachment(lastControl, margin);
		fdlURI.left = new FormAttachment(0, 0);
		fdlURI.right = new FormAttachment(middle, -margin);
		wlURI.setLayoutData(fdlURI);

		wURI = new TextVar(jobEntry, shell, SWT.SINGLE | SWT.LEFT
				| SWT.BORDER);
		props.setLook(wURI);
		wURI.addModifyListener(lsMod);
		FormData fdURI = new FormData();
		fdURI.top = new FormAttachment(lastControl, margin);
		fdURI.left = new FormAttachment(middle, 0);
		fdURI.right = new FormAttachment(100, 0);
		wURI.setLayoutData(fdURI);
		lastControl = wURI;
		
		// output file 
//		Label wlOutputFilename = new Label(shell, SWT.RIGHT);
//		wlOutputFilename.setText(Messages
//				.getString("DirigentPluginDialog.OutputFile.Label")); //$NON-NLS-1$
//		props.setLook(wlOutputFilename);
//		FormData fdlOutputFilename = new FormData();
//		fdlOutputFilename.top = new FormAttachment(lastControl, margin);
//		fdlOutputFilename.left = new FormAttachment(0, 0);
//		fdlOutputFilename.right = new FormAttachment(middle, -margin);
//		wlOutputFilename.setLayoutData(fdlOutputFilename);
//		
//		wbbOutputFilename= new Button(shell, SWT.PUSH | SWT.CENTER);
//		props.setLook(wbbOutputFilename);
//		
//		wbbOutputFilename.setText(Messages.getString("System.Button.Browse"));
//		wbbOutputFilename.setToolTipText(Messages
//				.getString("System.Tooltip.BrowseForFileOrDirAndAdd"));
//		FormData fdbOutputFilename = new FormData();
//		fdbOutputFilename.top = new FormAttachment(lastControl, margin);
//		fdbOutputFilename.right = new FormAttachment(100, 0);
//		wbbOutputFilename.setLayoutData(fdbOutputFilename);
//		
//		wOutputFilename = new TextVar(jobEntry, shell, SWT.SINGLE | SWT.LEFT
//				| SWT.BORDER);
//		props.setLook(wOutputFilename);
//		wOutputFilename.addModifyListener(lsMod);
//		FormData fdOutputFilename = new FormData();
//		fdOutputFilename.top = new FormAttachment(lastControl, margin);
//		fdOutputFilename.left = new FormAttachment(middle, 0);
//		fdOutputFilename.right = new FormAttachment(wbbOutputFilename, 0);
//		wOutputFilename.setLayoutData(fdOutputFilename);
//		lastControl = wOutputFilename;

		
		// Some buttons
		wOK = new Button(shell, SWT.PUSH);
		wOK.setText(Messages.getString("System.Button.OK")); //$NON-NLS-1$
		wCancel = new Button(shell, SWT.PUSH);
		wCancel.setText(Messages.getString("System.Button.Cancel")); //$NON-NLS-1$

		BaseStepDialog.positionBottomButtons(shell, new Button[] { wOK, wCancel, }, margin, null);
		// Add listeners
		lsCancel = new Listener() {
			public void handleEvent(Event e) {
				cancel();
			}
		};
		lsOK = new Listener() {
			public void handleEvent(Event e) {
				ok();
 			}
		};

		wCancel.addListener(SWT.Selection, lsCancel);
		wOK.addListener(SWT.Selection, lsOK);

		lsDef = new SelectionAdapter() {
			public void widgetDefaultSelected(SelectionEvent e) {
				ok();
			}
		};

		// wStepname.addSelectionListener( lsDef );
		if (wModel != null)
			wModel.addSelectionListener(lsDef);
		if (wOutputFilename != null)
			wOutputFilename.addSelectionListener(lsDef);
		wURI.addSelectionListener(lsDef);
//		wEnclosure.addSelectionListener(lsDef);

	

		if (wbbModel != null) {
			// Listen to the browse button next to the file name
			wbbModel.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent event) {
					DirectoryDialog dialog = new DirectoryDialog(shell,
							SWT.OPEN);
					String objectname = dialog.open();
					if (objectname != null)
						wModel.setText(objectname);
				}
			});
		}
//		
//		if (wbbOutputFilename!= null) {
//			// Listen to the browse button next to the file name
//			wbbOutputFilename.addSelectionListener(new SelectionAdapter() {
//				public void widgetSelected(SelectionEvent event) {
//					org.eclipse.swt.widgets.FileDialog dialog = new org.eclipse.swt.widgets.FileDialog(shell,
//							SWT.OPEN);
//					String objectname = dialog.open();
//					if (objectname != null)
//						wOutputFilename.setText(objectname);
//				}
//			});
//		}

		// Detect X or ALT-F4 or something that kills this window...
		shell.addShellListener(new ShellAdapter() {
			public void shellClosed(ShellEvent e) {
				cancel();
			}
		});

		// Set the shell size, based upon previous time...
		BaseStepDialog.setSize(shell);

		getData(jobEntry);
		
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		return jobEntry;
	}

	
	public void getData(JobEntryDirigentPlugin jobEntry) {
		wStepname.setText(Const.NVL(jobEntry.getName(), "")); 
		wModel.setText(Const.NVL(jobEntry.getModel(), ""));
		wURI.setText(Const.NVL(jobEntry.getUri(), ""));

	}
	
	public void dispose()
	{
		WindowProperty winprop = new WindowProperty(shell);
		props.setScreen(winprop);
		shell.dispose();
	}
	
	private void cancel() {
		jobEntry.setChanged(changed);
		jobEntry=null;
		dispose();
	}

	private void ok() {
		jobEntry.setName(wStepname.getText());
        jobEntry.setModel(wModel.getText());
		jobEntry.setUri(wURI.getText());
		jobEntry.setChanged(); 
		dispose();		
	}
	
	public String toString()
	{
		return this.getClass().getName();
	}
	
	public boolean evaluates()
	{
		return true;
	}

	public boolean isUnconditional()
	{
		return true;
	}
	
	public boolean isTrue(){
		return true;
	}
	
	public boolean isFalse(){
		return true;
	}

}

