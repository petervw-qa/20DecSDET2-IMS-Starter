package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DatabaseUtilities;

public class OrderDao implements IDomainDao<Order> {

	public static final Logger LOGGER = LogManager.getLogger();
	
	public static final ItemDao itemDao = new ItemDao();

	@Override
	public Order create(Order order) {	
		try (Connection connection = DatabaseUtilities.getInstance().getConnection();
			PreparedStatement statement = connection.prepareStatement("INSERT INTO orders(fk_customers_id, price) VALUES (?, 0.0)");) {
			statement.setLong(1, order.getFk_customer_id().getId());
			statement.executeUpdate();
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		} return null;
	}

	@Override
	public List<Order> readAll() {
		try (Connection connection = DatabaseUtilities.getInstance().getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM orders");) {
			List<Order> orders = new ArrayList<>();
			while (resultSet.next()) {
				orders.add(modelFromResultSet(resultSet));
			} return orders;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		} return new ArrayList<>();
	}

	public Order readLatest() {
		try (Connection connection = DatabaseUtilities.getInstance().getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM orders ORDER BY id DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		} return null;
	}
	
	public Order read(Long orderID) {
		try (Connection connection = DatabaseUtilities.getInstance().getConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM orders WHERE id = ?");) {
			statement.setLong(1, orderID);
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		} return null;
	}

	@Override
	public Order update(Order order) {
        return null;
	}

	@Override
	public int delete(long orderID) {
		try (Connection connection = DatabaseUtilities.getInstance().getConnection();
			Statement statement = connection.createStatement();) {
			return statement.executeUpdate("DELETE FROM orders WHERE id = " + orderID);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		} return 0;
	}
	
	public int deleteOrdersItems(long orderID) {
		try (Connection connection = DatabaseUtilities.getInstance().getConnection();
			Statement statement = connection.createStatement();) {
			return statement.executeUpdate("DELETE FROM orders_items WHERE fk_orders_id = " + orderID);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		} return 0;
	}
	
	public double calculateTotalOrderCost(Long orderID) {
		try(Connection connection = DatabaseUtilities.getInstance().getConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM orders_items WHERE fk_orders_id = ?");) {
			statement.setLong(1, orderID);
			ResultSet resultSet = statement.executeQuery();
			double totalOrderCost = 0;
			while( resultSet.next() ) {
				totalOrderCost += itemDao.read( resultSet.getLong("fk_items_id")  ).getPrice();
			} return totalOrderCost;	
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		} return 0.0;
	}

	@Override
	public Order modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("id");
		Long fk_customers_id = resultSet.getLong("fk_customers_id");
		OrderDao orderDao = new OrderDao();
		List<Item> itemList = orderDao.listOfItems(id);
		double TotalPrice = orderDao.calculateTotalOrderCost(id);
		CustomerDao customerDao = new CustomerDao();
        Customer customer = customerDao.read(fk_customers_id);
		return new Order(id, customer, itemList, TotalPrice);
	}
	
	public List<Item> listOfItems(Long orderID){
		List<Item> ListOfItems = new ArrayList<>();
		try(Connection connection = DatabaseUtilities.getInstance().getConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM orders_items WHERE fk_orders_id = ?");) {
			statement.setLong(1, orderID);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
			ListOfItems.add(itemDao.read( resultSet.getLong("fk_items_id")));	
			} return ListOfItems;
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		} return ListOfItems;
	}
	
	public Order addToOrder_NewUpdate(Order order, long orderID, Long itemID) {
		try(Connection connection = DatabaseUtilities.getInstance().getConnection();
			PreparedStatement statement = connection.prepareStatement("INSERT INTO orders_items(fk_orders_id, fk_items_id) VALUES (?, ?)");) {
			statement.setLong(1, orderID);
			statement.setLong(2, itemID);
			statement.executeUpdate();	
			List<Item> ListOfItems = order.getOrdersItems();
			ListOfItems.add( itemDao.read(itemID) );
			Order updatedOrder = order;
			updatedOrder.setOrdersItems(ListOfItems);
			updatedOrder.setTotalPrice(updatedOrder.getTotalPrice() + itemDao.read(itemID).getPrice());
			return updatedOrder;
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		} return null;
	}
	
	public Order removeFromOrder_NewUpdate(Order order, Long orderID, Long itemID) {
		try(Connection connection = DatabaseUtilities.getInstance().getConnection();
			Statement statement = connection.createStatement();) {
			statement.executeUpdate("DELETE FROM orders_items WHERE (fk_orders_id = ? AND fk_items_id = ?)");
			List<Item> ListOfItems = order.getOrdersItems();
			ListOfItems.remove( itemDao.read(itemID) );
			Order updatedOrder = order;
			updatedOrder.setOrdersItems(ListOfItems);
			updatedOrder.setTotalPrice(updatedOrder.getTotalPrice() + itemDao.read(itemID).getPrice());
			return updatedOrder;
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		} return null;
	}
	
	

}
