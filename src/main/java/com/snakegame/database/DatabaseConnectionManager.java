package com.snakegame.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionManager {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/se";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "psw";

    private static final DatabaseConnectionManager instance = new DatabaseConnectionManager();

    private DatabaseConnectionManager() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load MySQL JDBC driver.");
        }
    }

    public static DatabaseConnectionManager getInstance() {
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }
}