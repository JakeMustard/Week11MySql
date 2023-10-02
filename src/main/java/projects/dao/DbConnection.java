package projects.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import projects.exception.DbException;

public class DbConnection {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/projects";
    private static final String DB_USER = "projects";
    private static final String DB_PASSWORD = "projects";

    static {
        // Load the MySQL JDBC driver during class initialization
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Error loading MySQL JDBC driver.", e);
        }
    }

    public static Connection getConnection() {
        try {
            // Configure the connection properties
            Properties props = new Properties();
            props.setProperty("user", DB_USER);
            props.setProperty("password", DB_PASSWORD);

            return DriverManager.getConnection(JDBC_URL, props);
        } catch (SQLException e) {
            System.err.println("Error connecting to the database: " + e.getMessage());
            throw new DbException("Error connecting to the database.", e);
        }
    }
}
