INSERT INTO users (username, role_name, password, email)
VALUES ('admin', 'ADMIN', 'pass', 'admin@example.com');

SELECT setval('users_id_seq', 2, false);