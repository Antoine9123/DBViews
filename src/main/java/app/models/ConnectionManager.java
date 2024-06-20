package app.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private static final String JDBC_URL = "jdbc:h2:";
    private static ConnectionManager instance;
    private String dbName;
    private String user;
    private String password;
    private Connection connection;

    private ConnectionManager() {
    }

    public static synchronized ConnectionManager getInstance() {
        if (instance == null) {
            instance = new ConnectionManager();
        }
        return instance;
    }

    public void setCredentials(String dbName, String user, String password) {
        this.dbName = dbName;
        this.user = user;
        this.password = password;
        this.connection = null;
    }

    public Connection connect() throws SQLException {
        if (connection == null || connection.isClosed()) {
            if (dbName == null || user == null || password == null) {
                throw new IllegalStateException("Database credentials have not been set.");
            }
            connection = DriverManager.getConnection(JDBC_URL + dbName, user, password);
        }
        return connection;
    }
}



