-- Create the main database for My Pet Acadamia
CREATE DATABASE IF NOT EXISTS pet_acadamia_db;
USE pet_acadamia_db;

------------------
-- ACCOUNTS TABLE
------------------

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

----------------------
-- HEALTH_TYPES TABLE
----------------------

-- Health types and the amount of health provided at the given level
DROP TABLE IF EXISTS health_types;
CREATE TABLE health_types(
	health_type VARCHAR(12) NOT NULL,
	level INT NOT NULL,
	amount INT NOT NULL
);

-- Populate health_types
LOCK TABLES health_types WRITE;

-- Insert lightweight
INSERT INTO health_types (health_type, level, amount)
VALUES ('lightweight', 1, 16),
	   ('lightweight', 2, 21),
	   ('lightweight', 3, 25),
	   ('lightweight', 4, 29),
	   ('lightweight', 5, 35),
	   ('lightweight', 6, 39),
	   ('lightweight', 7, 43),
	   ('lightweight', 8, 48),
	   ('lightweight', 9, 51),
	   ('lightweight', 10, 55);

-- Insert middleweight
INSERT INTO health_types (health_type, level, amount)
VALUES ('middleweight', 1, 20),
	   ('middleweight', 2, 25),
	   ('middleweight', 3, 29),
	   ('middleweight', 4, 34),
	   ('middleweight', 5, 38),
	   ('middleweight', 6, 43),
	   ('middleweight', 7, 47),
	   ('middleweight', 8, 51),
	   ('middleweight', 9, 56),
	   ('middleweight', 10, 60);

-- Insert heavyweight
INSERT INTO health_types (health_type, level, amount)
VALUES ('heavyweight', 1, 23),
	   ('heavyweight', 2, 27),
	   ('heavyweight', 3, 32),
	   ('heavyweight', 4, 37),
	   ('heavyweight', 5, 41),
	   ('heavyweight', 6, 46),
	   ('heavyweight', 7, 50),
	   ('heavyweight', 8, 55),
	   ('heavyweight', 9, 60),
	   ('heavyweight', 10, 64);

UNLOCK TABLES;

