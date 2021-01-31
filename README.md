Coverage: 70.1%
# Fundamental Project: Inventory Management Position

This is an inventory management system for an end user to use via a command line interface (CLI); it allows for multiple functions including creation of customers, storing items to orders, adding and removing specific items based on the item's identification number and many other functions.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

In order to use this application, you will need:
```

- Java Version 11 or higher.
- MySQL Version 8 or higher.
- Git
- Maven

```


### Installing

You need to have the above installed.

Once you have the ims-0.0.1-jar-with-dependencies.jar file downloaded onto your system, you can execute it through your command line (cmd) or any terminal that can run executables (like Windows PowerShell for example) by running the following command:

java -jar ims-0.0.1-jar-with-dependencies.jar

## Running the tests

In order to run the tests on your own system, you must either fork and clone the repository down to your own local space or clone it using the git clone command and the root URL for this repository: 

git clone https://github.com/petervw-qa/20DecSDET2-IMS-Starter

### Unit Tests 

These are the tests that I created in order to test and figure out how the classes in the packages.domain package were interacting with one another, their getters and setters and how the create, update, read and delete functions worked in each data access object.

Examples of testing the item class:

```

@Test
	public void firstConstructorTEST() {
		Item item = new Item("pencil", 100);
		assertEquals("pencil", item.getName());
		assertEquals(100.0, item.getPrice(), 0.1);
}
  
@Test
	public void setIdTEST() {
		Item item = new Item(1L, "super rubber", 100);
		item.setId(2L);
		assertEquals(Long.valueOf("2"), item.getId());
}
	
@Test
public void setNameTEST() {
	Item item = new Item(1L, "super rubber", 100);
	item.setName("kryptonite");
	assertEquals("kryptonite", item.getName());	
}

```

Examples of testing the Item Data Access Object class:

```

@Test
	public void removeOrdersItemsTEST() {
	assertEquals(0, DAO.removeOrdersItems(new Item(1L, "rubber", 1.50), 1L));
}

@Test
  public void readLatestTEST() {
	assertEquals(new Item(1L, "rubber", 1.50D), DAO.readLatest());
}

@Test
	public void updateTEST() {
    final Item updated = new Item(1L, "rubber", 1.50D);
    assertEquals(updated, DAO.update(updated));
}

```
  


## Deployment

If you want to deploy this project with an actual database, you will need to have your MySQL database working and currently running. 

If you have changed your username and password from admin and root respectively, you will either need to use your bespoke details or reinstall to reset the username and password. When you have then logged in, run mySQL on your cmd and set up your databases and tables using the following commands tailored to this project:

```

drop schema ims;

CREATE SCHEMA IF NOT EXISTS `ims`;

USE `ims` ;

CREATE TABLE IF NOT EXISTS `ims`.`customers` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) DEFAULT NULL,
    `surname` VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `ims` . `items` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(50) NOT NULL,
	`price` DOUBLE NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `ims` . `orders` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`fk_customers_id` INT NOT NULL,
	`price` DOUBLE NOT NULL,
	PRIMARY KEY (`id`),
	FOREIGN KEY (`fk_customers_id`) REFERENCES customers(`id`)
);

CREATE TABLE IF NOT EXISTS `ims` . `orders_items` (
  `fk_orders_id` INT NOT NULL,
	`fk_items_id` INT NOT NULL,
	FOREIGN KEY (`fk_orders_id`) REFERENCES orders(`id`),
	FOREIGN KEY (`fk_items_id`) REFERENCES items(`id`)
);

```

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

We use [SemVer](http://semver.org/) for versioning.

## Authors

* **Chris Perrins** - *Initial work* - [christophperrins](https://github.com/christophperrins)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

## Acknowledgments

* Thank you to Nick (https://github.com/nickrstewarttds) for all the help I had received during the creation of this project, would not have been possible without him.
* A large thank you to QA's Team Serpent for all of the motivational and technical help during the development of this IMS (big love to Gie, Peprah, Nick and Emmy as well as everyone else who gave me a helping hand!)
