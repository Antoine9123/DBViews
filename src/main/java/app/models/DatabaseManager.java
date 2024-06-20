package app.models;

import app.services.ConfigLoader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class DatabaseManager {

    public void createDatabase(String dbName, String user, String password) throws SQLException {
        String dbPath = ConfigLoader.getProperty("database.path") + dbName;

        try (Connection connection = DriverManager.getConnection("jdbc:h2:" + dbPath, user, password);
             Statement statement = connection.createStatement()) {

            statement.execute("CREATE TABLE IF NOT EXISTS example_table (id INT PRIMARY KEY, name VARCHAR(255))");
        }
        System.out.println("Database created");
    }

    public Connection openDatabase(String dbName, String user, String password) throws SQLException {
        String dbPath = ConfigLoader.getProperty("database.path") + dbName;
        return DriverManager.getConnection("jdbc:h2:" + dbPath, user, password);
    }
}
