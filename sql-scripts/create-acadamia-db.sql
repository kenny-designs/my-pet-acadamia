-- Create the main database for My Pet Acadamia
CREATE DATABASE IF NOT EXISTS pet_acadamia_db;
USE pet_acadamia_db;

-- User account information
DROP TABLE IF EXISTS accounts;
CREATE TABLE accounts(
	id INT AUTO_INCREMENT PRIMARY KEY,
	username VARCHAR(32) NOT NULL UNIQUE,
	password VARCHAR(32) NOT NULL,
	battles_won INT DEFAULT 0,
	battles_lost INT DEFAULT 0
);

-- Dummy data for accounts table
LOCK TABLES accounts WRITE;
INSERT INTO accounts (username, password, battles_won, battles_lost)
VALUES ('kenny', 'password', 34, 4),
	   ('ash', 'p3kachi123', 0, 1000),
	   ('tye', 'digimonMun', 420, 69);
UNLOCK TABLES;