package com.project.db;

import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@Slf4j
@Log
public class DBUtil {
    private static final String DB_DRIVER_CLASS = "driver.class.name";
    private static final String DB_USERNAME = "db.username";
    private static final String DB_PASSWORD = "db.password";
    private static final String DB_URL = "db.url";
    private static Connection connection = null;

    private DBUtil() {
    }

    static {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("src/main/resources/database.properties"));
            Class.forName(properties.getProperty(DB_DRIVER_CLASS));
            connection = DriverManager.getConnection(properties.getProperty(DB_URL), properties.getProperty(DB_USERNAME),
                    properties.getProperty(DB_PASSWORD));
        } catch (ClassNotFoundException | IOException | SQLException e) {
            log.severe(e.getMessage());
        }
    }


    public static Connection getConnection() {
        return connection;
    }
}
