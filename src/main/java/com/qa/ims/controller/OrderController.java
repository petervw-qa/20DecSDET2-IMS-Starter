package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.CustomerDao;
import com.qa.ims.persistence.dao.OrderDao;
import com.qa.ims.persistence.domain.Customer;
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
        LOGGER.info("Please enter the ID of the Customer placing the order.");
        Long fk_customers_id = javaUtilities.getLong();
        CustomerDao customerDao = new CustomerDao();  
        Customer newCustomer = customerDao.read(fk_customers_id);
        Order order = orderDao.create(new Order(newCustomer));
        LOGGER.info("Order created");
        return order;
	}

	@Override
	public Order update() {
		Order order = new Order();
		LOGGER.info("Please enter the id of the order you would like to update.");
        Long order_id = javaUtilities.getLong();
        LOGGER.info("Would you like to ADD or REMOVE and item? Please enter in all capitals.");
        String choice = javaUtilities.getString();
        LOGGER.info("Please enter the ID of the item you would like to choose.");
        Long item_id = javaUtilities.getLong();
        if ( choice.equals("ADD") ) {
        	order = orderDao.addToOrder_NewUpdate(orderDao.read(order_id), item_id, order_id);
        	LOGGER.info("The item has been added to the order. Thank you!");
        } else if ( choice.equals("REMOVE") ) {
        	order = orderDao.removeFromOrder_NewUpdate(orderDao.read(order_id), item_id, order_id);
        	LOGGER.info("The item has been removed from order, Thank you!");
        }
        
        LOGGER.info("Order updated");
		return order;
        
	}

	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the order you would like to delete");
		Long id = javaUtilities.getLong();
		return orderDao.delete(id);
	}

}
