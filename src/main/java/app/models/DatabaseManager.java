package app.models;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {

    private static ConnectionManager dbConnection;

    public DatabaseManager() {
        dbConnection = ConnectionManager.getInstance();
    }

    public static void createDatabase(String dbName, String user, String password) throws SQLException {
        dbConnection.setCredentials(dbName, user, password);
        try (Connection connection = dbConnection.connect();
             Statement statement = connection.createStatement()) {

            statement.execute("CREATE DATABASE IF NOT EXISTS " + dbName);
        }
        System.out.println("Database created");
    }

    public Connection openDatabase() throws SQLException {
        return dbConnection.connect();
    }
}

