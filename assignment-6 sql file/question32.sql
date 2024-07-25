CREATE DATABASE IF NOT EXISTS Assignment6db;

use Assignment6db;

CREATE TABLE ass32jdbc (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    age INT NOT NULL
);

INSERT INTO ass32jdbc (name, age) VALUES ('yuvraj', 20);
INSERT INTO ass32jdbc (name, age) VALUES ('amit', 25);
INSERT INTO ass32jdbc (name, age) VALUES ('aniket', 22);

select * from ass32jdbc;