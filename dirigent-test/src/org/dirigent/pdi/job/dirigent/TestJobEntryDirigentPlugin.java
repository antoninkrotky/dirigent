package org.dirigent.pdi.job.dirigent;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import junit.framework.TestCase;

import org.pentaho.di.core.exception.KettlePluginException;
import org.pentaho.di.core.exception.KettleXMLException;
import org.pentaho.di.core.plugins.JobEntryPluginType;
import org.pentaho.di.core.plugins.Plugin;
import org.pentaho.di.core.plugins.PluginInterface;
import org.pentaho.di.core.plugins.PluginRegistry;
import org.pentaho.di.core.plugins.PluginTypeInterface;
import org.pentaho.di.job.entry.JobEntryInterface;

public class TestJobEntryDirigentPlugin extends TestCase {

	private static final String DIRIGENT_CATEGORY = "General";

	private static final String DIRIGENT_PLUGIN_ID = "DIRIGENT plugin";
	private static final String DIRIGENT_PLUGIN_NAME = "DIRIGENT";
	private static final String DIRIGENT_PLUGIN_DESCRIPTION = "This is Data Integration Generator Plugin (version 1)";
	private static final String DIRIGENT_PLUGIN_IMAGE_FILE_NAME = "plugins/jobentries/Dirigent/icon.png";
	private static final String DIRIGENT_PLUGIN_FOLDER = "plugins/jobentries/Dirigent";

	public void testPluginRegistry() throws KettlePluginException,
			KettleXMLException {
		// Register new plugin type
		PluginRegistry registry = PluginRegistry.getInstance();
		Class<? extends PluginTypeInterface> pluginType = JobEntryPluginType.class;
		registry.registerPluginType(pluginType);

		// Register dirigent plugin
		Map<Class<?>, String> classMap = new HashMap<Class<?>, String>();
		classMap.put(JobEntryInterface.class,
				"org.dirigent.kettle.JobEntryDirigentPlugin");

		ArrayList<String> libraries = new ArrayList<String>();
		libraries.add("plugins/jobentries/Dirigent/dirigent.jar");
		URL url = null;
		try {

			url = new URL("file", "", -1, DIRIGENT_PLUGIN_FOLDER);
		} catch (MalformedURLException e) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,
					"Wrong plugin folder \n" + e.getMessage());
		}
		PluginInterface dirigentPlugin;

		dirigentPlugin = new Plugin(new String[] { DIRIGENT_PLUGIN_ID, },
				pluginType, JobEntryInterface.class, DIRIGENT_CATEGORY,
				DIRIGENT_PLUGIN_NAME, DIRIGENT_PLUGIN_DESCRIPTION,
				DIRIGENT_PLUGIN_IMAGE_FILE_NAME, false, false, classMap,
				libraries, null, url);

		registry.registerPlugin(pluginType, dirigentPlugin);
		List<PluginInterface> stepPlugins = registry.getPlugins(pluginType);
		assertEquals("Size of plugins list expected to be 1", 1, stepPlugins
				.size());
	}

	// public void testDirigentJob() throws Exception {
	// KettleEnvironment.init();
	// PluginRegistryTest.
	// PluginRegistry.init();

	// JobEntryDirigentPlugin jobEntry = new JobEntryDirigentPlugin();

	// DirigentPluginData pluginData = new DirigentPluginData();

	// JobMeta jobMeta = new JobMeta();
	// JobMeta jobMeta = new
	// JobMeta("resources/pluginTests/model_8/kettle_job.kjb", null, null);

	// jobMeta.

	// jobMeta.
	//		
	//		
	// jobEntry.loadXML(this.createNode(), jobMeta.getDatabases(),
	// jobMeta.getSlaveServers(), null);
	// }

}
