CREATE DATABASE IF NOT EXISTS Assignment6db;

use Assignment6db;

CREATE TABLE ass31jdbc (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    age INT NOT NULL
);

INSERT INTO ass31jdbc (name, age) VALUES ('mustang', 20);
INSERT INTO ass31jdbc (name, age) VALUES ('porche', 25);
INSERT INTO ass31jdbc (name, age) VALUES ('bugati', 22);

select * from ass31jdbc;