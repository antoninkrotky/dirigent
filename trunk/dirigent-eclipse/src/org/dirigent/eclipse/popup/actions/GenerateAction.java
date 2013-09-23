package org.dirigent.eclipse.popup.actions;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;

public class GenerateAction implements IObjectActionDelegate {

	private static final String CONSOLE_NAME = "Dirigent";



	private final class AsyncGenerator implements Runnable {
		@Override
		public void run() {
			generate();				
		}
	}



	private ICompilationUnit selectedCompilationUnit;

	public ICompilationUnit getSelectedCompilationUnit() {
		return selectedCompilationUnit;
	}



	public void setSelectedCompilationUnit(
			ICompilationUnit selectedCompilationUnit) {
		this.selectedCompilationUnit = selectedCompilationUnit;
	}



	/**
	 * Constructor for Action1.
	 */
	public GenerateAction() {
		super();
	}



	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		new Thread(new AsyncGenerator()).start();
		
	}

	
	
	private void generate() {
		ICompilationUnit compilationUnit=getSelectedCompilationUnit();
		String projectPath=((IProject)compilationUnit.getJavaProject().getAdapter(IProject.class)).getLocation().toOSString();
		try {
			String targetType = compilationUnit.getAllTypes()[0].getFullyQualifiedName();
			String classPath=getProjectClassPath(compilationUnit);
			String command="java -cp \""+classPath+"\" org.dirigent.Dirigent "+targetType;
			Process process=Runtime.getRuntime().exec(command,null,new File(projectPath));
			MessageConsole console=findConsole();
			MessageConsoleStream out = console.newMessageStream();
			copyStream(process.getInputStream(), out);
			copyStream(process.getErrorStream(), out);
			((IResource)compilationUnit.getJavaProject().getAdapter(IResource.class)).refreshLocal(IResource.DEPTH_INFINITE,null);
		} catch (Exception e) {
			throw new RuntimeException("Unable to generate "+compilationUnit.getElementName(),e);
		} 
	}
	
	private MessageConsole findConsole() {
	      ConsolePlugin plugin = ConsolePlugin.getDefault();
	      IConsoleManager conMan = plugin.getConsoleManager();
	      IConsole[] existing = conMan.getConsoles();
	      for (int i = 0; i < existing.length; i++)
	         if (CONSOLE_NAME.equals(existing[i].getName()))
	            return (MessageConsole) existing[i];
	      //no console found, so create a new one
	      MessageConsole myConsole = new MessageConsole(CONSOLE_NAME, null);
	      conMan.addConsoles(new IConsole[]{myConsole});
	      return myConsole;
	   }
	
	static void copyStream(InputStream in, OutputStream out) throws IOException {
	    while (true) {
	      int c = in.read();
	      if (c == -1) break;
	      out.write((char)c);
	    }
	  }

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection ssel = (IStructuredSelection) selection;
			Object obj = ssel.getFirstElement();
			if (obj instanceof ICompilationUnit) {
				setSelectedCompilationUnit((ICompilationUnit) obj);
			}
		}
	}

	private String getProjectClassPath(ICompilationUnit compilationUnit) throws CoreException {
		String[] classPathEntries;
		classPathEntries = JavaRuntime
				.computeDefaultRuntimeClassPath(compilationUnit
						.getJavaProject());
		StringBuffer projectClassPath = new StringBuffer();
		for (int i = 0; i < classPathEntries.length; i++) {
			String entry = classPathEntries[i];
			IPath path = new Path(entry);
			try {
				projectClassPath.append(path.toFile().getCanonicalPath().toString());
				projectClassPath.append(File.pathSeparator);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return projectClassPath.toString();
	}



	@Override
	public void setActivePart(IAction arg0, IWorkbenchPart arg1) {
	}

}
