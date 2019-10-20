CREATE SCHEMA IF NOT EXISTS testdb;

CREATE TABLE IF NOT EXISTS testdb.roles(
id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS testdb.users (
id INT PRIMARY KEY AUTO_INCREMENT,
login VARCHAR(255) NOT NULL UNIQUE,
pass VARCHAR(255) NOT NULL,
email VARCHAR(255) NOT NULL,
firstname VARCHAR(255) NOT NULL,
lastname VARCHAR(255) NOT NULL,
birthday DATE NOT NULL,
role_id INT NOT NULL,
FOREIGN KEY (role_id) REFERENCES roles(id) ON UPDATE CASCADE ON DELETE CASCADE
);

DELETE FROM testdb.users WHERE id = 1;
DELETE FROM testdb.users WHERE id = 2;
DELETE FROM testdb.roles WHERE id = 1;
DELETE FROM testdb.roles WHERE id = 2;

INSERT INTO testdb.roles (id, name) VALUES (1, 'User');
INSERT INTO testdb.roles (id, name) VALUES (2, 'Admin');

INSERT INTO testdb.users (id, login, pass, email, firstname, lastname, birthday, role_id)
VALUES (1, 'user', 'password', 'admin@mail.com', 'Ihor', 'Velychko', '1996-01-20', 1);

INSERT INTO testdb.users (id, login, pass, email, firstname, lastname, birthday, role_id)
VALUES (2, 'admin', 'password', 'user@mail.com', 'John', 'Smith', '1980-04-06', 2)




