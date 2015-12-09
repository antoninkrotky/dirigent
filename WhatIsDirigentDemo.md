# Introduction #
The target of dirigent-demo project is to demonstrate dirigent capatibilities in diferent usage scenarios.

# Project structure #
## model folder ##
Contains exported dirigent-demo Enterprise Architect model in XMI format. All changes in dirigent-demo model must be synchronized to this file.

### Merging model in Enterprise Architect ###
The actual version of dirigent-demo model is maintained in SVN (file dirigent-demo/model/DEMO.xml). To merge changes from SVN to EA repository follow theese steps:
  1. Open your model in Enterprise Architect
  1. Right click the DEMO folder
  1. From the context menu select "Package control" -> "Compare with XMI file..."
  1. Select the last version of DEMO.xml from repository
  1. Your model is compared with last version from SVN. If you want to propagate change from SVN version to your model right click on specified element and select "Merge from Baseline".
After your model is merged with lastest SVN version you can export your model from EA and commit to SVN.

If you wannt just to update your model to latest SVN version, right click on DEMO folder and select "Export/Import" -> "Import package from XMI file...". This will completely overwrite your EA model.

## oracle folder ##
_Under Implementation_

Demonstration of dirigent capatibilities for Oracle datamart implementation project. This demo uses Enterprise Architect model of L0 (interface / data sources) layer, L2 (datamart presentation layer) and data mapping (L0 to L2 transformation) model. Demo uses ORACLE patterns from core dirigent project (dirigent/resources/patterns/ORACLE).

Dirigent is used to:
  * generate database object creation scripts of datamart L2 (presentation) layer
  * implement ELT mappings performing population of datamart

Patterns are designed to support "declarative MDA approach". This means that model is free of implementation details, which are moved to dirigent patterns. Good example is of this approach is the way how dimension tables are modelled. For each dimension table, only simple class with BIDimension stereotype is modelled without surrogate key and implicit dimension columns. The dirigent pattern ensures generation of database object creation scripts including implicit dimension table columns, database sequence and DML for implicit dimension rows (XNA, XNU values). So design standards and patterns are not part of model, but are contained in dirigent patterns.

The build.xml file contains ant targets to execute demo dirigent and other helper tasks. Oracle database (XE is sufficient) is required for executing this demo.
Core tasks are:
  * L0.install - install L0 layer
  * L2.install - generate L2 layer based on Enterprise Architect model using dirigent
  * L0.load - Load L0 layer
  * L2.load - Load L2 layer using dirigent

The pdi folder contains Pentaho data integration transformation a jobs demonstrating integration of dirigent with PDI. The Main job ensures datamart loading process complete. The L0 layer is loaded using PDI native transformations, the L2 layer is loading using dirigent SCD and fact table loading patterns.