CREATE DATABASE IF NOT EXISTS Assignment6db;

use Assignment6db;

CREATE TABLE ass37jdbc (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    age INT NOT NULL
);

INSERT INTO ass37jdbc (name, age) VALUES ('harsh', 22);
INSERT INTO ass37jdbc (name, age) VALUES ('adi', 25);
INSERT INTO ass37jdbc (name, age) VALUES ('satya', 23);
INSERT INTO ass37jdbc (name, age) VALUES ('yash', 23);

select * from ass37jdbc;