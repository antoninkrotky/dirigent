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
	private TextVar wFilename, wDelimiter, wEnclosure;
	private Button wbbFilename, wbDelimiter, wOK, wCancel; // Browse for a file
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
		
		// filename label
		Label wlFilename = new Label(shell, SWT.RIGHT);
		wlFilename.setText(Messages
				.getString("DirigentPluginDialog.InputFile.Label")); //$NON-NLS-1$
		props.setLook(wlFilename);
		FormData fdlFilename = new FormData();
		fdlFilename.top = new FormAttachment(lastControl, margin);
		fdlFilename.left = new FormAttachment(0, 0);
		fdlFilename.right = new FormAttachment(middle, -margin);
		wlFilename.setLayoutData(fdlFilename);
		
		wbbFilename = new Button(shell, SWT.PUSH | SWT.CENTER);
		props.setLook(wbbFilename);
		
		wbbFilename.setText(Messages.getString("System.Button.Browse"));
		wbbFilename.setToolTipText(Messages
				.getString("System.Tooltip.BrowseForFileOrDirAndAdd"));
		FormData fdbFilename = new FormData();
		fdbFilename.top = new FormAttachment(lastControl, margin);
		fdbFilename.right = new FormAttachment(100, 0);
		wbbFilename.setLayoutData(fdbFilename);
		
		wFilename = new TextVar(jobEntry, shell, SWT.SINGLE | SWT.LEFT
				| SWT.BORDER);
		props.setLook(wFilename);
		wFilename.addModifyListener(lsMod);
		FormData fdFilename = new FormData();
		fdFilename.top = new FormAttachment(lastControl, margin);
		fdFilename.left = new FormAttachment(middle, 0);
		fdFilename.right = new FormAttachment(wbbFilename, 0);
		wFilename.setLayoutData(fdFilename);
		lastControl = wFilename;

		

		//delimiter 
		Label wlDelimiter = new Label(shell, SWT.RIGHT);
		wlDelimiter.setText(Messages
				.getString("DirigentPluginDialog.Delimiter.Label")); //$NON-NLS-1$ 
		props.setLook(wlDelimiter);
		FormData fdlDelimiter = new FormData();
		fdlDelimiter.top = new FormAttachment(lastControl, margin);
		fdlDelimiter.left = new FormAttachment(0, 0);
		fdlDelimiter.right = new FormAttachment(middle, -margin);
		wlDelimiter.setLayoutData(fdlDelimiter);
		
		wbDelimiter=new Button(shell, SWT.PUSH| SWT.CENTER);
        props.setLook(wbDelimiter);
        wbDelimiter.setText(Messages.getString("DirigentPluginDialog.Delimiter.Button")); //$NON-NLS-1$
        FormData fdbDelimiter=new FormData();
        fdbDelimiter.top  = new FormAttachment(lastControl, margin);
        fdbDelimiter.right= new FormAttachment(100, 0);        
        wbDelimiter.setLayoutData(fdbDelimiter);
		
        wDelimiter = new TextVar(jobEntry, shell, SWT.SINGLE | SWT.LEFT
				| SWT.BORDER);
		props.setLook(wDelimiter);
		wDelimiter.addModifyListener(lsMod);
		FormData fdDelimiter = new FormData();
		fdDelimiter.top = new FormAttachment(lastControl, margin);
		fdDelimiter.left = new FormAttachment(middle, 0);
		fdDelimiter.right = new FormAttachment(wbDelimiter, -margin);
		wDelimiter.setLayoutData(fdDelimiter);
		lastControl = wDelimiter;

		// enclosure
		Label wlEnclosure = new Label(shell, SWT.RIGHT);
		wlEnclosure.setText(Messages
				.getString("DirigentPluginDialog.Enclosure.Label")); //$NON-NLS-1$
		props.setLook(wlEnclosure);
		FormData fdlEnclosure = new FormData();
		fdlEnclosure.top = new FormAttachment(lastControl, margin);
		fdlEnclosure.left = new FormAttachment(0, 0);
		fdlEnclosure.right = new FormAttachment(middle, -margin);
		wlEnclosure.setLayoutData(fdlEnclosure);
		wEnclosure = new TextVar(jobEntry, shell, SWT.SINGLE | SWT.LEFT
				| SWT.BORDER);
		props.setLook(wEnclosure);
		wEnclosure.addModifyListener(lsMod);
		FormData fdEnclosure = new FormData();
		fdEnclosure.top = new FormAttachment(lastControl, margin);
		fdEnclosure.left = new FormAttachment(middle, 0);
		fdEnclosure.right = new FormAttachment(100, 0);
		wEnclosure.setLayoutData(fdEnclosure);
		lastControl = wEnclosure;


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
		if (wFilename != null)
			wFilename.addSelectionListener(lsDef);
		wDelimiter.addSelectionListener(lsDef);
		wEnclosure.addSelectionListener(lsDef);

		// Allow the insertion of tabs as separator...
		wbDelimiter.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent se) {
				Text t = wDelimiter.getTextWidget();
				if (t != null)
					t.insert("\t");
			}
		});

		if (wbbFilename != null) {
			// Listen to the browse button next to the file name
			wbbFilename.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent event) {
					DirectoryDialog dialog = new DirectoryDialog(shell,
							SWT.OPEN);
					String objectname = dialog.open();
					if (objectname != null)
						wFilename.setText(objectname);
				}
			});
		}

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
		wFilename.setText(Const.NVL(jobEntry.getFilename(), ""));
		wDelimiter.setText(Const.NVL(jobEntry.getDelimiter(), ""));
		wEnclosure.setText(Const.NVL(jobEntry.getEnclosure(), ""));
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
        jobEntry.setFilename(wFilename.getText());
		jobEntry.setDelimiter(wDelimiter.getText());
		jobEntry.setEnclosure(wEnclosure.getText());
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

