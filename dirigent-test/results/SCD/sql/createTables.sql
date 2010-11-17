--------------------------------------------------------
--  DDL for Table D_CUSTOMER
--------------------------------------------------------

  CREATE TABLE "D_CUSTOMER" 
   (	"CUST_ADDRESS_CITY" VARCHAR2(50), 
	"CUST_ADDRESS_STREET" VARCHAR2(50), 
	"CUST_ADDRESS_ZIP" NUMBER, 
	"CUST_ID" NUMBER, 
	"CUST_NAME" VARCHAR2(50), 
	"CUST_VALID_FROM_DATETIME" DATE, 
	"CUST_VALID_TO_DATETIME" DATE, 
	"CUST_CURRENT_FLAG" CHAR(1), 
	"CUST_UPDATED_DATETIME" DATE
   ) ;
--------------------------------------------------------
--  DDL for Table D_INVOICE
--------------------------------------------------------

  CREATE TABLE "D_INVOICE" 
   (	"INV_ID" NUMBER(*,0), 
	"INV_DUE_DATE" DATE, 
	"INV_PAYMENT_DATE" DATE, 
	"INV_PAYMENT_FLAG" CHAR(1), 
	"INV_VALID_FROM_DATETIME" DATE, 
	"INV_VALID_TO_DATETIME" DATE, 
	"INV_CURRENT_FLAG" CHAR(1), 
	"INV_UPDATED_DATETIME" DATE
   ) ;
--------------------------------------------------------
--  DDL for Table D_PRODUCT
--------------------------------------------------------

  CREATE TABLE "D_PRODUCT" 
   (	"PROD_ID" NUMBER(*,0), 
	"PROD_GROUP_CODE" VARCHAR2(50), 
	"PROD_GROUP_NAME" VARCHAR2(50), 
	"PROD_NAME" VARCHAR2(50), 
	"PROD_UNIT_PRICE" NUMBER, 
	"PROD_VALID_FROM_DATETIME" DATE, 
	"PROD_VALID_TO_DATETIME" DATE, 
	"PROD_CURRENT_FLAG" CHAR(1), 
	"PROD_UPDATED_DATETIME" DATE
   ) ;
--------------------------------------------------------
--  DDL for Table D_TIME
--------------------------------------------------------

  CREATE TABLE "D_TIME" 
   (	"TIME_ID" DATE, 
	"TIME_MONTH_CODE" NUMBER(*,0), 
	"TIME_YEAR_CODE" NUMBER(*,0), 
	"TIME_VALID_FROM_DATETIME" DATE, 
	"TIME_VALID_TO_DATETIME" DATE, 
	"TIME_CURRENT_FLAG" CHAR(1), 
	"TIME_UPDATED_DATETIME" DATE
   ) ;
--------------------------------------------------------
--  DDL for Table F_SALES
--------------------------------------------------------

  CREATE TABLE "F_SALES" 
   (	"QUANTITY" NUMBER, 
	"TOTAL_PRICE" NUMBER, 
	"UNIT_PRICE" NUMBER
   ) ;
--------------------------------------------------------
--  DDL for Table L0_CUSTOMER
--------------------------------------------------------

  CREATE TABLE "L0_CUSTOMER" 
   (	"CUSTOMER_ID" NUMBER(8,0), 
	"ADDRESS_ID" NUMBER(8,0), 
	"FIRST_NAME" VARCHAR2(50), 
	"SURNAME" VARCHAR2(50)
   ) ;
--------------------------------------------------------
--  DDL for Table L0_CUSTOMER_ADDRESS
--------------------------------------------------------

  CREATE TABLE "L0_CUSTOMER_ADDRESS" 
   (	"ADDRESS_ID" NUMBER(8,0), 
	"CITY" VARCHAR2(50), 
	"STREET" VARCHAR2(50), 
	"HOUSE_NR" NUMBER(4,0), 
	"ZIP" NUMBER(5,0)
   ) ;
--------------------------------------------------------
--  DDL for Table L0_INVOICE
--------------------------------------------------------

  CREATE TABLE "L0_INVOICE" 
   (	"INVOICE_ID" NUMBER(8,0), 
	"CUSTOMER_ID" NUMBER(8,0), 
	"DUE_DATE" DATE, 
	"PAYMENT_DATE" DATE, 
	"PAYMENT_FLAG" CHAR(1)
   ) ;
--------------------------------------------------------
--  DDL for Table L0_INVOICE_ITEM
--------------------------------------------------------

  CREATE TABLE "L0_INVOICE_ITEM" 
   (	"INVOICE_ITEM_ID" NUMBER(8,0), 
	"PRODUCT_ID" NUMBER(8,0), 
	"INVOICE_ID" NUMBER(8,0), 
	"AMOUNT" NUMBER(4,0)
   ) ;
--------------------------------------------------------
--  DDL for Table L0_PRODUCT
--------------------------------------------------------

  CREATE TABLE "L0_PRODUCT" 
   (	"PRODUCT_ID" NUMBER(8,0), 
	"PRODUCT_GROUP_ID" NUMBER(8,0), 
	"PRODUCT_NAME" VARCHAR2(50), 
	"UNIT_PRICE" NUMBER(8,2)
   ) ;
--------------------------------------------------------
--  DDL for Table L0_PRODUCT_GROUP
--------------------------------------------------------

  CREATE TABLE "L0_PRODUCT_GROUP" 
   (	"PRODUCT_GROUP_ID" NUMBER(8,0), 
	"PRODUCT_GROUP_CODENAME" VARCHAR2(10), 
	"PRODUCT_GROUP_NAME" VARCHAR2(50)
   ) ;
--------------------------------------------------------
--  Constraints for Table D_CUSTOMER
--------------------------------------------------------

  ALTER TABLE "D_CUSTOMER" MODIFY ("CUST_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table L0_PRODUCT_GROUP
--------------------------------------------------------

  ALTER TABLE "L0_PRODUCT_GROUP" ADD CONSTRAINT "L0_PRODUCT_GROUP_PK" PRIMARY KEY ("PRODUCT_GROUP_ID") ENABLE;
 
  ALTER TABLE "L0_PRODUCT_GROUP" MODIFY ("PRODUCT_GROUP_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table D_INVOICE
--------------------------------------------------------

  ALTER TABLE "D_INVOICE" ADD PRIMARY KEY ("INV_ID") ENABLE;
--------------------------------------------------------
--  Constraints for Table D_TIME
--------------------------------------------------------

  ALTER TABLE "D_TIME" MODIFY ("TIME_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table D_PRODUCT
--------------------------------------------------------

  ALTER TABLE "D_PRODUCT" ADD PRIMARY KEY ("PROD_ID") ENABLE;
--------------------------------------------------------
--  Constraints for Table L0_CUSTOMER_ADDRESS
--------------------------------------------------------

  ALTER TABLE "L0_CUSTOMER_ADDRESS" ADD CONSTRAINT "L0_CUSTOMER_ADDRESS_PK" PRIMARY KEY ("ADDRESS_ID") ENABLE;
 
  ALTER TABLE "L0_CUSTOMER_ADDRESS" MODIFY ("ADDRESS_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table L0_CUSTOMER
--------------------------------------------------------

  ALTER TABLE "L0_CUSTOMER" ADD CONSTRAINT "L0_CUSTOMER_PK" PRIMARY KEY ("CUSTOMER_ID") ENABLE;
 
  ALTER TABLE "L0_CUSTOMER" MODIFY ("CUSTOMER_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table L0_PRODUCT
--------------------------------------------------------

  ALTER TABLE "L0_PRODUCT" ADD CONSTRAINT "L0_PRODUCT_PK" PRIMARY KEY ("PRODUCT_ID") ENABLE;
 
  ALTER TABLE "L0_PRODUCT" MODIFY ("PRODUCT_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table L0_INVOICE_ITEM
--------------------------------------------------------

  ALTER TABLE "L0_INVOICE_ITEM" ADD CONSTRAINT "L0_INVOICE_ITEM_PK" PRIMARY KEY ("INVOICE_ITEM_ID") ENABLE;
 
  ALTER TABLE "L0_INVOICE_ITEM" MODIFY ("INVOICE_ITEM_ID" NOT NULL ENABLE);

--------------------------------------------------------
--  Constraints for Table L0_INVOICE
--------------------------------------------------------

  ALTER TABLE "L0_INVOICE" ADD CONSTRAINT "L0_INVOICE_PK" PRIMARY KEY ("INVOICE_ID") ENABLE;
 
  ALTER TABLE "L0_INVOICE" MODIFY ("INVOICE_ID" NOT NULL ENABLE);





--------------------------------------------------------
--  Ref Constraints for Table L0_CUSTOMER
--------------------------------------------------------

  ALTER TABLE "L0_CUSTOMER" ADD CONSTRAINT "FK_ADDRESS_ID" FOREIGN KEY ("ADDRESS_ID")
	  REFERENCES "L0_CUSTOMER_ADDRESS" ("ADDRESS_ID") ENABLE;

--------------------------------------------------------
--  Ref Constraints for Table L0_INVOICE
--------------------------------------------------------

  ALTER TABLE "L0_INVOICE" ADD CONSTRAINT "FK_L0_CUSTOMER_ID" FOREIGN KEY ("CUSTOMER_ID")
	  REFERENCES "L0_CUSTOMER" ("CUSTOMER_ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table L0_INVOICE_ITEM
--------------------------------------------------------

  ALTER TABLE "L0_INVOICE_ITEM" ADD CONSTRAINT "FK_L0_INVOICE_ID" FOREIGN KEY ("INVOICE_ID")
	  REFERENCES "L0_INVOICE" ("INVOICE_ID") ENABLE;
 
  ALTER TABLE "L0_INVOICE_ITEM" ADD CONSTRAINT "FK_L0_PRODUCT_ID" FOREIGN KEY ("PRODUCT_ID")
	  REFERENCES "L0_PRODUCT" ("PRODUCT_ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table L0_PRODUCT
--------------------------------------------------------

  ALTER TABLE "L0_PRODUCT" ADD CONSTRAINT "FK_L0_PRODUCT_GROUP_ID" FOREIGN KEY ("PRODUCT_GROUP_ID")
	  REFERENCES "L0_PRODUCT_GROUP" ("PRODUCT_GROUP_ID") ENABLE;

