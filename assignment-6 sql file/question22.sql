CREATE DATABASE IF NOT EXISTS Assignment6db;

use Assignment6db;

CREATE TABLE ass22jdbc (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    age INT NOT NULL
);

INSERT INTO ass22jdbc (name, age) VALUES ('ganesh', 20);
INSERT INTO ass22jdbc (name, age) VALUES ('anand', 25);
INSERT INTO ass22jdbc (name, age) VALUES ('mayank', 22);

select * from ass22jdbc;