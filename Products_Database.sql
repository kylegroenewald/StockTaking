DROP DATABASE IF EXISTS checkersproducts;
CREATE DATABASE checkersproducts;
USE checkersproducts;

CREATE TABLE IF NOT EXISTS products
(
prodID INTEGER AUTO_INCREMENT NOT NULL PRIMARY KEY,
prodName VARCHAR(30) NOT NULL,
prodType VARCHAR(30) NOT NULL,
prodPrice DECIMAL (6,2) NOT NULL
);