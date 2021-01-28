DROP TABLE `orders_items`;
DROP TABLE `orders`;
DROP TABLE `items`;
DROP TABLE `customers`;

CREATE TABLE IF NOT EXISTS customers(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`first_name` VARCHAR(255),
`surname` VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS orders(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`fk_customers_id` INT NOT NULL,
`price` DECIMAL(5,2) NOT NULL,
FOREIGN KEY (`fk_customers_id`) REFERENCES customers(`id`)
);

CREATE TABLE IF NOT EXISTS items(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(255),
`price` DECIMAL(5, 2)
);

CREATE TABLE IF NOT EXISTS orders_items(
`fk_orders_id` INT NOT NULL,
`fk_items_id` INT NOT NULL,
FOREIGN KEY (`fk_orders_id`) REFERENCES orders(id),
FOREIGN KEY (`fk_items_id`) REFERENCES items(id)
);