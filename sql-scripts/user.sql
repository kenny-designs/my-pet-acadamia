-- Creates user 'petuser' to manage the pet_acadamia_db database
CREATE USER 'petuser'@'localhost' IDENTIFIED BY 'cemePet29';

GRANT ALL PRIVILEGES ON pet_acadamia_db.* TO 'petuser'@'localhost';

ALTER USER 'petuser'@'localhost' IDENTIFIED WITH mysql_native_password BY 'cemePet29';
