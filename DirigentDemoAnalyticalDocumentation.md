# Contents #

  * Introduction
  * Basic principles
  * Dirigent project: package description
  * Sequence diagrams
    * Basic course
    * Metafacade creation
    * Metafacade creation - decorator


# Introduction #
In general, the Dirigent project provides transformation of software models from different sources into the output files (scripts, source codes). The model can generally be a file or database repository created by different modelling tools, the output is defined by XML patterns which are used according to further setups in the dirigent configuration file.

This document deals with principles of those parts of the application, which are important for the dirigent-demo package. This package should demonstrate the possibilities of the dirigent project. It provides the required functionality for data-warehouse-related tasks. That means transforming the data warehouse model into SQL scripts which:

  * create the dimensions and fact table in the database
  * loads the data from the source database into the data warehouse (ELT process)


# Basic principles #
Project Dirigent contains GUI using Flex technology, which is not available neither open source. During the dirigent-demo development, only ant script which will provide executing of all desired operations (table generation, transformation scripts generation, executing the scripts, …). This script is placed in the dirigent-demo/oracle/build.xml directory and contains following targets:

  * **L0.install**: runs SQL scripts which trunctate and recreate L0 layer of the database (source data layer).
  * **L0.load**: loads data to the L0 layer.
  * **L2.generate**: calls the main method of Dirigent and passes the following URI to it: {479A188C-C78E-47c3-8829-2B418E9F5989}. This is the URI of model package "L2 - Datamart Layer". First, Dirigent generates the transformed data for this element. There is no pattern for this element type, however, as it is an IComposite element, its children elements (which are dimensions and fact table) are transformed into the output data. So, the result are drop and create scripts for dimensions and fact table.
  * **L2.install**: executes all drop scripts and than executes all create scripts created by L2.generate
  * **L2.load**: SCD mapping itself. Generates and also executes scripts for ELT processes.



The program operation starts with an org.dirigent.Dirigent.main(String[.md](.md)) method call, which expects the first parameter to be an URI of the model object to be transformed into the output data.

**Image:** Dirigent - component diagram

![http://dirigent.googlecode.com/svn/wiki/img/image1.png](http://dirigent.googlecode.com/svn/wiki/img/image1.png)


The program has following inputs:

  * model (currently, Enterprise Architect database repository and CSV files are supported), which contains data to be transformed into the result
  * XML patterns defining the transformation into the result data
  * URI of the model element, which should be transformed. According to the element type, appropriate patterns are used to create the result data.
  * configuration file (containing information such as model type etc.)



The model element with selected URI is chosen and a metafacade for it is generated, i.e. the model element (elements) are transformed from the proprietary input data format to an uniform object representation. According to the element type, appropriate pattern is chosen for the output data generation. The output data can be written into file or executeda as an SQL script etc. (depending on current setup).

Program can be divided into following components (see diagram above):

  * **Generator** - contains the main course of the program, directs other component methods calls
  * **MetafacadeBuilder** - according to the model type returns appropriate Decorator
  * **Decorator** - creates metafacade based on the data from VO objects
  * **PatternBuilder** - generates the output data according to the selected pattern
  * **Executor** - different Executor types treat the PatternBuilder output data differently: they write them into file, execute them etc.
  * **DirigentConfig** - provides an access to the configuration file setup for other components


# Dirigent project: package description #
Dirigent consists of four Eclipse projects:

  * **dirigent** - basic project containing the application core (model loading, metafacade creation, pattern loading and model transformation), working as anconsole application.
  * **dirigent-demo** - the part of application dealing with data warehouses (demonstrating Dirigent project possibilities).
  * **dirigent-blazeds** - GUI using BlazeDS technology.
  * **dirigent-test** - JUnit tests of dirigent and dirigent-demo projects



The following paragraphs describe the packages of the dirigent project.
### org.dirigent ###
Contains (apart from another packages) only one class, Dirigent.java, which contains only main method, which is called while the application is executed. It only calls the Generator.generate method and passes the first command line argument to it (supposing it is a model object URI).
### org.dirigent.config ###
Provides access to the confiiguration file and its data (model type and URL, model login data if necessarry etc.). This document does not deal with this package.
### org.dirigent.executor ###
Classes which perform appropriate operation with the output data. Currently, two executors exist: FileExecutor, which saves the data to a file, and JDBCStatementExecutor, executing the data as an SQL script under specified database (both being IPatternExecutor class children). Further, ExecutorFactory class is a part of this package, with method getExecutor(String)returning appropriate Executor according to the desired operation type (the operation type is passed as a string in the argument - the string can be obtained from IPatternStep.getType() method for each IPatternStep object).
### org.dirigent.generator ###
Contains Generator class, generating output using patterns.
### org.dirigent.metafacade ###
The root package contains interfaces defining the metafacade objects  (e.g. IMapping, IDimension, ITable). Following diagram shows all the metafacade interfaces and its inheritance relationships.

**Image:** org.dirigent.metafacade package - class diagram (part 1, 2)

![http://dirigent.googlecode.com/svn/wiki/img/image2.png](http://dirigent.googlecode.com/svn/wiki/img/image2.png)
![http://dirigent.googlecode.com/svn/wiki/img/image4.png](http://dirigent.googlecode.com/svn/wiki/img/image4.png)

### org.dirigent.metafacade.builder ###
Contains abstract class MetafacadeBuilder wiht abstract methods defining interface for children metafacade builders (with implementation depending on the model format). Next, it contains static method getMetafacadeBuilder, which according to the model type setup (property dirigent.model.type of DirigentConfig class) returns appropriate MetafacadeBuilder.
Important (but in MetafacadeBuilder abstract) method is getMetafacade(String), which returns the metafacade object (IElement) for object with specified URI (string in the parameter).
### org.dirigent.metafacade.builder.csv ###
Classes for generating metafacade from CSV files. This document does not deal with this package.
### org.dirigent.metafacade.builder.decorator ###
Contains general Decorators - classes implementing some interface from org.dirigent.metafacade package, which provide a metafacade for the model data which they obtain from VO objects from org.dirigent.metafacade.builder.vo package. These decorators are e.g. AttributeDecorator (implementing IAttribute with AttributeVO data source), DimensionDecorator etc.
### org.dirigent.metafacade.builder.vo ###
Contains general metafacade VO objects - AttributeVO, BIColumnVO etc. They represent the metafacade data model, which is independent on the original model format.
### org.dirigent.metafacade.builder.ea ###
Contains EAHelper class, providing a connection to the EA repository, and EAMetafacadeBuilder class, which is a child of MetafacadeBuilder. Its most important method, getMetafacade(String), returns an EA...Decorator instance, according to the original model object type.
org.dirigent.metafacade.builder.ea.dao, org.dirigent.metafacade.builder.ea.vo
DAO and DTO (VO) objects for an EA model.
### org.dirigent.metafacade.builder.ea.decorator ###
Decorators for EA model (similar to org.dirigent.metafacade.builder.decorator, but using VO objects from package org.dirigent.metafacade.builder.ea.vo, which represent the EA model repository data).
### org.dirigent.pattern ###
Package with classes being used for generating the XML patternoutput. This document does not deal with this package.
### org.dirigent.pdi.job.dirigent ###
Integration to the Pentahoo Data Integration. This document does not deal with this package.

# Sequence diagrams #
## Basic course ##
![http://dirigent.googlecode.com/svn/wiki/img/image0.png](http://dirigent.googlecode.com/svn/wiki/img/image0.png)

After being executed, program calls the Generator.generate(String) method and passes the first argument of the command line to it. The argument of the overloaded generate method is either and string with model element URI or an IGenerable instance, which is an metafacade object
Method generate(String), which has and model element URI in the argument, provides metafacade generation for the object with the specified URI and than calls method generate, with the generated IGenerable instance in the argument.
Method generate(IGenerable) choses appropriate pattern (IPattern) according to the  IGenerable type and provides the output generation (not displayed in the diagram).


Following sequence diagrams are based on a example course when a metafacade for a Dimension object in EA model is being generated. The Dimension metafacade object is generated for EA model class element with BiDimension stereotype.

Note: as can be seen, one object type in the original model can be represented by various metafacade objects and vice versa (the decision, which metafacade object will be used for representing the actual model object, can be made not only based on the element type, but also by other element properties - here, for example, by stereotype).

## Metafacade creation ##

![http://dirigent.googlecode.com/svn/wiki/img/image3.png](http://dirigent.googlecode.com/svn/wiki/img/image3.png)

Static method MetafacadeBuilder.getMetafacadeBuilder() is called. This method returns implementation of abstract class MetafacadeBuilder, which corresponds to the model type (defined in the configuration file). In this example, Enterprise Architect model is used, so a new instance of EAMetafacadeBuilder is returned. This object generates the metafacade from the model.
Method getMetafacade(String) is called with model element URI in parameter (in our example, this object will be of “type” Dimension). The EAElementDAO retrieves instance v:EAElementVO for this model element (by the EAElementDAO.getEAElement(String) method with model element URI in parameter). The VO object provides access to the properties of the model element with the selected URI.
According to the v properties (in this case v.type a v.stereotype) the appropriate metafacade object type is chosen to be generated and a decorator is created - in this case, it is EADimensionDecorator. More in the following diagram.

## Metafacade creation - decorator ##
The diagram shows calls of parent’s methods and constructors from children’s methods and constructors during the creation of the metafacade for Dimension element. The meaning of the UML Sequence diagram elements is different from the standard in this diagram. When :A instance calls a method of super:B instance, it means, that a method of B class, which is a parent class of A class (object :A and object super:B are therefore the same object; this call means therefore a call of super.xyz() method). When the first action after creation of :A instance a super:B instance is created, it means a parent’s constructor call in the constructor of the :A object (ie. the super() call).

![http://dirigent.googlecode.com/svn/wiki/img/image5.png](http://dirigent.googlecode.com/svn/wiki/img/image5.png)

(Continues from the previous diagram.) Class EADimensionDecorator implements IDimension interface and so, the EADimensionDecorator instance itself is the resulting metafacade. In the constructor parameter, an EAElementVO object for the selected model element is passed (this is the v:EAElementVO from the previous diagram). The EADimensionDecorator parent’s constructors are of course being called by the constructor of this class.
In the constructor of EATableDecorator (direct parent of EADimensionDecorator) a TableVO instance is created, which is a metafacade VO object (not an EA model VO object). EATableDecorator.init(EAElementVO, TableVO) method is called, which calls EAElementDecorator.init(EAElementVO, ElementVO) method. This method loads the data from  EAElementVO (first parameter) into ElementVO (second parameter) - pseudomethods EAElementVO.getProperties() and ElementVO.setProperties(). This is the metafacade creation itself:

```
public static ElementVO init(EAElementVO ea,ElementVO v) {        
       v.uri=ea.guid;
       v.id=ea.objectId;
       v.name=ea.name;
       v.type=ea.type;
       v.description=ea.note;
       v.stereotype=ea.stereotype;
       v.packageId=ea.packageId;
       v.properties=new
         EAObjectPropertyDAO().getObjectProperties(ea.objectId);
       v.parentUri=ea.parentGuid;
       v.alias = ea.alias;
       v.keywords = ea.keywords;
       return v;
   }
```

Some properties have different names in the metafacade: e.g. in the EA model, element’s ID is stored in guid, while in the metafacade the name of this property is uri. Etc.

The created TableVO (in general, ElementVO) is then passed to the constructors of further parents (here TableDecorator and ElementDecorator), these save a pointer to this VO object - because of overloaded methods. The IDimension interface is a child of ITable and that is a child of IElement, and some method calls to EADimensionDecorator are passed to parent Decorators.

For example, if a getName() method is called (a method of  IElement interface, so the EADimensionDecorator has this method as well - as it implements IDimension), it is served by ElementDecorator.getName() method. ElementDecorator holds the pointer to TableVO and returns  its name property value.
Directly in EADimensionDecorator, methods dealing with dimension-specific properties are implemented - e.g. getSCDType().

There are also some methods returning another metafacade objects, for example IElement.getParent(). This method returns an IElement instance containing the metafacade of the current element’s parent. In this case, the generating of the required metafacade objects is called in the body of this method:
```
   @Override
   public IElement getParent() {
       if ( ! element.parentUri.equals("") ) {
             return MetafacadeBuilder
                  .getMetafacadeBuilder()
                  .getMetafacade(element.parentUri);
       } else {
           l.warning("element " + element.name + " (" + element.uri + ") don't have any parent!");
           return null;
       }
   }
```

So, when we call this method, a metafacade object is generated for the parent element (if it exists), ie. whole the process described by this and previous diagram runs again (for another object of the model).
Similarly, the object’s attributes are generated for example (method getAttributes() returning a collection of IAttribute). The only difference is, that IElement holds a pointer to the collection, but the collection is initialized and generated after the first call of getAttributes() method (lazy loading).