CREATE SCHEMA IF NOT EXISTS testdb;

CREATE TABLE IF NOT EXISTS testdb.roles(id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255) NOT NULL);

CREATE TABLE IF NOT EXISTS testdb.users (id INT PRIMARY KEY AUTO_INCREMENT, login VARCHAR(255) NOT NULL, pass VARCHAR(255) NOT NULL, email VARCHAR(255) NOT NULL, firstname VARCHAR(255) NOT NULL, lastname VARCHAR(255) NOT NULL, birthday DATE NOT NULL, role_id INT NOT NULL, FOREIGN KEY (role_id) REFERENCES roles(id) ON UPDATE CASCADE ON DELETE CASCADE)