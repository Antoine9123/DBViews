package app.models;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {

    private final ConnectionManager dbConnection;

    public DatabaseManager() {
        this.dbConnection = ConnectionManager.getInstance();
    }

    public void createDatabase(String dbName, String user, String password) throws SQLException {
        dbConnection.setCredentials(dbName, user, password);
        try (Connection connection = dbConnection.connect();
             Statement statement = connection.createStatement()) {

            System.out.println("Connected to database: " + dbName);
        }
    }
}


