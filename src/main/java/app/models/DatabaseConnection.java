package app.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String JDBC_URL = "jdbc:h2:";
    private String dbPath;
    private String user;
    private String password;

    public DatabaseConnection(String dbPath, String user, String password) {
        this.dbPath = dbPath;
        this.user = user;
        this.password = password;
    }

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(JDBC_URL + dbPath, user, password);
    }
}