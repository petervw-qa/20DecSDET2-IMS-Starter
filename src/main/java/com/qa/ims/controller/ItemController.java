package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.qa.ims.persistence.dao.ItemDao;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.JavaUtilities;

public class ItemController implements ICrudController<Item> {
	
	public static final Logger LOGGER = LogManager.getLogger();

    private ItemDao itemDao;
    private JavaUtilities javaUtilities;

	@Override
	public List<Item> readAll() {
        List<Item> items = itemDao.readAll();
        for (Item item : items) {
            LOGGER.info(item);
        }
        return items;
    }

	@Override
	public Item create() {
        LOGGER.info("Please enter the items title");
        String name = javaUtilities.getString();
        LOGGER.info("Please enter a value");
        Double value = javaUtilities.getDouble();
        Item item = itemDao.create(new Item(name, value));
        LOGGER.info("Customer created");
        return item;
	}
	@Override
	public Item update() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete() {
        LOGGER.info("Please enter the id of the item you would like to delete");
        Long id = javaUtilities.getLong();
        return itemDao.delete(id);
    }

}
