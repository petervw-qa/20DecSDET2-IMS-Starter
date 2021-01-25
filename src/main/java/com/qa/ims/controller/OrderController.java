package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.OrderDao;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.JavaUtilities;

public class OrderController implements ICrudController<Order>  {
	
	public static final Logger LOGGER = LogManager.getLogger();

    private OrderDao orderDao;
    private JavaUtilities javaUtilities;

	@Override
	public List<Order> readAll() {
        List<Order> orders = orderDao.readAll();
        for (Order order: orders) {
            LOGGER.info(order);
        }
        return orders;
    }


	@Override
	public Order create() {
        LOGGER.info("Please enter the order ID");
        Long id = javaUtilities.getLong();
        LOGGER.info("Please enter the customer's ID");
        Long fk_customer_id = javaUtilities.getLong();
        Order order = orderDao.create(new Order(id, fk_customer_id));
        LOGGER.info("Customer created");
        return order;
    }

	@Override
	public Order update() {
        LOGGER.info("Please enter the id of the order you would like to update");
        Long id = javaUtilities.getLong();
        LOGGER.info("Please enter the customer's ID number");
        Long fk_customer_id = javaUtilities.getLong();
        Order order = orderDao.update(new Order(id, fk_customer_id));
        LOGGER.info("Order Updated");
        return order;
    }

	@Override
	public int delete() {
        LOGGER.info("Please enter the id of the order you would like to delete");
        Long id = javaUtilities.getLong();
        return orderDao.delete(id);
    }
}
