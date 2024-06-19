package app.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private final String url;
    private final String user;
    private final String password;
    private Connection connection;

    public DatabaseConnection(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public void connect() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        }
    }

    public void disconnect() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
            System.out.println("Disconnected from the PostgreSQL server.");
        }
    }

    public Connection getConnection() {
        return connection;
    }
}