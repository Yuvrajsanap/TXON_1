CREATE DATABASE IF NOT EXISTS Assignment6db;

use Assignment6db;

CREATE TABLE ass12jdbc (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    age INT NOT NULL
);
INSERT INTO ass12jdbc (name, age) VALUES ('yuvraj', 20);
INSERT INTO ass12jdbc (name, age) VALUES ('hari', 25);
INSERT INTO ass12jdbc (name, age) VALUES ('ys', 25);
insert into ass12jdbc (name,age) values('suraj',19);
select * from ass12jdbc;