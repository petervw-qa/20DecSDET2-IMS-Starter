package com.qa.ims.cli;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.utils.JavaUtilities;

public enum ActionMenu {
    CREATE("To save a new entity into the database"), READ("To read an entity from the database"),
    UPDATE("To change an entity already in the database"), DELETE("To remove an entity from the database"),
    RETURN("To return to domain selection");

    public static final Logger LOGGER = LogManager.getLogger();

    private String description;

    ActionMenu(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.name() + ": " + this.description;
    }

    public static void printActions() {
        for (ActionMenu actionMenu : ActionMenu.values()) {
            LOGGER.info(actionMenu.getDescription());
        }
    }

    public static ActionMenu getAction(JavaUtilities javaUtilities) {
        ActionMenu actionMenu = null;
        do {
            try {
                actionMenu = ActionMenu.valueOf(javaUtilities.getString().toUpperCase());
            } catch (IllegalArgumentException e) {
                LOGGER.error("Invalid selection please try again");
            }
        } while (actionMenu == null);
        return actionMenu;
    }

}
