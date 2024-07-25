CREATE DATABASE IF NOT EXISTS Assignment6db;

use Assignment6db;

CREATE TABLE ass23jdbc (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    age INT NOT NULL
);

INSERT INTO ass23jdbc (name, age) VALUES ('yuvraj', 20);
INSERT INTO ass23jdbc (name, age) VALUES ('hari', 25);
INSERT INTO ass23jdbc (name, age) VALUES ('mayank', 22);

select * from ass23jdbc;