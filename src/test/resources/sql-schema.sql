DROP TABLE `order_items`;
DROP TABLE `orders`;
DROP TABLE `items`;
DROP TABLE `customers`;

CREATE TABLE IF NOT EXISTS customers(
id INT PRIMARY KEY AUTO_INCREMENT,
first_name VARCHAR(255),
surname VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS orders(
id INT PRIMARY KEY AUTO_INCREMENT,
fk_customer_id INT,
FOREIGN KEY (fk_customer_id) REFERENCES customers(id)
);

CREATE TABLE IF NOT EXISTS items(
id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(255),
price DECIMAL(5, 2)
);

CREATE TABLE IF NOT EXISTS order_items(
fk_order_id INT,
fk_item_id INT,
FOREIGN KEY (fk_order_id) REFERENCES orders(id),
FOREIGN KEY (fk_item_id) REFERENCES items(id)