-- Create the main database for My Pet Acadamia
CREATE DATABASE IF NOT EXISTS pet_acadamia_db;
USE pet_acadamia_db;

-- User account information
DROP TABLE IF EXISTS accounts;
CREATE TABLE accounts(
	id INT AUTO_INCREMENT PRIMARY KEY,
	username VARCHAR(32) NOT NULL UNIQUE,
	password VARCHAR(32) NOT NULL
);

-- Dummy data for accounts table
LOCK TABLES accounts WRITE;
INSERT INTO accounts (username, password)
VALUES ('kenny', 'pass'),
	   ('ash', 'p3kachu'),
	   ('tye', 'digimon');
UNLOCK TABLES;