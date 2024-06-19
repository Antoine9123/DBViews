package app.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class DatabaseManager {

    public void createDatabase(String dbName, String user, String password) throws SQLException {
        String dbPath = "./src/main/resources/app/db/" + dbName;

        try (Connection connection = DriverManager.getConnection("jdbc:h2:" + dbPath, user, password);
             Statement statement = connection.createStatement()) {

            statement.execute("CREATE TABLE IF NOT EXISTS example_table (id INT PRIMARY KEY, name VARCHAR(255))");
        }
        System.out.println("Database created");
    }

    public Connection openDatabase(String dbPath, String user, String password) throws SQLException {
        return DriverManager.getConnection("jdbc:h2:" + dbPath, user, password);
    }
}
