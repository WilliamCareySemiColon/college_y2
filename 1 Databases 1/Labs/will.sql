--removing previous versions so the new ones can be set up 
DROP TABLE Species;
DROP TABLE Animal;
DROP TABLE Customer;
DROP TABLE Animal_Sale;

--creating the variables and the content for them
--make sure the primary key has a constraint
CREATE TABLE Species
(
    speciesId varchar2(10),
    species_name varchar2(10) NOT NULL,
    species_price number(5,2) NOT NULL,
    CONSTRAINT species_id_pk PRIMARY KEY (speciesId)
);

--when creating a foregn key, make sure there is a copy within the struct
--itself. EG: CONSTRAINT fk_key_type FOREIGN KEY (variable within this struct)
-- REFERENCES ANOTHER_STRUCT (PRIMARY_KEY_TYPE)
CREATE TABLE Animal
(
    animalId VARCHAR2(10),
    animal_name VARCHAR2(10) NOT NULL,
    species_Id varchar2(10),
    CONSTRAINT animal_id_pk PRIMARY KEY (animalId),
    CONSTRAINT fk_species_id FOREIGN KEY (species_Id)
        references Species(speciesId)
);

CREATE TABLE Customer
(
    custId varchar2(10),
    cust_name varchar2(20) NOT NULL,
    cust_email varchar(50) NOT NULL,
    CONSTRAINT cust_id_pk PRIMARY KEY (custId)
);

CREATE TABLE Animal_Sale
(
    sale_date DATE NOT NULL,
    animal_Id VARCHAR2(10),
    cust_Id varchar2(10),
    Constraint animal_id_fk FOREIGN KEY (animal_Id)
        REFERENCES Animal(animalId),
    Constraint cust_id_fk FOREIGN KEY (cust_Id)
        REFERENCES Customer (custId)
);

--now inserting information into the variables
INSERT INTO SPECIES(species_id, species_name, species_price) VALUES 
('1','Dog',9.99);
INSERT INTO SPECIES(species_id,species_name,species_price) VALUES 
('1','Dog',9.99);
INSERT INTO SPECIES(species_id,species_name,species_price) VALUES 
('2','Cat',10.20);
INSERT INTO SPECIES(species_id,species_name,species_price) VALUES 
('2','Cat',10.20);
INSERT INTO SPECIES(species_id,species_name,species_price) VALUES 
('3','Snamek',20.00);
INSERT INTO SPECIES(species_id,species_name,species_price) VALUES 
('4','Mouse',5.00);
INSERT INTO SPECIES(species_id,species_name,species_price) VALUES 
('1','Dog',9.99);
--dealing with customer now 
INSERT INTO CUSTOMER(cust_id, cust_email,cust_name) values 
('1','dsmith@yahoo.co.uk','D.Smith');
INSERT INTO CUSTOMER(cust_id, cust_email,cust_name) values 
('2','bb@gmail.com','B.Byrne');
INSERT INTO CUSTOMER(cust_id, cust_email,cust_name) values 
('2','bb@gmail.com','B.Byrne');
INSERT INTO CUSTOMER(cust_id,cust_name) values 
('3','X.Dobbs');
INSERT INTO CUSTOMER(cust_id, cust_email,cust_name) values 
('2','bb@gmail.com','B.Byrne');
--dealing with animal
INSERT INTO ANIMAL(animal_id, animal_name) VALUES ('12','Tiny');
INSERT INTO ANIMAL(animal_id, animal_name) VALUES ('13','Prince');
INSERT INTO ANIMAL(animal_id, animal_name) VALUES ('14','CJ');
INSERT INTO ANIMAL(animal_id, animal_name) VALUES ('15','Sid');
INSERT INTO ANIMAL(animal_id, animal_name) VALUES ('16','Sid');
INSERT INTO ANIMAL(animal_id, animal_name) VALUES ('17','Danger');
INSERT INTO ANIMAL(animal_id, animal_name) VALUES ('18','Bonnie');
--dealing with the next variable
INSERT INTO ANIMAL_SALE(sale_date) VALUES (TO_DATE('01/06/2017', 'DD/MM/YYYY'));
INSERT INTO ANIMAL_SALE(sale_date) VALUES (TO_DATE('11/06/2017', 'DD/MM/YYYY'));
INSERT INTO ANIMAL_SALE(sale_date) VALUES (TO_DATE('12/03/2017', 'DD/MM/YYYY'));
INSERT INTO ANIMAL_SALE(sale_date) VALUES (TO_DATE('04/09/2017', 'DD/MM/YYYY'));
INSERT INTO ANIMAL_SALE(sale_date) VALUES (TO_DATE('04/09/2017', 'DD/MM/YYYY'));

commit;

SELECT sale_date FROM Animal_Sale;
SELECT fk_species_id,animal_name FROM Animal;
SELECT animal_name FROM Animal WHERE fk_species_id BETWEEN '1' AND '1';