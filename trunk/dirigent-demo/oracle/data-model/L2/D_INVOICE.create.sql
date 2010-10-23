    
    	/*Create script for dimension D_INVOICE*/  
    	CREATE TABLE D_INVOICE (
    		INV_KEY NUMBER(38) PRIMARY KEY,
    		INV_DUE_DATE DATE,
    		INV_ID VARCHAR(50),
    		INV_PAYMENT_DATE DATE,
    		INV_PAYMENT_FLAG CHAR(1),
    					
			INV_VALID_FROM_DATETIME DATE,
			INV_VALID_TO_DATETIME DATE,
			INV_CURRENT_FLAG CHAR(1),
			INV_UPDATED_DATETIME DATE
    	);

		/*Create sequence for generation of surrogate key values.*/   
    	CREATE SEQUENCE INV_SEQ;
    
    
