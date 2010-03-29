drop table PUBLIC.EMP;
drop table PUBLIC.DEPT;


create table PUBLIC.EMP  (EMP_ID INTEGER, DEPT_ID INTEGER,EMP_FIRST_NAME VARCHAR(255), LAST_NAME VARCHAR(255));
create table PUBLIC.DEPT (DEPT_ID INTEGER, DEPT_NAME VARCHAR(255));
create table PUBLIC.EMPLOYEE (EMPLOYEE_ID INTEGER, EMPLOYEE_NAME VARCHAR(255),DEPARTMENT_ID INTEGER, DEPARTMENT_NAME VARCHAR(255));

insert into PUBLIC.DEPT values (1,'Prague');
insert into PUBLIC.DEPT values (2,'London');
insert into PUBLIC.DEPT values (3,'Paris');

insert into PUBLIC.EMP values (1,1,'John','Smith');
insert into PUBLIC.EMP values (2,2,'James','Blake');
insert into PUBLIC.EMP values (3,3,'Gregoire','Plastique');

commit;