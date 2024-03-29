    
	   	/*Create script for fact table F_SALES*/  
	   	CREATE TABLE F_SALES (
	   		QUANTITY INTEGER,
    		TOTAL_PRICE NUMBER(38,2),
    		UNIT_PRICE NUMBER(38,2),
    		    		
    		TIME_KEY INTEGER,
    		INV_KEY INTEGER,
    		PROD_KEY INTEGER,
    		CUST_KEY INTEGER,
    		    		
    		SALES_KEY NUMBER(38) PRIMARY KEY,
    		SALES_UPDATED_DATETIME DATE
	   	);
	   	
   		   			ALTER TABLE F_SALES ADD CONSTRAINT FK_SALES_TIME
   			FOREIGN KEY (TIME_KEY) REFERENCES D_TIME(TIME_KEY) DISABLE;
   		   			ALTER TABLE F_SALES ADD CONSTRAINT FK_SALES_INV
   			FOREIGN KEY (INV_KEY) REFERENCES D_INVOICE(INV_KEY) DISABLE;
   		   			ALTER TABLE F_SALES ADD CONSTRAINT FK_SALES_PROD
   			FOREIGN KEY (PROD_KEY) REFERENCES D_PRODUCT(PROD_KEY) DISABLE;
   		   			ALTER TABLE F_SALES ADD CONSTRAINT FK_SALES_CUST
   			FOREIGN KEY (CUST_KEY) REFERENCES D_CUSTOMER(CUST_KEY) DISABLE;
   		   		
   		/*Create sequence for generation of surrogate key values.*/   
    	CREATE SEQUENCE SALES_SEQ;
	   
	   
