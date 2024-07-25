CREATE DATABASE IF NOT EXISTS Assignment6db;

use Assignment6db;

CREATE TABLE ass39jdbc (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    age INT NOT NULL
);

INSERT INTO ass39jdbc (name, age) VALUES ('om', 22);
INSERT INTO ass39jdbc (name, age) VALUES ('mayank', 25);
INSERT INTO ass39jdbc (name, age) VALUES ('vatan', 28);
INSERT INTO ass39jdbc (name, age) VALUES ('yash', 35);

select * from ass39jdbc;