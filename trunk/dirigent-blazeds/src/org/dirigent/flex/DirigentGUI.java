package org.dirigent.flex;

import java.net.URI;

import org.dirigent.config.DirigentConfig;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class DirigentGUI {
	
	public static final String DIRIGENT_BROWSER_ROOT_URI="dirigent.gui.browser.root.uri";
	public static final String DIRIGENT_BROWSER_PORT="dirigent.gui.browser.port";
	
	
	public static void main(String args[]) {
		String webContentPath="WebContent";
		if (args.length>0) {
			webContentPath=args[0];
		}
		launchDirigentGUI(webContentPath);
	}

	public static void launchDirigentGUI(String webContentPath) {
		int port=Integer.parseInt(DirigentConfig.getDirigentConfig().getProperty(DIRIGENT_BROWSER_PORT,"8888"));
		Server server = new Server(port);
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
				launchBrowser(port);
			}			
			server.join();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		System.exit(0);

	}
	
	private static void launchBrowser(int port) throws Exception{
		java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
		desktop.browse(new URI("http://localhost:"+port+"/dirigent-blazeds/Dirigent.swf"));
	}
}
