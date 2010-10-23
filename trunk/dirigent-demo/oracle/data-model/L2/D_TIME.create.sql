    
    	/*Create script for dimension D_TIME*/  
    	CREATE TABLE D_TIME (
    		TIME_KEY NUMBER(38) PRIMARY KEY,
    		TIME_ID DATE,
    		TIME_MONTH_CODE VARCHAR(50),
    		TIME_YEAR_CODE VARCHAR(50),
    					
			TIME_VALID_FROM_DATETIME DATE,
			TIME_VALID_TO_DATETIME DATE,
			TIME_CURRENT_FLAG CHAR(1),
			TIME_UPDATED_DATETIME DATE
    	);

		/*Create sequence for generation of surrogate key values.*/   
    	CREATE SEQUENCE TIME_SEQ;
    
    
