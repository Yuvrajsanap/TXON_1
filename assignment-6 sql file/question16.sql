CREATE DATABASE IF NOT EXISTS Assignment6db;

use Assignment6db;

CREATE TABLE ass16jdbc (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    age INT NOT NULL
);

select * from ass16jdbc;