INSERT INTO roles (id, name) VALUES (1, 'User');
INSERT INTO roles (id, name) VALUES (2, 'Admin');

INSERT INTO users (id, login, password, email, firstname, lastname, birthday, role_id) VALUES (1, 'user', 'password', 'admin@mail.com', 'Ihor', 'Velychko', '1996-01-20', 1);

INSERT INTO users (id, login, password, email, firstname, lastname, birthday, role_id) VALUES (2, 'admin', 'password', 'user@mail.com', 'John', 'Smith', '1980-04-06', 2)