CREATE DATABASE IF NOT EXISTS Assignment6db;

use Assignment6db;

CREATE TABLE ass30jdbc (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    age INT NOT NULL
);

INSERT INTO ass30jdbc (name, age) VALUES ('yuvraj', 20);
INSERT INTO ass30jdbc (name, age) VALUES ('yash', 30);
INSERT INTO ass30jdbc (name, age) VALUES ('vishal', 22);

select * from ass30jdbc;