package com.qa.ims.cli;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.controller.CustomerController;
import com.qa.ims.controller.ICrudController;
import com.qa.ims.persistence.dao.CustomerDao;
import com.qa.ims.utils.DatabaseUtilities;
import com.qa.ims.utils.JavaUtilities;

public class IMSEntryPoint {

    public static final Logger LOGGER = LogManager.getLogger();

    private final CustomerController customers;
    private final JavaUtilities javaUtilities;

    public IMSEntryPoint() {
        this.javaUtilities = new JavaUtilities();
        final CustomerDao custDAO = new CustomerDao();
        this.customers = new CustomerController(custDAO, javaUtilities);
    }

    public void init() {
        DatabaseUtilities.connect();

        DomainMenu domain = null;
        do {
            LOGGER.info("Which entity would you like to use?");
            DomainMenu.printDomains();

            domain = DomainMenu.getDomain(javaUtilities);

            chooseDomain(domain);

        } while (domain != DomainMenu.STOP);
    }

    private void chooseDomain(DomainMenu domainMenu) {
        boolean changeDomain = false;
        do {

            ICrudController<?> active = null;
            switch (domainMenu) {
            case CUSTOMER:
                active = this.customers;
                break;
            case ITEM:
                // fill this in! this.item
                break;
            case ORDER:
                // fill this in! this.order
                break;
            case STOP:
                return;
            default:
                break;
            }

            LOGGER.info("What would you like to do with " + domainMenu.name().toLowerCase() + ":");

            ActionMenu.printActions();
            ActionMenu action = ActionMenu.getAction(javaUtilities);

            if (action == ActionMenu.RETURN) {
                changeDomain = true;
            } else {
                chooseAction(active, action);
            }
        } while (!changeDomain);
    }

    public void chooseAction(ICrudController<?> crudController, ActionMenu actionMenu) {
        switch (actionMenu) {
        case CREATE:
            crudController.create();
            break;
        case READ:
            crudController.readAll();
            break;
        case UPDATE:
            crudController.update();
            break;
        case DELETE:
            crudController.delete();
            break;
        case RETURN:
            break;
        default:
            break;
        }
    }

}
