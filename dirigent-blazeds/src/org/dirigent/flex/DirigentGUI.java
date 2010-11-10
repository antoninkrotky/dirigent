package org.dirigent.flex;

import java.net.URI;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class DirigentGUI {
	
	public static final String DIRIGENT_BROWSER_ROOT_URI="dirigent.gui.browser.root.uri";
	
	
	public static void main(String args[]) {
		String webContentPath="WebContent";
		if (args.length>0) {
			webContentPath=args[0];
		}
		launchDirigentGUI(webContentPath);
	}

	public static void launchDirigentGUI(String webContentPath) {
		Server server = new Server(8888);
		WebAppContext context = new WebAppContext();
		context.setDescriptor(webContentPath+"/WEB-INF/web.xml");
		context.setResourceBase(webContentPath);
		context.setContextPath("/dirigent-blazeds");
		context.setParentLoaderPriority(true);
		server.setHandler(context);
		try {
			try {				
				server.start();
			} finally {
				launchBrowser();
			}			
			server.join();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		System.exit(0);

	}
	
	private static void launchBrowser() throws Exception{
		java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
		desktop.browse(new URI("http://localhost:8888/dirigent-blazeds/Dirigent.swf"));
	}
}
