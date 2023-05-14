INSERT INTO manufacturers (manufacturer_name)
VALUES ('Apple'),
       ('Samsung'),
       ('Sony');
INSERT INTO categories (category_name)
VALUES ('Phones'),
       ('Laptops'),
       ('TVs');
INSERT INTO products (manufacturer_id, category_id, product_name, price)
VALUES (1, 1, 'iPhone X', 799),
       (2, 2, 'Samsung Galaxy Book Pro', 1499),
       (3, 3, 'Sony Bravia OLED TV', 2499);