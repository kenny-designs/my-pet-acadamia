-- Create the main database for My Pet Acadamia
CREATE DATABASE IF NOT EXISTS pet_acadamia_db;
USE pet_acadamia_db;

-- DROP TABLES
DROP TABLE IF EXISTS player_battle_instances;	-- ongoing pvp battles
DROP TABLE IF EXISTS safari_battle_instances;	-- ongoing pve battles
DROP TABLE IF EXISTS team_instances;			-- active battle pets teams
DROP TABLE IF EXISTS battle_pets;				-- pets currently in battle
DROP TABLE IF EXISTS player_pets;				-- pets players own
DROP TABLE IF EXISTS safari_pets;				-- wild pets that players fight
DROP TABLE IF EXISTS pets;						-- all pets available in the game
DROP TABLE IF EXISTS skills;					-- skills that pets use in battle
DROP TABLE IF EXISTS accounts;					-- player accounts
DROP TABLE IF EXISTS health_types;				-- lookup table for hitpoint totals
DROP TABLE IF EXISTS damages;					-- lookup table for damage types
DROP TABLE IF EXISTS debuffs;					-- lookup table for debuff types
DROP TABLE IF EXISTS buffs;						-- lookup table for buff types

-- ACCOUNTS TABLE
-- User account information
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

-- HEALTH_TYPES TABLE
-- Health types and the amount of health provided at the given level
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

-- SKILLS TABLE
-- All skills available in the game that pets can use in battle
CREATE TABLE skills(
	id INT AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(32) NOT NULL UNIQUE,
	damage_type VARCHAR(64),
	debuff_type VARCHAR(64),
	buff_type VARCHAR(64)
);

-- PETS TABLE
-- Basic pet information table
CREATE TABLE pets(
	id INT AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(32) NOT NULL UNIQUE,
	health_type VARCHAR(12) NOT NULL,
	image VARCHAR(255) NOT NULL UNIQUE,
	description VARCHAR(255) NOT NULL UNIQUE,
	skill_1_id INT,
	skill_2_id INT,
	skill_3_id INT,
	skill_4_id INT,	
	FOREIGN KEY (skill_1_id) REFERENCES skills(id),
	FOREIGN KEY (skill_2_id) REFERENCES skills(id),
	FOREIGN KEY (skill_3_id) REFERENCES skills(id),
	FOREIGN KEY (skill_4_id) REFERENCES skills(id)
);

-- Add pet data
LOCK TABLES pets WRITE;

INSERT INTO pets (name, health_type, image, description)
VALUES ('Pachino',  'heavyweight',  'pachino.png', 'A powerful bird-like pet! Prefers to strike from the sky with its claws and disrupt the battle with its powerful wings. Some say it looks a bit too real...'),
	   ('Jujimufu', 'heavyweight',  'jujimufu.png', 'An incredibly fat snake. Could eat you whole if it could catch up... For what they lack in agility, the Jujimufu mesmerizes its foes and leaves a lethal bite!'),
	   ('Eric',     'heavyweight',  'eric.png', 'Eric is a gigantic bear and perhaps one of the most fearsome competitors in the entire Boku No Pet Acadamia: Budokai Tenkaichi 3 Ultra Edition universe! Eric charges in full force without a care and often leaves disaster in its wake.'),
	   ('Spiegel',  'lightweight',  'spiegel.png', 'This little fella is not much but will leave its foes in devastation with a combination of poison and needles! In case of an emergency, the spiky Spiegel will explode and bring everyone down with it. See you sea fish boy...'),
	   ('Lord',     'lightweight',  'lord.png', 'A slimy, snail-like creature. Despite appearing so small and fragile, Lords are able to disorient their enemies and harden their shells to protect against even the nastiest of attacks. Also really likes listening to Lorde.'),
	   ('Blowfeld', 'middleweight', 'blowfeld.png', 'An adorable little creature with a devastating poison! The Blowfeld uses its powerful claws to hurt others and stuff. DO NOT MESS WITH THEM! They are angry.'),
	   ('Cat', 		'middleweight', 'cat.png', 'A normal, everyday house cat. What kind of sick monster would make their own pet cat fight other animals?'),
	   ('Chuck', 	'middleweight', 'chuck.png', 'The purest of all creatures! Can hold their own quite well but prefers peace and tranquility. If at all possible, the Chuck will befriend their foes and leave the battle to some other pet.'),
	   ('Weezer', 	'lightweight',  'weezer.png', 'In the Perfect Situation, Weezer prefers to fight from the skies! Sadly, they are notably much weaker than their flying counter-parts such as the Pachino. To make up for this, Weezers prefer mending their wounds and mesmerizing the competition.');
 
UNLOCK TABLES;

-- PLAYER_PETS TABLE
-- Collection of all pets in the game and their owners
CREATE TABLE player_pets(
	id INT AUTO_INCREMENT PRIMARY KEY,	
	level INT DEFAULT 1,
	exp INT DEFAULT 0,
	is_team BIT DEFAULT 0,
	pet_id INT NOT NULL,
	account_id INT NOT NULL,
	FOREIGN KEY (pet_id) REFERENCES pets(id),
	FOREIGN KEY (account_id) REFERENCES accounts(id)
);

-- Add dummy data for existing dummy accounts
LOCK TABLES player_pets WRITE;

INSERT INTO player_pets (level, pet_id, account_id, is_team)
VALUES (4, 8, 1, 1),
	   (7, 3, 1, 1),
	   (9, 7, 1, 1),
	   (2, 3, 1, 0),
	   (5, 1, 2, 1),
	   (1, 2, 2, 1),
	   (2, 5, 2, 1),
	   (2, 6, 3, 1),
	   (5, 5, 3, 1),
	   (7, 4, 3, 1);
 
UNLOCK TABLES;

-- SAFARI_PETS TABLE
-- Collection of all pets in the safari
CREATE TABLE safari_pets(
	id INT AUTO_INCREMENT PRIMARY KEY,	
	level INT DEFAULT 1,
	pet_id INT NOT NULL,
	FOREIGN KEY (pet_id) REFERENCES pets(id)
);

-- BATTLE_PETS TABLE
-- All pets that are currently fighting (both player owned and NPC)
CREATE TABLE battle_pets(
	id INT AUTO_INCREMENT PRIMARY KEY,	
	hitpoints INT NOT NULL,
	player_pet_id INT,
	safari_pet_id INT,
	FOREIGN KEY (player_pet_id) REFERENCES player_pets(id),
	FOREIGN KEY (safari_pet_id) REFERENCES safari_pets(id)
);

-- DAMAGES TABLE
-- Properties of various damage inflicting skills
CREATE TABLE damages(
	damage_type VARCHAR(64) NOT NULL,
	level INT NOT NULL,
	low_damage INT NOT NULL,
	high_damage INT NOT NULL
);

-- DEBUFFS TABLE
-- Properties of various debuffs that pets inflict
CREATE TABLE debuffs(
	debuff_type VARCHAR(64) NOT NULL,
	level INT NOT NULL,
	turn_duration INT NOT NULL
);

-- BUFFS TABLE
-- Properties of various buffs pets can inflict
CREATE TABLE buffs(
	debuff_type VARCHAR(64) NOT NULL,
	level INT NOT NULL,
	turn_duration INT NOT NULL
);

-- TEAM_INSTANCES TABLE
-- Teams of battle pets currently engaging in combat
CREATE TABLE team_instances(
	id INT AUTO_INCREMENT PRIMARY KEY,	
	battle_pet_1_id INT NOT NULL,
	battle_pet_2_id INT,
	battle_pet_3_id INT,
	FOREIGN KEY (battle_pet_1_id) REFERENCES battle_pets(id),
	FOREIGN KEY (battle_pet_2_id) REFERENCES battle_pets(id),
	FOREIGN KEY (battle_pet_3_id) REFERENCES battle_pets(id)
);

-- PLAYER_BATTLE_INSTANCES TABLE
-- Ongoing player versus player battles
CREATE TABLE player_battle_instances(
	id INT AUTO_INCREMENT PRIMARY KEY,	
	team_1_id INT NOT NULL,
	team_2_id INT NOT NULL,
	FOREIGN KEY (team_1_id) REFERENCES team_instances(id),
	FOREIGN KEY (team_2_id) REFERENCES team_instances(id)
);

-- SAFARI_BATTLE_INSTANCES TABLE
-- Ongoing player versus player battles
CREATE TABLE safari_battle_instances(
	id INT AUTO_INCREMENT PRIMARY KEY,	
	player_team_id INT NOT NULL,
	safari_team_id INT NOT NULL,
	FOREIGN KEY (player_team_id) REFERENCES team_instances(id),
	FOREIGN KEY (safari_team_id) REFERENCES team_instances(id)
);
