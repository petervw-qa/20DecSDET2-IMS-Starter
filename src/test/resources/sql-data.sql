INSERT INTO `customers` (`first_name`, `surname`) VALUES ('jordan', 'harrison');
INSERT INTO `items` (`name`, `price`) VALUES ('rubber', 1.50);
INSERT INTO `orders` (`fk_customers_id`) VALUES (1);
INSERT INTO `orders_items` (`fk_orders_id`, `fk_items_id`) VALUES (1, 1);
