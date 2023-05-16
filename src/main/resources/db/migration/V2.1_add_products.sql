INSERT INTO manufacturers (manufacturer_name)
VALUES ('Apple'),
('Samsung'),
('Sony'),
('Google'),
('Microsoft'),
('Lenovo'),
('HP'),
('LG'),
('Dell'),
('Asus');

INSERT INTO categories (category_name)
VALUES ('Phones'),
       ('Laptops'),
       ('TVs'),
       ('Tablets'),
       ('Monitors');

INSERT INTO products (manufacturer_id, category_id, product_name, price)
VALUES (1, 1, 'iPhone X', 799),
       (2, 1, 'Samsung Galaxy S21', 999),
       (3, 2, 'Sony VAIO S', 1499),
       (4, 2, 'Google Pixelbook Go', 899),
       (5, 3, 'Microsoft Surface Laptop 4', 1299),
       (6, 3, 'Lenovo ThinkPad X1 Carbon', 1699),
       (7, 4, 'HP ElitePad 1000 G2', 699),
       (8, 4, 'LG Gram 14', 1099),
       (9, 5, 'Dell UltraSharp U2719D', 399),
       (10, 5, 'Asus ZenScreen MB16AC', 299);

