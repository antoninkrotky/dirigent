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
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.pentaho.di.core.Const;
import org.pentaho.di.ui.core.widget.TextVar;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.pentaho.di.trans.TransMeta;
import org.pentaho.di.ui.trans.step.BaseStepDialog;
import org.pentaho.di.trans.step.BaseStepMeta;
import org.pentaho.di.trans.step.StepDialogInterface;

public class DirigentPluginDialog extends BaseStepDialog implements
		StepDialogInterface {

	private DirigentPluginMeta inputMeta;

	private TextVar wFilename;
	
	private Button wbbFilename; // Browse for a file
	
	private Button wbDelimiter;
	private TextVar wDelimiter;
	private TextVar wEnclosure;
	private Button wHeaderPresent;

	public DirigentPluginDialog(Shell parent, Object in, TransMeta transMeta,
			String sname) {
		super(parent, (BaseStepMeta) in, transMeta, sname);
		inputMeta = (DirigentPluginMeta) in;
	}

	@Override
	public String open() {
		Shell parent = getParent();
		Display display = parent.getDisplay();

		shell = new Shell(parent, SWT.DIALOG_TRIM | SWT.RESIZE | SWT.MIN
				| SWT.MAX);
		props.setLook(shell);
		setShellImage(shell, inputMeta);

		ModifyListener lsMod = new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				inputMeta.setChanged();
			}
		};
		changed = inputMeta.hasChanged();

		FormLayout formLayout = new FormLayout();
		formLayout.marginWidth = Const.FORM_MARGIN;
		formLayout.marginHeight = Const.FORM_MARGIN;

		shell.setLayout(formLayout);
		shell.setText(Messages.getString("DirigentPluginDialog.Shell.Title"));
		int middle = props.getMiddlePct();
		int margin = Const.MARGIN;

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
				.getString("DirigentPluginDialog.InputFile.Label"));
		props.setLook(wlFilename);
		FormData fdlFilename = new FormData();
		fdlFilename.top = new FormAttachment(0, margin);
		fdlFilename.left = new FormAttachment(0, 0);
		fdlFilename.right = new FormAttachment(middle, -margin);
		wlFilename.setLayoutData(fdlFilename);
		wFilename = new TextVar(transMeta, shell, SWT.SINGLE | SWT.LEFT
				| SWT.BORDER);
		props.setLook(wFilename);
		wFilename.addModifyListener(lsMod);
		FormData fdFilename = new FormData();
		fdFilename.top = new FormAttachment(0, margin);
		fdFilename.left = new FormAttachment(middle, 0);
		fdFilename.right = new FormAttachment(100, 0);
		wFilename.setLayoutData(fdFilename);
		lastControl = wFilename;

		// The filename browse button
		//
		wbbFilename = new Button(shell, SWT.PUSH | SWT.CENTER);
		props.setLook(wbbFilename);
		wbbFilename.setText(Messages.getString("System.Button.Browse"));
		wbbFilename.setToolTipText(Messages
				.getString("System.Tooltip.BrowseForFileOrDirAndAdd"));
		FormData fdbFilename = new FormData();
		fdbFilename.top = new FormAttachment(lastControl, margin);
		fdbFilename.right = new FormAttachment(100, 0);
		wbbFilename.setLayoutData(fdbFilename);

		Label wlDelimiter = new Label(shell, SWT.RIGHT);
		wlDelimiter.setText(Messages
				.getString("DirigentPluginDialog.Delimiter.Label")); //$NON-NLS-1$
		props.setLook(wlDelimiter);
		FormData fdlDelimiter = new FormData();
		fdlDelimiter.top = new FormAttachment(lastControl, margin);
		fdlDelimiter.left = new FormAttachment(0, 0);
		fdlDelimiter.right = new FormAttachment(middle, -margin);
		wlDelimiter.setLayoutData(fdlDelimiter);
		wbDelimiter = new Button(shell, SWT.PUSH | SWT.CENTER);
		props.setLook(wbDelimiter);
		wbDelimiter.setText(Messages
				.getString("DirigentPluginDialog.Delimiter.Button"));
		FormData fdbDelimiter = new FormData();
		fdbDelimiter.top = new FormAttachment(lastControl, margin);
		fdbDelimiter.right = new FormAttachment(100, 0);
		wbDelimiter.setLayoutData(fdbDelimiter);
		wDelimiter = new TextVar(transMeta, shell, SWT.SINGLE | SWT.LEFT
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
		wEnclosure = new TextVar(transMeta, shell, SWT.SINGLE | SWT.LEFT
				| SWT.BORDER);
		props.setLook(wEnclosure);
		wEnclosure.addModifyListener(lsMod);
		FormData fdEnclosure = new FormData();
		fdEnclosure.top = new FormAttachment(lastControl, margin);
		fdEnclosure.left = new FormAttachment(middle, 0);
		fdEnclosure.right = new FormAttachment(100, 0);
		wEnclosure.setLayoutData(fdEnclosure);
		lastControl = wEnclosure;

		// header row?
		//
		Label wlHeaderPresent = new Label(shell, SWT.RIGHT);
		wlHeaderPresent.setText(Messages
				.getString("DirigentPluginDialog.HeaderPresent.Label")); //$NON-NLS-1$
		props.setLook(wlHeaderPresent);
		FormData fdlHeaderPresent = new FormData();
		fdlHeaderPresent.top = new FormAttachment(lastControl, margin);
		fdlHeaderPresent.left = new FormAttachment(0, 0);
		fdlHeaderPresent.right = new FormAttachment(middle, -margin);
		wlHeaderPresent.setLayoutData(fdlHeaderPresent);
		wHeaderPresent = new Button(shell, SWT.CHECK);
		props.setLook(wHeaderPresent);
		FormData fdHeaderPresent = new FormData();
		fdHeaderPresent.top = new FormAttachment(lastControl, margin);
		fdHeaderPresent.left = new FormAttachment(middle, 0);
		fdHeaderPresent.right = new FormAttachment(100, 0);
		wHeaderPresent.setLayoutData(fdHeaderPresent);
		lastControl = wHeaderPresent;

		// Some buttons
		wOK = new Button(shell, SWT.PUSH);
		wOK.setText(Messages.getString("System.Button.OK")); //$NON-NLS-1$
		wCancel = new Button(shell, SWT.PUSH);
		wCancel.setText(Messages.getString("System.Button.Cancel")); //$NON-NLS-1$

		setButtonPositions(new Button[] { wOK, wCancel, }, margin, null);
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
		setSize();

		getData();
		inputMeta.setChanged(changed);

		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		return stepname;
	}

	// Read data from input (TextFileInputInfo)
	public void getData() {
		getData(inputMeta);
	}

	public void getData(DirigentPluginMeta inputMeta) {
		wStepname.setText(stepname);
		wFilename.setText(Const.NVL(inputMeta.getFilename(), ""));

		wDelimiter.setText(Const.NVL(inputMeta.getDelimiter(), ""));
		wEnclosure.setText(Const.NVL(inputMeta.getEnclosure(), ""));
		wHeaderPresent.setSelection(inputMeta.isHeaderPresent());

		wStepname.selectAll();

	}

	private void getInfo(DirigentPluginMeta inputMeta) {
		inputMeta.setFilename(wFilename.getText());
		
		inputMeta.setDelimiter(wDelimiter.getText());
		inputMeta.setEnclosure(wEnclosure.getText());
		inputMeta.setHeaderPresent(wHeaderPresent.getSelection());
		
    	
		
		inputMeta.setChanged();
	}
	
	private void cancel() {
		stepname = null;
		inputMeta.setChanged(changed);
		dispose();
	}

	private void ok() {
		if (Const.isEmpty(wStepname.getText()))
			return;

		getInfo(inputMeta);
		stepname = wStepname.getText();
		dispose();
	}
}
