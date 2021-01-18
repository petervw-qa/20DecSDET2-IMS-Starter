package com.qa.ims;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.cli.IMSEntryPoint;

public class Runner {

    public static final Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {
        LOGGER.info("Welcome to the Inventory Management System.");
        IMSEntryPoint ims = new IMSEntryPoint();
        ims.init();
        LOGGER.info("Exited the Inventory Management System.");
    }

}
