CREATE DATABASE IF NOT EXISTS Assignment6db;

use Assignment6db;

CREATE TABLE ass40jdbc (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    age INT NOT NULL
);

INSERT INTO ass40jdbc (name, age) VALUES ('yuvraj', 22);
INSERT INTO ass40jdbc (name, age) VALUES ('yash', 25);
INSERT INTO ass40jdbc (name, age) VALUES ('mayur', 28);
INSERT INTO ass40jdbc (name, age) VALUES ('sahil', 35);

select * from ass40jdbc;