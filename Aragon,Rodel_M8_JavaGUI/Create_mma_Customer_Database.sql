DROP DATABASE IF EXISTS mma;
CREATE DATABASE mma;
USE mma;

CREATE TABLE Customer (
    Customer_Email		varchar(50)		PRIMARY KEY,
    Customer_FName		varchar(20)		NOT NULL,
    Customer_LName		varchar(20)		NOT NULL
);

INSERT INTO Customer Values
('frankjones@yahoo.com', 'Frank', 'Jones'),
('johnsmith@hotmail.com', 'John', 'Smith'),
('seagreen@levi.com', 'Cynthia', 'Green'),
('wendyk@warners.com', 'Wendy', 'Kowolski');


GRANT SELECT, INSERT, DELETE, UPDATE
ON mma.*
TO mma_user@localhost
IDENTIFIED BY 'sesame';