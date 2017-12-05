/**************************************************************************
C16315253
William Carey
DT228
**************************************************************************/

--DROPPING PERVISIONS VERSION
DROP TABLE SpecProd;
DROP TABLE Specifications;
DROP TABLE ProdType;
DROP TABLE Designer;
DROP TABLE Product;
DROP TABLE Clients;

--Creating tables for input
CREATE TABLE Clients
(
    clientId NUMBER(6) NOT NULL,
    fullname VARCHAR2(50) NOT NULL,
    emailAdr VARCHAR2(30) NOT NULL,
    --ADD CONSTRAINTS LATER
    --ADD PRIMARY KEYS LATER ETC
    CONSTRAINT clientId_pk PRIMARY KEY (clientId),
    CONSTRAINT email_ck CHECK (emailAdr LIKE '%@%.com')
);

CREATE TABLE Designer
(
    designerID NUMBER(6) NOT NULL,
    dName VARCHAR2(50) NOT NULL,
    dEmailadr VARCHAR2(30) NOT NULL,
    dRateOfPay NUMBER(4,2) NOT NULL,
    --ADD CONSTRANTS LATER
    CONSTRAINT designerID_pk PRIMARY KEY (designerID),
    CONSTRAINT email_ck2 CHECK (dEmailadr LIKE '%@%.com%')
);

CREATE TABLE ProdType
(
    prodCat CHAR(1) NOT NULL,
    catDes VARCHAR2(50) NOT NULL,
    --ADD CONSTRAINTS LATER
    CONSTRAINT prodCat_pk PRIMARY KEY (prodCat)
);

CREATE TABLE Product
(
      prodId NUMBER(6) NOT NULL,
      --FORIEGN KEY NEEDED FURTHER DOWN
      prodCat2 CHAR(1) NOT NULL,
      prodDes VARCHAR2(50) NOT NULL,
      prodPrice NUMBER(4,2) NOT NULL,
      prodQty NUMBER(3) NOT NULL,
      --ADD CONSTRAINTS LATER
      CONSTRAINT prodId_pk PRIMARY KEY (prodId),
      CONSTRAINT prodCat_fk FOREIGN KEY (prodCat2)
      REFERENCES ProdType(prodCat)
);

CREATE TABLE Specifications
(
    specId NUMBER(6) NOT NULL,
    specDate DATE NOT NULL,
    --FOREIGN KEYS TO BE ADDED LATER
     clientId NUMBER(6) NOT NULL,
     designerID NUMBER(6) NOT NULL,
     specDes VARCHAR2(50) NOT NULL,
     specCommission NUMBER(7,2) NOT NULL,
     designHrsWorked NUMBER(3) NOT NULL,
     --CONSTRAINTS TO BE ADDED
     CONSTRAINT specId_pk PRIMARY KEY (specId),
     CONSTRAINT clientId_fk FOREIGN KEY (clientId)
     REFERENCES Clients(clientId),
     CONSTRAINT designerID_fk FOREIGN KEY (designerID)
     REFERENCES Designer(designerID)
);

CREATE TABLE SpecProd
(
    --foreign keys to be added
     specId2 NUMBER(6) NOT NULL,
     prodId NUMBER(6) NOT NULL,
     prodCat2 CHAR(1) NOT NULL,
     qtyUsed NUMBER(3) NOT NULL,
     --CONSTRAINTS TO BE ADDED LATER
     CONSTRAINT specId_fk FOREIGN KEY (specId2)
     REFERENCES Specifications(specId),
     CONSTRAINT prodId_fk2 FOREIGN KEY (prodId)
     REFERENCES Product(prodId),
     CONSTRAINT prodCat_fk2 FOREIGN KEY (prodCat2)
      REFERENCES ProdType(prodCat)
);
--inserting values into the programme now
--client side first
INSERT INTO Clients (clientId,fullname,emailAdr) 
VALUES (101,'J.J. Abrams','jjab@sw.com');
INSERT INTO Clients (clientId,fullname,emailAdr)
VALUES (201,'Lawrence Kasdan','lkas@sw.com');
INSERT INTO Clients (clientId,fullname,emailAdr) 
VALUES (301,'Daisy Ridley','drid@sw.com');
INSERT INTO Clients (clientId,fullname,emailAdr) 
VALUES (401,'John Boyega','jboy@sw.com');
--designer side
INSERT INTO Designer (designerID,dName,dEmailadr,dRateOfPay) 
VALUES (101,'Kelly Hoppen','khop@gmail.com.uk',65.00);
INSERT INTO Designer (designerID,dName,dEmailadr,dRateOfPay) 
VALUES (201,'Philippe Starck','pstark@stark.com',72.50);
INSERT INTO Designer (designerID,dName,dEmailadr,dRateOfPay) 
VALUES (301,'Victoria Hagan','vichag@gmail.com',75.00);
INSERT INTO Designer (designerID,dName,dEmailadr,dRateOfPay) 
VALUES (401,'Marmol Radziner','marmrad@gmail.com',45.50);

--prodtype values 
INSERT INTO ProdType (prodCat,catDes) VALUES ('G','Garden Lighting');
INSERT INTO ProdType (prodCat,catDes) VALUES ('L','Lamps and Bulbs');
INSERT INTO ProdType (prodCat,catDes) VALUES ('C','Cables');
INSERT INTO ProdType (prodCat,catDes) VALUES ('X','Christmas');
INSERT INTO ProdType (prodCat,catDes) VALUES ('S','Shades');

--product values
INSERT INTO Product (prodId,prodCat2,prodDes,prodPrice,prodQty)
VALUES (101,'G','Outdoor Wall Light',40.00,26);
INSERT INTO Product (prodId,prodCat2,prodDes,prodPrice,prodQty)
VALUES (102,'G','Patio Lights',41.00,27);
INSERT INTO Product (prodId,prodCat2,prodDes,prodPrice,prodQty)
VALUES (103,'L','E14 Engery Saving Bulb',6.00,28);
INSERT INTO Product (prodId,prodCat2,prodDes,prodPrice,prodQty)
VALUES (104,'L','E27 Led Bulb',9.00,30);
INSERT INTO Product (prodId,prodCat2,prodDes,prodPrice,prodQty)
VALUES (105,'C','2-Core Black Braided Flexible Rubber Cable',10.00,50);
INSERT INTO Product (prodId,prodCat2,prodDes,prodPrice,prodQty)
VALUES (106,'C','Southwire 250-Ft 2-Conductor Landscape Lighting',11.00,78);
INSERT INTO Product (prodId,prodCat2,prodDes,prodPrice,prodQty)
VALUES (107,'X','LED string lights German Christmas 10-light',15.50,12);
INSERT INTO Product (prodId,prodCat2,prodDes,prodPrice,prodQty)
VALUES (108,'X','LED heart string lights',20.00,12);
INSERT INTO Product (prodId,prodCat2,prodDes,prodPrice,prodQty)
VALUES (109,'S','Fabric Cylinder Shade Red',30.00,100);
--Specifications
INSERT INTO Specifications 
(specId,specDate,clientId,designerID,specDes,specCommission,designHrsWorked)
VALUES 
(101,TO_DATE('12/06/17', 'DD/MM/YYYY'),101,101,'Full house',10000.00,10);
INSERT INTO Specifications 
(specId,specDate,clientId,designerID,specDes,specCommission,designHrsWorked)
VALUES 
(102,TO_DATE('14/06/17', 'DD/MM/YYYY'),101,101,'Garden Patio',12000.00,20);
INSERT INTO Specifications 
(specId,specDate,clientId,designerID,specDes,specCommission,designHrsWorked)
VALUES 
(103,TO_DATE('15/08/17', 'DD/MM/YYYY'),201,301,'Summerhouse',8000.00,5);
INSERT INTO Specifications 
(specId,specDate,clientId,designerID,specDes,specCommission,designHrsWorked)
VALUES 
(104,TO_DATE('10/09/17', 'DD/MM/YYYY'),301,201,'Christmas decorations',5000.00,5);

--SpecProd
INSERT INTO SpecProd (specId2,prodId,prodCat2,qtyUsed) 
VALUES (101,103,'L',20);
INSERT INTO SpecProd (specId2,prodId,prodCat2,qtyUsed) 
VALUES (101,104,'L',20);
INSERT INTO SpecProd (specId2,prodId,prodCat2,qtyUsed) 
VALUES (101,105,'C',20);
INSERT INTO SpecProd (specId2,prodId,prodCat2,qtyUsed) 
VALUES (102,103,'G',20);
INSERT INTO SpecProd (specId2,prodId,prodCat2,qtyUsed) 
VALUES (102,104,'G',20);
INSERT INTO SpecProd (specId2,prodId,prodCat2,qtyUsed) 
VALUES (103,101,'C',20);
INSERT INTO SpecProd (specId2,prodId,prodCat2,qtyUsed) 
VALUES (103,101,'C',20);
INSERT INTO SpecProd (specId2,prodId,prodCat2,qtyUsed) 
VALUES (104,107,'X',20);

COMMIT;

--Each comment section refers to the question in the document

/**************************************************************************
1.	A listing for all designers of the specifications they have worked on 
including in the output 
their name, 
email address 
and a 10 character description of the specification in uppercase 

sorted in descending order of designer id and then specification description
**************************************************************************/
SELECT dName"Designer Name",dEmailadr"Designer Email Address",
UPPER(SUBSTR(specDes,0,10))"Ten Character Description"
FROM Designer JOIN SPECIFICATIONS USING (designerID)
ORDER BY designerID DESC, specDes; 

/**************************************************************************
2.	A listing of all products. 
Including one column which combines the product ID and the product category 
code; 
the product category description (in uppercase); catDes
product description (in uppercase); 
product price (preceded by the Euro symbol).

The listing should be sorted by 
product category description in ascending order catDes
and descending order of product price within each category.prodPrice
**************************************************************************/
SELECT prodCat2 || prodId "Unique Id", UPPER(prodDes)"Product Description",
catDes"Catogory Description",TO_CHAR(prodPrice,'U99.99')"Product Price" 
FROM Product JOIN  ProdType ON (ProdType.prodCat = Product.prodCat2)
ORDER BY catDes ASC, prodPrice DESC; 

/************************************************************************
3.	A listing of all specifications showing the 
specification ID, specId
client ID, clientId
client name, 
specification description, specDes
specification date (formatted as dd/mm/yyyy) and specDate
specification commission(including the Euro symbol) specCommission

sorted in descending order of commission. specCommission
*************************************************************************/

SELECT specId "Specification Id", clientId "Client Id", fullname "Client Name", 
specDes "Specification Description",
TO_CHAR(specDate,'DD/MM/YYYY') "Specification date",
TO_CHAR(specCommission,'U99999.99') "Specification commission"
FROM Specifications JOIN Clients USING (clientId)
ORDER BY specCommission DESC;

/*************************************************************************
4.	A listing of all specifications showing the 
specification ID, 
client ID, 
client name, 
designer ID, 
designer name, 
specification description, 
specification date (formatted as dd/mm/yyyy) and 
specification commission(including the Euro symbol)

sorted in descending order of commission. 

the following headers should be used 
SPECIFICATION ID
CLIENT NAME 
DESIGNER NAME 
DESCRIPTION 
COMMISSION DATE 
COMMISSION AMT
*************************************************************************/

SELECT specId "SPECIFICATION ID", 
TO_CHAR(specDate,'DD/MM/YYYY') "COMMISSION DATE",
TO_CHAR(specCommission,'U99999.99') "COMMISSION AMT",
clientId "CLIENT ID", fullname "CLIENT NAME",
designerID "DESIGNER ID",dName "DESIGNER NAME"
FROM Specifications 
JOIN Clients USING (clientId)
JOIN Designer USING (designerID)
ORDER BY specCommission DESC;
/*************************************************************************
5.A listing for each product used as part of a specification 
the specification ID, 
specification description, 
the product name,  
product price, 
number of each product used and 
a total price per product per specification (price x quantity used).
*************************************************************************/
SELECT specId "SPECIFICATION ID",specDes "SPECIFICATION DESCRIPTION",
prodDes "PRODUCT DESCRIPTION",prodPrice "PRODUCT PRICE",
qtyUsed "PRODUCT USED",prodPrice*qtyUsed  "TOTAL COST"
FROM SpecProd
JOIN Specifications ON (Specifications.specId = SpecProd.specId2)
JOIN Product USING (prodId);

/********************************************************************
6.	A listing for each specification including the 
specification ID, specId
specification description and specDes
total cost of the specification 
(commission + sum of(qty x product) price for all products used).
specCommission+(*prodPrice)
Hint: Involves a group 

********************************************************************/
SELECT specId "SPECIFICATION ID",specDes "SPECIFICATION DESCRIPTION",
TO_CHAR(specCommission+SUM(prodPrice*qtyUsed),'U9999999.99') "TOTAL COMMISSION PRICE"
FROM SpecProd
JOIN Specifications ON (Specifications.specId = SpecProd.specId2)
JOIN Product USING (prodId)
GROUP BY specId,specDes,specCommission;

/********************************************************************
7.	A listing showing details required for report 6
but including an additional column in the output which categorises 
the specification as ‘High Value’ if the total cost is > 10,000, 
‘Medium Cost’ if the total cost is between 8,000 and 10,000 and 
‘Low Cost’ otherwise.
Hint: Involves a selection

********************************************************************/
SELECT specId "SPECIFICATION ID",specDes "SPECIFICATION DESCRIPTION",
TO_CHAR(specCommission+SUM(prodPrice*qtyUsed),'U9999999.99') "TOTAL COMMISSION PRICE",
CASE WHEN specCommission+SUM(prodPrice*qtyUsed) > 10000 THEN 'HIGH'
WHEN specCommission+SUM(prodPrice*qtyUsed) < 8000 THEN 'LOW'
ELSE 'MEDIUM'
END AS Range
FROM SpecProd
JOIN Specifications ON (Specifications.specId = SpecProd.specId2)
JOIN Product USING (prodId)
GROUP BY specId,specDes,specCommission;

/********************************************************************
8.	A listing showing details required for report 6 
but including only specifications with a total cost more than 10000. 

Output should in the form of a sentence for each specification and 
the output column should be called ‘High Value Specifications’. 

All numeric fields should be formatted appropriately for 
numerical/monetary field and trimmed of leading spaces to give a 
consistent output. 
E.g.
Specification 102 Garden Patio used a total of 45 products 
at a cost of €1825.00 and the total cost 
including commission was €13825.00

Hint: involves a group but including only selected values in the output
with all output concatenated.

   prodPrice*qtyUsed 
********************************************************************/
SELECT 'Specification '||ID1||' '||TRIM(des)||
' used a total of '||qty||' products at a cost of '|| TRIM(TO_CHAR(COST_1,'U999'))
||' and the total cost including commission was '||
TRIM(TO_CHAR(TOTAL,'U999999.99')) AS "High Value Specifications" 
FROM
(SELECT MAX(specCommission) AS COMM,
SUM(prodPrice*qtyUsed) AS COST_1,
MAX(specCommission)+SUM(prodPrice*qtyUsed) AS TOTAL, 
max(specDes) as des,
max(specId) as ID1,
sum(qtyUsed) as qty FROM SpecProd
JOIN Product USING (prodId)
JOIN Specifications ON (Specifications.specId = SpecProd.specId2)
GROUP BY specId)

WHERE TOTAL > 10000;
