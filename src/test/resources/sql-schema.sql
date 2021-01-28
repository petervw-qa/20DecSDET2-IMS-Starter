DROP TABLE IF EXISTS orders_items;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS items;
DROP TABLE IF EXISTS customers;

CREATE TABLE IF NOT EXISTS customers (
`id` INT(10) NOT NULL AUTO_INCREMENT,
`first_name` VARCHAR(50) DEFAULT NULL,
`surname` VARCHAR(50) DEFAULT NULL,
PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS orders (
`id` INT(11) NOT NULL AUTO_INCREMENT,
`fk_customers_id` INT NOT NULL,
`price` DOUBLE NOT NULL,
PRIMARY KEY (`id`),
FOREIGN KEY (`fk_customers_id`) REFERENCES customers(`id`)
);

CREATE TABLE IF NOT EXISTS items (
`id` INT(11) NOT NULL AUTO_INCREMENT,
`name` VARCHAR(100) NOT NULL,
`price` DOUBLE NOT NULL,
PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS orders_items (
`fk_orders_id` INT NOT NULL,
`fk_items_id` INT NOT NULL,
FOREIGN KEY (`fk_orders_id`) REFERENCES orders(`id`),
FOREIGN KEY (`fk_items_id`) REFERENCES items(`id`)
);