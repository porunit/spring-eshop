CREATE TABLE purchases
(
    id            serial PRIMARY KEY,
    purchase_date timestamp NOT NULL
);

CREATE TABLE categories
(
    id            serial PRIMARY KEY,
    category_name varchar(50) NOT NULL
);

CREATE TABLE manufacturers
(
    id                serial PRIMARY KEY,
    manufacturer_name varchar(50) NOT NULL
);

CREATE TABLE products
(
    id              serial PRIMARY KEY,
    manufacturer_id BIGINT NOT NULL REFERENCES manufacturers (id),
    category_id     BIGINT NOT NULL REFERENCES categories (id),
    product_name    text   NOT NULL,
    price           int    NOT NULL CHECK (price > 0)
);

CREATE TABLE deliveries
(
    id            serial PRIMARY KEY,
    product_id    BIGINT NOT NULL REFERENCES products (id),
    delivery_date date   NOT NULL,
    product_count int    NOT NULL
);

CREATE TABLE purchased_items
(
    id            serial PRIMARY KEY,
    purchase_id   BIGINT NOT NULL REFERENCES purchases (id),
    product_id    BIGINT NOT NULL REFERENCES products (id),
    product_count int    NOT NULL CHECK (product_count > 0)
);

CREATE TABLE price_changes
(
    id                serial PRIMARY KEY,
    product_id        BIGINT    NOT NULL REFERENCES products (id),
    price_change_date timestamp NOT NULL,
    old_price         int       NOT NULL
);

CREATE TABLE delivery_products
(
    id          serial PRIMARY KEY,
    delivery_id BIGINT REFERENCES deliveries (id),
    product_id  BIGINT REFERENCES products (id)
);

CREATE TABLE roles
(
    id        SERIAL PRIMARY KEY,
    role_name VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE users
(
	id serial PRIMARY KEY,
	username varchar(20) NOT NULL UNIQUE,
	role_name varchar(20) NOT NULL,
	password varchar(16) NOT NULL,
	email varchar(20) NOT NULL,
	CONSTRAINT username_unique UNIQUE(username)
);



