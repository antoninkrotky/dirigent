--------------------------------------------------------
--  File created - Tuesday-October-19-2010   
--------------------------------------------------------

---------------------------------------------------
--   DATA FOR TABLE D_CUSTOMER
--   FILTER = none used
---------------------------------------------------
REM INSERTING into D_CUSTOMER

---------------------------------------------------
--   END DATA FOR TABLE D_CUSTOMER
---------------------------------------------------


---------------------------------------------------
--   DATA FOR TABLE D_INVOICE
--   FILTER = none used
---------------------------------------------------
REM INSERTING into D_INVOICE

---------------------------------------------------
--   END DATA FOR TABLE D_INVOICE
---------------------------------------------------


---------------------------------------------------
--   DATA FOR TABLE D_PRODUCT
--   FILTER = none used
---------------------------------------------------
REM INSERTING into D_PRODUCT

---------------------------------------------------
--   END DATA FOR TABLE D_PRODUCT
---------------------------------------------------


---------------------------------------------------
--   DATA FOR TABLE D_TIME
--   FILTER = none used
---------------------------------------------------
REM INSERTING into D_TIME

---------------------------------------------------
--   END DATA FOR TABLE D_TIME
---------------------------------------------------


---------------------------------------------------
--   DATA FOR TABLE F_SALES
--   FILTER = none used
---------------------------------------------------
REM INSERTING into F_SALES

---------------------------------------------------
--   END DATA FOR TABLE F_SALES
---------------------------------------------------


---------------------------------------------------
--   DATA FOR TABLE L0_CUSTOMER
--   FILTER = none used
---------------------------------------------------
REM INSERTING into L0_CUSTOMER
Insert into L0_CUSTOMER (CUSTOMER_ID,ADDRESS_ID,FIRST_NAME,SURNAME) values (1,1,'Pepa','Novak');
Insert into L0_CUSTOMER (CUSTOMER_ID,ADDRESS_ID,FIRST_NAME,SURNAME) values (2,6,'ANDREA','Novomanzelova');
Insert into L0_CUSTOMER (CUSTOMER_ID,ADDRESS_ID,FIRST_NAME,SURNAME) values (3,3,'ONDREJ','STARY');
Insert into L0_CUSTOMER (CUSTOMER_ID,ADDRESS_ID,FIRST_NAME,SURNAME) values (4,4,'boris','ostry');
Insert into L0_CUSTOMER (CUSTOMER_ID,ADDRESS_ID,FIRST_NAME,SURNAME) values (5,5,'Emanuel','Modry');
Insert into L0_CUSTOMER (CUSTOMER_ID,ADDRESS_ID,FIRST_NAME,SURNAME) values (6,6,'Petr','Novomanzel');

---------------------------------------------------
--   END DATA FOR TABLE L0_CUSTOMER
---------------------------------------------------


---------------------------------------------------
--   DATA FOR TABLE L0_CUSTOMER_ADDRESS
--   FILTER = none used
---------------------------------------------------
REM INSERTING into L0_CUSTOMER_ADDRESS
Insert into L0_CUSTOMER_ADDRESS (ADDRESS_ID,CITY,STREET,HOUSE_NR,ZIP) values (1,'PRAHA','TECHNICKA',3,16000);
Insert into L0_CUSTOMER_ADDRESS (ADDRESS_ID,CITY,STREET,HOUSE_NR,ZIP) values (3,'Brno','Freyova',103,24890);
Insert into L0_CUSTOMER_ADDRESS (ADDRESS_ID,CITY,STREET,HOUSE_NR,ZIP) values (4,'ostrava','stodolni',666,45689);
Insert into L0_CUSTOMER_ADDRESS (ADDRESS_ID,CITY,STREET,HOUSE_NR,ZIP) values (5,'Praha','Vaclavske nam',27,11000);
Insert into L0_CUSTOMER_ADDRESS (ADDRESS_ID,CITY,STREET,HOUSE_NR,ZIP) values (6,'praha','roseveltova',54,16400);

---------------------------------------------------
--   END DATA FOR TABLE L0_CUSTOMER_ADDRESS
---------------------------------------------------


---------------------------------------------------
--   DATA FOR TABLE L0_INVOICE
--   FILTER = none used
---------------------------------------------------
REM INSERTING into L0_INVOICE
Insert into L0_INVOICE (INVOICE_ID,CUSTOMER_ID,DUE_DATE,PAYMENT_DATE,PAYMENT_FLAG) values (1,1,to_timestamp('30-OCT-10 12.00.00.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'),to_timestamp('01-JAN-00 12.00.00.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'),'0');
Insert into L0_INVOICE (INVOICE_ID,CUSTOMER_ID,DUE_DATE,PAYMENT_DATE,PAYMENT_FLAG) values (2,2,to_timestamp('30-OCT-10 12.00.00.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'),to_timestamp('20-OCT-10 12.00.00.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'),'1');
Insert into L0_INVOICE (INVOICE_ID,CUSTOMER_ID,DUE_DATE,PAYMENT_DATE,PAYMENT_FLAG) values (3,3,to_timestamp('30-OCT-10 12.00.00.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'),to_timestamp('01-JAN-00 12.00.00.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'),'0');
Insert into L0_INVOICE (INVOICE_ID,CUSTOMER_ID,DUE_DATE,PAYMENT_DATE,PAYMENT_FLAG) values (4,4,to_timestamp('30-OCT-10 12.00.00.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'),to_timestamp('01-JAN-10 12.00.00.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'),'1');
Insert into L0_INVOICE (INVOICE_ID,CUSTOMER_ID,DUE_DATE,PAYMENT_DATE,PAYMENT_FLAG) values (5,5,to_timestamp('30-NOV-10 12.00.00.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'),to_timestamp('01-JAN-00 12.00.00.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'),'0');
Insert into L0_INVOICE (INVOICE_ID,CUSTOMER_ID,DUE_DATE,PAYMENT_DATE,PAYMENT_FLAG) values (6,5,to_timestamp('30-OCT-10 12.00.00.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'),to_timestamp('01-JAN-00 12.00.00.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'),'0');

---------------------------------------------------
--   END DATA FOR TABLE L0_INVOICE
---------------------------------------------------


---------------------------------------------------
--   DATA FOR TABLE L0_INVOICE_ITEM
--   FILTER = none used
---------------------------------------------------
REM INSERTING into L0_INVOICE_ITEM
Insert into L0_INVOICE_ITEM (INVOICE_ITEM_ID,PRODUCT_ID,INVOICE_ID,AMOUNT) values (1,1,1,1);
Insert into L0_INVOICE_ITEM (INVOICE_ITEM_ID,PRODUCT_ID,INVOICE_ID,AMOUNT) values (3,3,3,3);
Insert into L0_INVOICE_ITEM (INVOICE_ITEM_ID,PRODUCT_ID,INVOICE_ID,AMOUNT) values (4,4,4,1);
Insert into L0_INVOICE_ITEM (INVOICE_ITEM_ID,PRODUCT_ID,INVOICE_ID,AMOUNT) values (5,5,5,500);
Insert into L0_INVOICE_ITEM (INVOICE_ITEM_ID,PRODUCT_ID,INVOICE_ID,AMOUNT) values (6,4,5,22);

---------------------------------------------------
--   END DATA FOR TABLE L0_INVOICE_ITEM
---------------------------------------------------


---------------------------------------------------
--   DATA FOR TABLE L0_PRODUCT
--   FILTER = none used
---------------------------------------------------
REM INSERTING into L0_PRODUCT
Insert into L0_PRODUCT (PRODUCT_ID,PRODUCT_GROUP_ID,PRODUCT_NAME,UNIT_PRICE) values (1,1,'Skoda Octavia',500000);
Insert into L0_PRODUCT (PRODUCT_ID,PRODUCT_GROUP_ID,PRODUCT_NAME,UNIT_PRICE) values (2,2,'Soustruh B238',90000);
Insert into L0_PRODUCT (PRODUCT_ID,PRODUCT_GROUP_ID,PRODUCT_NAME,UNIT_PRICE) values (3,3,'Plysovy medvidek',200);
Insert into L0_PRODUCT (PRODUCT_ID,PRODUCT_GROUP_ID,PRODUCT_NAME,UNIT_PRICE) values (4,4,'AK-47(original)',15000);
Insert into L0_PRODUCT (PRODUCT_ID,PRODUCT_GROUP_ID,PRODUCT_NAME,UNIT_PRICE) values (5,5,'Semtex C3 1kg',3000);
Insert into L0_PRODUCT (PRODUCT_ID,PRODUCT_GROUP_ID,PRODUCT_NAME,UNIT_PRICE) values (6,5,'c4 1kg',5000);
Insert into L0_PRODUCT (PRODUCT_ID,PRODUCT_GROUP_ID,PRODUCT_NAME,UNIT_PRICE) values (7,3,'Panenka Barbie',758);

---------------------------------------------------
--   END DATA FOR TABLE L0_PRODUCT
---------------------------------------------------


---------------------------------------------------
--   DATA FOR TABLE L0_PRODUCT_GROUP
--   FILTER = none used
---------------------------------------------------
REM INSERTING into L0_PRODUCT_GROUP
Insert into L0_PRODUCT_GROUP (PRODUCT_GROUP_ID,PRODUCT_GROUP_CODENAME,PRODUCT_GROUP_NAME) values (1,'AUTA','AUTOMOBILY');
Insert into L0_PRODUCT_GROUP (PRODUCT_GROUP_ID,PRODUCT_GROUP_CODENAME,PRODUCT_GROUP_NAME) values (2,'stroje','stroje');
Insert into L0_PRODUCT_GROUP (PRODUCT_GROUP_ID,PRODUCT_GROUP_CODENAME,PRODUCT_GROUP_NAME) values (3,'Hracky','Hracky');
Insert into L0_PRODUCT_GROUP (PRODUCT_GROUP_ID,PRODUCT_GROUP_CODENAME,PRODUCT_GROUP_NAME) values (4,'Zbrane','Zbrane');
Insert into L0_PRODUCT_GROUP (PRODUCT_GROUP_ID,PRODUCT_GROUP_CODENAME,PRODUCT_GROUP_NAME) values (5,'Trhaviny','SEMTEX a jemu podobne');
Insert into L0_PRODUCT_GROUP (PRODUCT_GROUP_ID,PRODUCT_GROUP_CODENAME,PRODUCT_GROUP_NAME) values (6,'Letadla','Letecke stroje a plavidla');

---------------------------------------------------
--   END DATA FOR TABLE L0_PRODUCT_GROUP
---------------------------------------------------

