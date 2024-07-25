CREATE DATABASE IF NOT EXISTS Assignment6db;

use Assignment6db;

CREATE TABLE ass41jdbc (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    age INT NOT NULL
);

INSERT INTO ass41jdbc (name, age) VALUES ('rohit sharma', 37);
INSERT INTO ass41jdbc (name, age) VALUES ('virat kohli', 35);
INSERT INTO ass41jdbc (name, age) VALUES ('sky', 28);
INSERT INTO ass41jdbc (name, age) VALUES ('bumrah', 35);

select * from ass41jdbc;