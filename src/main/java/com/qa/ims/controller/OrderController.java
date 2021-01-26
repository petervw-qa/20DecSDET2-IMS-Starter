package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.OrderDao;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.JavaUtilities;

public class OrderController implements ICrudController<Order> {

	public static final Logger LOGGER = LogManager.getLogger();

	private OrderDao orderDao;
	private JavaUtilities javaUtilities;

	public OrderController(OrderDao orderDao, JavaUtilities javaUtilities) {
		super();
        this.orderDao = orderDao;
        this.javaUtilities = javaUtilities;
    }

	@Override
	public List<Order> readAll() {
		List<Order> orders = orderDao.readAll();
		for (Order order : orders) {
			LOGGER.info(order);
		}
		return orders;
	}

	@Override
	public Order create() {
        LOGGER.info("Please enter the customer id");
        Long customer_id = javaUtilities.getLong();
        Order order = orderDao.create(new Order(customer_id));
        LOGGER.info("Order created");
        return order;
    }

	@Override
	 public Order update() {
        LOGGER.info("Please enter the customer id you would like to update");
        Long customer_id = javaUtilities.getLong();
        LOGGER.info("Please enter the order id you would like to update");
        Long order_id = javaUtilities.getLong();
        LOGGER.info("Would you like to ADD or REMOVE an item? Please type in all capitals.");
        String action = javaUtilities.getString();
        if (action.equals("ADD")) {
            LOGGER.info("Enter the id of the item you would like to add to the order");
            Long item_id = javaUtilities.getLong();
            Order order = orderDao.addToOrder_NewUpdate(new Order(order_id, customer_id), item_id);
            return order;
        } else if (action.equals("REMOVE")) {
            LOGGER.info("Enter the id of the item you would like to remove from the order");
            Long item_id = javaUtilities.getLong();
            orderDao.removeFromOrder_NewUpdate(new Order(order_id, customer_id), item_id);
            return null;
        }
        return null;
    }

	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the order you would like to delete");
		Long id = javaUtilities.getLong();
		return orderDao.delete(id);
	}

	public Double calculateOrderTotalCost() {
		LOGGER.info("Please enter the customer id");
		Long customer_id = javaUtilities.getLong();
		LOGGER.info("Please enter the order id");
		Long order_id = javaUtilities.getLong();
		LOGGER.info("Calculating Total...");
		Double orderTotal = orderDao.calculateOrderTotalCost(new Order(order_id, customer_id));
		LOGGER.info(orderTotal);
		return orderTotal;
	}
}
