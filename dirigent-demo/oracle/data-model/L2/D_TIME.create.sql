    
    	/*Create script for dimension D_TIME*/  
    	CREATE TABLE D_TIME (
    		D_TIME_KEY NUMBER(38) PRIMARY KEY,
    		TIME_ID DATE,
    		TIME_MONTH_CODE VARCHAR(50),
    		TIME_YEAR_CODE VARCHAR(50),
    		D_TIME_VALID_FROM_DATETIME DATE,
			D_TIME_VALID_TO_DATETIME DATE,
			D_TIME_CURRENT_FLAG CHAR(1),
			D_TIME_UPDATED_DATETIME DATE
    	);

		/*Create sequence for generation of surrogate key values.*/   
    	CREATE SEQUENCE D_TIME_SEQ;
    
    
