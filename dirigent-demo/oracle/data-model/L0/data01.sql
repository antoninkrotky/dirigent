---------------------------------------------------
--   DATA FOR TABLE L0_CUSTOMER_ADDRESS
--   FILTER = none used
---------------------------------------------------
Insert into L0_CUSTOMER_ADDRESS (ADDRESS_ID,CITY,STREET,HOUSE_NR,ZIP) values (1,'PRAHA','TECHNICKA',3,16000);
Insert into L0_CUSTOMER_ADDRESS (ADDRESS_ID,CITY,STREET,HOUSE_NR,ZIP) values (3,'BRNO','FREYOVA',103,24890);
Insert into L0_CUSTOMER_ADDRESS (ADDRESS_ID,CITY,STREET,HOUSE_NR,ZIP) values (2,'PLZEN','PIVOVARSKA',1,38954);
Insert into L0_CUSTOMER_ADDRESS (ADDRESS_ID,CITY,STREET,HOUSE_NR,ZIP) values (4,'ostrava','stodolni',666,45689);
Insert into L0_CUSTOMER_ADDRESS (ADDRESS_ID,CITY,STREET,HOUSE_NR,ZIP) values (5,'Praha','Vaclavske nam',27,11000);

---------------------------------------------------
--   END DATA FOR TABLE L0_CUSTOMER_ADDRESS
---------------------------------------------------


---------------------------------------------------
--   DATA FOR TABLE L0_CUSTOMER
--   FILTER = none used
---------------------------------------------------
Insert into L0_CUSTOMER (CUSTOMER_ID,ADDRESS_ID,FIRST_NAME,SURNAME) values (1,1,'Pepa','Novak');
Insert into L0_CUSTOMER (CUSTOMER_ID,ADDRESS_ID,FIRST_NAME,SURNAME) values (2,2,'Andrea','VELKA');
Insert into L0_CUSTOMER (CUSTOMER_ID,ADDRESS_ID,FIRST_NAME,SURNAME) values (3,3,'ONDREJ','STARY');
Insert into L0_CUSTOMER (CUSTOMER_ID,ADDRESS_ID,FIRST_NAME,SURNAME) values (4,4,'BORIS','OSTRY');
Insert into L0_CUSTOMER (CUSTOMER_ID,ADDRESS_ID,FIRST_NAME,SURNAME) values (5,5,'Emanuel','Modry');

---------------------------------------------------
--   END DATA FOR TABLE L0_CUSTOMER
---------------------------------------------------


---------------------------------------------------
--   DATA FOR TABLE L0_INVOICE
--   FILTER = none used
---------------------------------------------------
Insert into L0_INVOICE (INVOICE_ID,CUSTOMER_ID,DUE_DATE,PAYMENT_DATE,PAYMENT_FLAG,INVOICE_DATE) values (1,1,to_timestamp('30-OCT-10 12.00.00.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'),to_timestamp('01-JAN-00 12.00.00.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'),'0',to_timestamp('01-JAN-00 12.00.00.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'));
Insert into L0_INVOICE (INVOICE_ID,CUSTOMER_ID,DUE_DATE,PAYMENT_DATE,PAYMENT_FLAG,INVOICE_DATE) values (2,2,to_timestamp('30-OCT-10 12.00.00.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'),to_timestamp('20-OCT-10 12.00.00.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'),'1',to_timestamp('01-JAN-00 12.00.00.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'));
Insert into L0_INVOICE (INVOICE_ID,CUSTOMER_ID,DUE_DATE,PAYMENT_DATE,PAYMENT_FLAG,INVOICE_DATE) values (3,3,to_timestamp('30-OCT-10 12.00.00.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'),to_timestamp('01-JAN-00 12.00.00.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'),'0',to_timestamp('01-JAN-00 12.00.00.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'));
Insert into L0_INVOICE (INVOICE_ID,CUSTOMER_ID,DUE_DATE,PAYMENT_DATE,PAYMENT_FLAG,INVOICE_DATE) values (4,4,to_timestamp('30-OCT-10 12.00.00.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'),to_timestamp('01-JAN-10 12.00.00.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'),'1',to_timestamp('01-JAN-00 12.00.00.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'));
Insert into L0_INVOICE (INVOICE_ID,CUSTOMER_ID,DUE_DATE,PAYMENT_DATE,PAYMENT_FLAG,INVOICE_DATE) values (5,5,to_timestamp('30-NOV-10 12.00.00.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'),to_timestamp('01-JAN-00 12.00.00.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'),'0',to_timestamp('01-JAN-00 12.00.00.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'));

---------------------------------------------------
--   END DATA FOR TABLE L0_INVOICE
---------------------------------------------------


---------------------------------------------------
--   DATA FOR TABLE L0_PRODUCT_GROUP
--   FILTER = none used
---------------------------------------------------
Insert into L0_PRODUCT_GROUP (PRODUCT_GROUP_ID,PRODUCT_GROUP_CODE,PRODUCT_GROUP_NAME) values (1,'AUTA','AUTOMOBILY');
Insert into L0_PRODUCT_GROUP (PRODUCT_GROUP_ID,PRODUCT_GROUP_CODE,PRODUCT_GROUP_NAME) values (2,'stroje','stroje');
Insert into L0_PRODUCT_GROUP (PRODUCT_GROUP_ID,PRODUCT_GROUP_CODE,PRODUCT_GROUP_NAME) values (3,'Hracky','Hracky');
Insert into L0_PRODUCT_GROUP (PRODUCT_GROUP_ID,PRODUCT_GROUP_CODE,PRODUCT_GROUP_NAME) values (4,'Zbrane','Zbrane a strelivo');
Insert into L0_PRODUCT_GROUP (PRODUCT_GROUP_ID,PRODUCT_GROUP_CODE,PRODUCT_GROUP_NAME) values (5,'Trhaviny','SEMTEX a jemu podobne');

---------------------------------------------------
--   END DATA FOR TABLE L0_PRODUCT_GROUP
---------------------------------------------------

---------------------------------------------------
--   DATA FOR TABLE L0_PRODUCT
--   FILTER = none used
---------------------------------------------------
Insert into L0_PRODUCT (PRODUCT_ID,PRODUCT_GROUP_ID,PRODUCT_NAME,UNIT_PRICE) values (1,1,'Skoda Octavia',500000);
Insert into L0_PRODUCT (PRODUCT_ID,PRODUCT_GROUP_ID,PRODUCT_NAME,UNIT_PRICE) values (2,2,'Soustruh B238',90000);
Insert into L0_PRODUCT (PRODUCT_ID,PRODUCT_GROUP_ID,PRODUCT_NAME,UNIT_PRICE) values (3,3,'Plysovy medvidek',200);
Insert into L0_PRODUCT (PRODUCT_ID,PRODUCT_GROUP_ID,PRODUCT_NAME,UNIT_PRICE) values (4,4,'AK-47',15000);
Insert into L0_PRODUCT (PRODUCT_ID,PRODUCT_GROUP_ID,PRODUCT_NAME,UNIT_PRICE) values (5,5,'Semtex C3 1kg',3000);

---------------------------------------------------
--   END DATA FOR TABLE L0_PRODUCT
---------------------------------------------------

---------------------------------------------------
--   DATA FOR TABLE L0_INVOICE_ITEM
--   FILTER = none used
---------------------------------------------------
Insert into L0_INVOICE_ITEM (INVOICE_ITEM_ID,PRODUCT_ID,INVOICE_ID,AMOUNT,UNIT_PRICE) values (1,1,1,1,1);
Insert into L0_INVOICE_ITEM (INVOICE_ITEM_ID,PRODUCT_ID,INVOICE_ID,AMOUNT,UNIT_PRICE) values (2,2,2,2,2);
Insert into L0_INVOICE_ITEM (INVOICE_ITEM_ID,PRODUCT_ID,INVOICE_ID,AMOUNT,UNIT_PRICE) values (3,3,3,3,3);
Insert into L0_INVOICE_ITEM (INVOICE_ITEM_ID,PRODUCT_ID,INVOICE_ID,AMOUNT,UNIT_PRICE) values (4,4,4,1,1);
Insert into L0_INVOICE_ITEM (INVOICE_ITEM_ID,PRODUCT_ID,INVOICE_ID,AMOUNT,UNIT_PRICE) values (5,5,5,100,100);

---------------------------------------------------
--   END DATA FOR TABLE L0_INVOICE_ITEM
---------------------------------------------------