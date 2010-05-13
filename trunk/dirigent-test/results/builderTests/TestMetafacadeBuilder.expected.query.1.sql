/*Mapping: M_EMPLOYEE. Query generated by DIRIGENT.*/
SELECT 
	E.EMP_ID AS "EMPLOYEE_ID",
	E.EMP_FIRST_NAME||' '||E.LAST_NAME AS "EMPLOYEE_NAME",
	D.DEPT_ID AS "DEPARTMENT_ID",
	D.DEPT_NAME AS "DEPARTMENT_NAME"
FROM
	PUBLIC.DEPT D
	,PUBLIC.EMP E
WHERE
	(1=1)

/*End of mapping: M_EMPLOYEE.*/