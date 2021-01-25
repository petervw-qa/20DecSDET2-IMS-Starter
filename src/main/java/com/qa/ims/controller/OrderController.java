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
		LOGGER.info("Please enter the customer's id");
		Long customer_id = javaUtilities.getLong();
		LOGGER.info("Please enter the order's id");
		Long order_id = javaUtilities.getLong();
		LOGGER.info("Would you like to ADD or REMOVE an item from the order?");
		String action = javaUtilities.getString();
		if (action.equals("ADD")) {
			LOGGER.info("Enter the id of the item you would like to add");
			Long item_id = javaUtilities.getLong();
			Order order = orderDao.addToOrder(new Order(order_id, customer_id), item_id);
			return order;
		} else if (action.equals("REMOVE")) {
			LOGGER.info("Enter the id of the item you would like to remove from the order");
			Long item_id = javaUtilities.getLong();
			Order order = orderDao.removeFromOrder(item_id);
			return order;
		}

		return null;
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

	public Double calculateOrderTotal() {
		LOGGER.info("Please enter the customer id");
		Long c_id = javaUtilities.getLong();
		LOGGER.info("Please enter the order id");
		Long o_id = javaUtilities.getLong();
		LOGGER.info("Calculating Total...");
		Double total = orderDao.calculateOrderTotalCost(new Order(o_id, c_id));
		LOGGER.info(total);
		return total;
	}
}
