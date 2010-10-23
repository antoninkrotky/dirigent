    
    	/*Create script for dimension D_PRODUCT*/  
    	CREATE TABLE D_PRODUCT (
    		PROD_KEY NUMBER(38) PRIMARY KEY,
    		PROD_GROUP_CODE VARCHAR(100),
    		PROD_GROUP_NAME VARCHAR(100),
    		PROD_ID VARCHAR(50),
    		PROD_NAME VARCHAR(100),
    					
			PROD_VALID_FROM_DATETIME DATE,
			PROD_VALID_TO_DATETIME DATE,
			PROD_CURRENT_FLAG CHAR(1),
			PROD_UPDATED_DATETIME DATE
    	);

		/*Create sequence for generation of surrogate key values.*/   
    	CREATE SEQUENCE PROD_SEQ;
    
    
