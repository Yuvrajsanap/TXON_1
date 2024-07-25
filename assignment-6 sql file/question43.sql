CREATE DATABASE IF NOT EXISTS Assignment6db;

use Assignment6db;

CREATE TABLE ass43jdbc (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    age INT NOT NULL
);

INSERT INTO ass43jdbc (name, age) VALUES ('yuvraj', 20);
INSERT INTO ass43jdbc (name, age) VALUES ('aniket', 35);
INSERT INTO ass43jdbc (name, age) VALUES ('sky', 28);
INSERT INTO ass43jdbc (name, age) VALUES ('ravi', 35);

select * from ass43jdbc;