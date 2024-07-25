CREATE DATABASE IF NOT EXISTS Assignment6db;

use Assignment6db;

DELIMITER //
CREATE PROCEDURE InsertPerson(IN personName VARCHAR(255), IN personAge INT)
BEGIN
    INSERT INTO ass17jdbc (name, age) VALUES (personName, personAge);
END//
DELIMITER ;


select * from ass17jdbc;