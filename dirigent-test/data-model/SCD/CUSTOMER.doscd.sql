    
		DROP TABLE STG_D_CUSTOMER;
    
    
    
		CREATE TABLE STG_D_CUSTOMER
		(
				"CUST_ADDRESS_CITY",
	"CUST_ADDRESS_STREET",
	"CUST_ADDRESS_ZIP",
	"CUST_ID",
	"CUST_NAME"

		)
		as
		/*Mapping: CUSTOMER. Query generated by DIRIGENT.*/
SELECT 
	A.CITY
	 AS "CUST_ADDRESS_CITY",
	A.STREET
	 AS "CUST_ADDRESS_STREET",
	A.ZIP
	 AS "CUST_ADDRESS_ZIP",
	C.CUSTOMER_ID
	 AS "CUST_ID",
	C.FIRST_NAME + ' ' + C.SURNAME
	 AS "CUST_NAME"
FROM
	L0_CUSTOMER_ADDRESS A
	 JOIN L0_CUSTOMER C ON C.ADDRESS_ID=A.ADDRESS_ID
WHERE
	(1=1)

/*End of mapping: CUSTOMER.*/
;
    
    
    

    
    
    

    
    
 


	MERGE INTO D_CUSTOMER target
	USING
		(
		SELECT *
		FROM STG_D_CUSTOMER
		MINUS
			(
			SELECT 	"CUST_ADDRESS_CITY",
	"CUST_ADDRESS_STREET",
	"CUST_ADDRESS_ZIP",
	"CUST_ID",
	"CUST_NAME"

			FROM D_CUSTOMER
			WHERE CUST_VALID_TO_DATETIME > sysdate
			)
		) stage
	ON
		(
			target.CUST_ID = stage.CUST_ID
			
			AND target.CUST_VALID_TO_DATETIME > sysdate
		)
	WHEN MATCHED THEN
		UPDATE
		SET
														target.CUST_NAME = stage.CUST_NAME,
												target.CUST_ADDRESS_STREET = stage.CUST_ADDRESS_STREET,
												target.CUST_ADDRESS_CITY = stage.CUST_ADDRESS_CITY,
												target.CUST_ADDRESS_ZIP = stage.CUST_ADDRESS_ZIP,
							target.CUST_UPDATED_DATETIME = sysdate
		
	WHEN NOT MATCHED THEN
		INSERT
		(
		CUST_ID,
		CUST_NAME,
		CUST_ADDRESS_STREET,
		CUST_ADDRESS_CITY,
		CUST_ADDRESS_ZIP,
		    	CUST_VALID_FROM_DATETIME,
		CUST_VALID_TO_DATETIME,
		CUST_CURRENT_FLAG,
		CUST_UPDATED_DATETIME
    	)
    	VALUES
    	(
			stage.CUST_ID,
			stage.CUST_NAME,
			stage.CUST_ADDRESS_STREET,
			stage.CUST_ADDRESS_CITY,
			stage.CUST_ADDRESS_ZIP,
			    		sysdate,
   			to_date('1.1.3000','dd.mm.yyyy'),
   			'Y',
   			sysdate
    	);
	    	
	    	
   	UPDATE D_CUSTOMER target
   	SET 
   		target.CUST_CURRENT_FLAG = 'N',
   		target.CUST_VALID_TO_DATETIME = sysdate
   	WHERE EXISTS
   	(
   		SELECT * from STG_D_CUSTOMER stage
   		WHERE
   		target.CUST_VALID_TO_DATETIME > sysdate
   		AND
   		target.CUST_ID = stage.CUST_ID   		
   		
   																																);
    
    
