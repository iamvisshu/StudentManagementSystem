package com.example.sms.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBConnection {

    private static final Logger logger = LoggerFactory.getLogger(DBConnection.class);
    private static Connection connection = null;
    private static final Object lock = new Object();
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = DBConnection.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                logger.error("Sorry, unable to find application.properties");
            } else {
                properties.load(input);
                // Load the database driver
                Class.forName(properties.getProperty("db.driver"));
            }
        } catch (Exception e) {
            logger.error("Error loading database properties: ", e);
        }
    }

    private static Connection createConnection() throws SQLException {
        String url = properties.getProperty("db.url");
        String username = properties.getProperty("db.username");
        String password = properties.getProperty("db.password");

        return DriverManager.getConnection(url, username, password);
    }

    public static Connection getConnection() {
        synchronized (lock) {
            try {
                if (connection == null || connection.isClosed()) {
                    connection = createConnection();
                    logger.info("Database connection created successfully.");
                }
            } catch (SQLException e) {
                logger.error("Database connection error: ", e);
            }
            return connection;
        }
    }

    public static void closeConnection() {
        synchronized (lock) {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                    logger.info("Database connection closed successfully.");
                }
            } catch (SQLException e) {
                logger.error("Error closing connection: ", e);
            }
        }
    }
}
