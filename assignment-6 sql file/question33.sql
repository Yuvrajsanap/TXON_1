CREATE DATABASE IF NOT EXISTS Assignment6db;

use Assignment6db;

CREATE TABLE ass33jdbc (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    age INT NOT NULL
);

INSERT INTO ass33jdbc (name, age) VALUES ('harsh', 22);
INSERT INTO ass33jdbc (name, age) VALUES ('adi', 25);
INSERT INTO ass33jdbc (name, age) VALUES ('satya', 23);

select * from ass33jdbc;