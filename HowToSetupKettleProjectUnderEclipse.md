# How To Run/Debug Kettle Under Eclipse #

## Purpose ##

It took me about a day or longer to setup Kettle under Eclipse so that I can run/debug it. This document is here for those who will need it to debug this plugin in Eclipse.

# Details #

# checkout Kettle using svn from http://source.pentaho.org/svnkettleroot/Kettle/trunk/
# check that your local directory structure is as follows
```
<directory of your choice>
`-- trunk
     ... 
```
  1. Import project to Eclipse as Java Project (File -> Import)
  1. Checkout Build Path of the project
    * all .jars are in trunk/<some folder> on build path by default (that's why step 2 is important)
    * verify there is only one swt.jar file on build path and **that it corresponds with your architecture**. Use trunk/libswt/linux/<your architecture>/swt.jar for linux.
  1. Create new **Java Application Run Configuration** in Eclipse
    * add project name
    * set main class to org.pentaho.di.ui.spoon.Spoon
    * set VM argument to where swt.jar is present. For Linux x64 it is _-Djava.library.path=libswt/linux/x86\_64_
    * set Working directory to _${workspace\_loc:<Project Name>/distrib}_. My example is _${workspace\_loc:Kettle trunk/distrib}_
  1. now you should be able to build project from Eclipse. So run Ant build on build.xml and pray for success ;). Once it's done, distrib directory is created and you should be able to run/debug directly from Eclipse. If you wish to run without Eclipse, then run spoon.sh/Spoon.bat located in distrib directory.