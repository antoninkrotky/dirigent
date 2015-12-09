# Tutorial for the Dirigent project #
## Glossary ##
  * EA (Enterprise Architect) - CASE tool(http://www.sparxsystems.com/products/ea/)
  * ETL - the process of transformation and transmission of data (http://en.wikipedia.org/wiki/Extract,_transform,_load)
  * ELT - a process similar to the ETL process, but data transformation is the last step of the whole process.
  * Velocity - framework (http://velocity.apache.org/docbook/)

## Application groundwork ##
  1. Creation of data model for data source
  1. Creation of data model
  1. Creation of transformation model
  1. Loading of data source
  1. Creation of initial scripts
  1. Creation of transformation scripts
  1. Application start
  1. Template creation

### Creation of data model for data source in EA ###
The data model will be created with the application Enterprise Architect.

#### Process of creation ####
  1. Create a table
  1. Create a table attributes
  1. Set the primary key (s) in Table
  1. Set the association with other tables
Repeat the above procedure for each of your source tables

### Creation of data model in EA ###
The data model will be created with the application Enterprise Architect.

#### Process of creation ####
  1. Creation of dimensions
    1. Create an object of Class type
    1. Set the BiDimension stereotype to this object.
    1. Create dimension attributes and set them the BiDimensionColumn stereotype.
  1. Creation of a fact table
    1. Create an object of Class type
    1. Set the BiFact stereotype to this object.
    1. Create a Fact table attributes

### Creation of transformation model in EA ###
The transformation model will be created with the application Enterprise Architect.

#### Process of creation ####
  1. Create an object of Class type with the stereotype setted to BiMapping
  1. Set the dependency between mapping and source table.
    1. Dependency direction is always from mapping to source table.
    1. Set the BiMappingSource stereotype.
    1. Set the alias of source table in dependency properties.
    1. There are these Tagged Values:
      1. JoinType
      1. JoinCondition
      1. JoinOrder
  1. Set the dependency between mapping and target tables (data model dimensions)
    1. Dependency direction is always from mapping to target table.
    1. Set the BiMappingTarget stereotype.
  1. Create the attributes of mapping with the stereotype setted to BiMappingColumn
> > Attributes mapping are consistent with the attributes of the target table.
  1. For all the attributes in note give a fragment of SQL query, which output will be mapped to the corresponding attribute of the target table.

### Loading of data source ###
Loading of the source data is possible in several ways. Source data can be obtained from:
  * XML
  * XMI
  * CSV
  * DB

### Creation of initial scripts ###
Creation scripts performs the tool Dirigent itself.

### Creation of transformation scripts ###
Creation scripts performs the tool Dirigent itself.

### Application start ###

#### Process of creation ####
To run the project is now necessary to have a Dirigent development environment installed(which can be obtained here). It is necessary to follow the instructions to synchronize the Enterprise Architect model, which is stored in XMI format (instructions are available here). It should be noted that for the sample application Dirigent-demo is not necessary to create a model or a transformation model, both of them are contained in the above-mentioned XMI file.
The project itself is run as a ant script which is stored in dirigent-demo/oracle/build.xml.
##### This script has several goals #####

  * **L0.install** - This target performs the SQL scripts for the cancellation and re-creation of L0 (source data) in the database layer.
  * **L0.load** - This target takes care of filling L0 layer with data.
  * **L2.generate** - This target calls the main method of Dirigent, together with a URI {479A188C-C78E-8829-47c3-2B418E9F5989}. This URI refers to a package "L2 - Layer Datamart. Dirigent tries to make the generation of this element (package). For this element, however, no template is created. Since this element type is IComposite, not only it, but also its children will begin to generate (and its children are the dimensions and fact table). The result is generation of drop and create scripts for dimensions and fact table.
  * **L2.install** - The objective of this target is to execute all the drop and create scripts that were created during L2.generate.
  * **L2.load** - At this point the SCD mapping will be executed. This time the scripts are executed directly because the templates for the mapping are run directly in database...

##### The entire project is then managed by targets #####
  * **L2.refresh** - Executes L2.generate, L2.install.
  * **default** - Executes L0.install, L2.refresh, L0.load, L2.load.

### Template creation ###

Templates are currently created as XML files. These files are located in _dirigent/resources/patterns_. In the current phase of the Dirigent project templates are created for Oracle, these templates are placed in _dirigent/resources/Patterns/Oracle/_. Template name takes the form: _[Name](Template.md).Pattern.xml_. Thus, for example FACT\_LOAD for Oracle are stored in the _dirigent/resources/Patterns/Oracle/FACT\_LOAD.pattern.xml_.

#### The content of template is based on the following template: ####
```
<?xml version="1.0" encoding="UTF-8"?>
<tns:Pattern xmlns:tns="http://www.example.org/dirigent-pattern/"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.example.org/dirigent-pattern/ ../dirigent-pattern.xsd ">
<Documentation>Dokumentace šablony</Documentation>


<PatternStep ignoreErrors="true" name="Drop Stage Table" type="File">
<Parameter name="fileName" value="data-model/L2/${element.name}.factLoad.sql" />
<Parameter name="mode" value="overwrite" />
<Template><![CDATA[
               SELECT * FROM ${element.name};
               ]]>
</Template>
</PatternStep>
</tns:Pattern>
```
#### Explanation: ####
  * There can be PatternStep elements.
  * ignoreErrors can take values true or false (for dropping a table, set to true)
  * PatternStep type may have values File or JDBCStatement. If the value is set to file there are even more required parameters:
    * filename - output filename
    * Mode - options overwrite or append

The template itself is made for the **Velocity framework**. Elements that are in processed in templates are **$ {element}**, which is a specific metafacade (depending on the object just processed - Fact table, mapping, dimensions, ...). In addition to **$ {element}**,  template contains **$ {utils}**, which is a class library of Dirigent - path to it leads to _org.dirigent.executor.TemplateUtils_.