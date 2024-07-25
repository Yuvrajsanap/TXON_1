CREATE DATABASE IF NOT EXISTS Assignment6db;

use Assignment6db;

CREATE TABLE ass36jdbc (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    salary DECIMAL(10, 2)
);
INSERT INTO ass36jdbc (name, salary) VALUES ('shubham', 5000.00);
INSERT INTO ass36jdbc (name, salary) VALUES ('aniket', 6000.00);
INSERT INTO ass36jdbc (name, salary) VALUES ('omkar', 7000.00);

select * from ass36jdbc;