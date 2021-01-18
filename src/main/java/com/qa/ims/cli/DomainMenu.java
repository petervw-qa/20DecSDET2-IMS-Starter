package com.qa.ims.cli;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.utils.JavaUtilities;

public enum DomainMenu {

	CUSTOMER("Information about customers"), ITEM("Individual Items"), ORDER("Purchases of items"),
	STOP("To close the application");

	public static final Logger LOGGER = LogManager.getLogger();

	private String description;

	private DomainMenu(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.name() + ": " + this.description;
	}

	public static void printDomains() {
		for (DomainMenu domainMenu : DomainMenu.values()) {
			LOGGER.info(domainMenu.getDescription());
		}
	}

	public static DomainMenu getDomain(JavaUtilities javaUtilities) {
		DomainMenu domainMenu;
		while (true) {
			try {
				domainMenu = DomainMenu.valueOf(javaUtilities.getString().toUpperCase());
				break;
			} catch (IllegalArgumentException e) {
				LOGGER.error("Invalid selection please try again");
			}
		}
		return domainMenu;
	}

}
