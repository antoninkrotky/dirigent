# Introduction #

DIRIGENT IDE is VMware virtual machine configured to develop and test dirigent-demo project. VMWare version 3.1.2 build-301548 was used to create virtual machine. The image can be downloaded [here](http://www.gemsystem.cz/Dirigent-IDE.zip).

It is based on Ubuntu linux and contains Eclipse IDE, Oracle XE demo database, Oracle Pentaho Data integration with instaled dirigennt add-in. The oracle database contains demo Enterprise Architect model and demo datamart schema. All datamart layers (L0, L2) are for simplicity in one schema which is not recomended for real world projects.


# Credentials #

## OS ##
User: user

Password: password


## Oracle database ##
SID: XE

Host: localhost

Port: 1521


### Enterprise Architect repository (model) schema ###
User: ea\_repository

Password: ea\_repository

To configure Enterprise Architect repository connesction in host OS follow this link: http://www.sparxsystems.com/enterprise_architect_user_guide/projects_and_teams/connecttoaoracle9idatarep.html

Use datasource guest\_ip:1521/xe when configuring ODBC connection in EA. Guest IP can be retrieved using ifconfig command in DIRIGENT-IDE terminal. May be 192.168.114.128:1521/xe should be working by default.



### Demo datamart schema ###
User: dirigent\_demo\_dm

Password: dirigent\_demo\_dm


### System, sys  password ###
manager