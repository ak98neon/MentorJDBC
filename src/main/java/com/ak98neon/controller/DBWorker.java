package com.ak98neon.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * class DBWorker, pattern Singelton
 */
public class DBWorker {
    private static Connection connection = null;
    private static final String URL = "jdbc:h2:~/test?useSSL=false&serverTimezone = UTC";
    private static final String USER = "sa";
    private static final String PASS = "";

    private DBWorker() {
    }

    /**
     * The method creates a connection if it does not exist.
     * @return Connection
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection(URL, USER, PASS);
            return connection;
        } else {
            return connection;
        }
    }
}