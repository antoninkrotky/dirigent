/*Mapping: EMPLOYEE. Query generated by DIRIGENT.*/
SELECT 
	E.EMPNO AS "EMPLOYEE_ID",
	E.ENAME AS "EMPLOYEE_NAME",
	D.DEPTNO AS "DEPARTMENT_ID",
	DNAME AS "DEPARTMENT_NAME"
FROM
	PUBLIC.SCOTT_DEPT D
	 JOIN PUBLIC.SCOTT_EMP E ON D.DEPTNO=E.DEPTNO
WHERE
	(1=1)

/*End of mapping: EMPLOYEE.*/