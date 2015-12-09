#how to setup eclipse run/debug configuration in order to debug kettle plugins

# How To Debug Kettle Plugin Under Eclipse #

Once you set up Kettle run/debug configuration under Eclipse you may want to debug plugin, for example this one ;) (sources for plugin have not been committed yet). This document is here for you then and for everybody else who suffers from lack of documentation for Kettle.

In case you find any mistake or easier way how to debug plugins, let me know, please.

# Details #

I assume you have checked out kettle. This instructions are for this plugin and configuration is for plugin project, not for kettle.

  * follow information on [this blog](http://pentahodev.blogspot.com/2009/08/developdebug-kettle-plugin-in-eclipse.html)
  * then set VM variables in your run/debug configuration as below. Use correct path for swt.jar according to your OS.

`-Djava.library.path=libswt/linux/x86_64 -DKETTLE_PLUGIN_CLASSES=org.dirigent.kettle.ui.DirigentPluginDialog,org.dirigent.kettle.DirigentPlugin,org.dirigent.kettle.DirigentPluginData,org.dirigent.kettle.DirigentPluginMeta`
  * make sure swt.jar is on build path only once and it's correct
  * build plugin (HowToBuildDirigentPlugin)

# Links #

  1. [How To Write Pentaho Plugins On Pentaho Wiki](http://wiki.pentaho.com/display/EAI/Writing+your+own+Pentaho+Data+Integration+Plug-In)
  1. [How To Debug Kettle 4+ Plugins on Pentaho wiki](http://wiki.pentaho.com/display/EAI/How+to+debug+a+Kettle+4+plugin)
  1. HowToBuildDirigent