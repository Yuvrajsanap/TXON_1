CREATE DATABASE IF NOT EXISTS Assignment6db;

use Assignment6db;

CREATE TABLE ass9jdbc (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    age INT NOT NULL
);
INSERT INTO ass9jdbc (name, age) VALUES ('yuvraj', 20);
INSERT INTO ass9jdbc (name, age) VALUES ('sahil', 25);
INSERT INTO ass9jdbc (name, age) VALUES ('om', 25);
select * from ass9jdbc;