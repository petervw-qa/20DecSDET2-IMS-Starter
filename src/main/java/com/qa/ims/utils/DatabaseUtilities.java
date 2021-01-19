package com.qa.ims.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DatabaseUtilities {

    private static final Logger LOGGER = LogManager.getLogger();

    private final String DB_URL;

    private final String DB_USER;

    private final String DB_PASS;

    private DatabaseUtilities(String properties) {
        Properties dbProps = new Properties();
        try (InputStream fis = ClassLoader.getSystemResourceAsStream(properties)) {
            dbProps.load(fis);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        this.DB_URL = dbProps.getProperty("db.url", "");
        this.DB_USER = dbProps.getProperty("db.user", "");
        this.DB_PASS = dbProps.getProperty("db.password", "");
    }

    public DatabaseUtilities() {
        this("db.properties");
    }

    public int init(String... paths) {
        int modified = 0;

        for (String path : paths) {
            modified += executeSQLFile(path);
        }

        return modified;
    }

    public int executeSQLFile(String file) {
        int modified = 0;
        try (Connection connection = this.getConnection();
                BufferedReader br = new BufferedReader(new FileReader(file));) {
            String fileAsString = br.lines().reduce((acc, next) -> acc + next).orElse("");
            String[] queries = fileAsString.split(";");
            modified += Stream.of(queries).map(string -> {
                try (Statement statement = connection.createStatement();) {
                    return statement.executeUpdate(string);
                } catch (Exception e) {
                    LOGGER.debug(e);
                    return 0;
                }
            }).reduce((acc, next) -> acc + next).orElse(0);
        } catch (Exception e) {
            LOGGER.debug(e);
        }
        return modified;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }

    public static DatabaseUtilities instance;

    public static DatabaseUtilities connect() {
        instance = new DatabaseUtilities();
        return instance;
    }

    public static DatabaseUtilities connect(String properties) {
        instance = new DatabaseUtilities(properties);
        return instance;
    }

    public static DatabaseUtilities getInstance() {
        if (instance == null) {
            instance = new DatabaseUtilities();
        }
        return instance;
    }

}
