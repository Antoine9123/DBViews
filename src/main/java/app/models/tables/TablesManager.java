package app.models.tables;

import app.models.ConnectionManager;

import java.sql.*;

public class TablesManager {
    private final String tableName;
    private final ConnectionManager dbConnection;

    public TablesManager(String tableName) {
        this.dbConnection = ConnectionManager.getInstance();
        this.tableName = tableName;
    }

    public void createTable(TableStructure tableStructure) {
        StringBuilder sql = new StringBuilder("CREATE TABLE " + tableName + " (id SERIAL PRIMARY KEY");
        for (Field field : tableStructure.getFields()) {
            sql.append(", ").append(field);
        }
        sql.append(")");

        try (Connection conn = dbConnection.connect(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql.toString());
            System.out.println("Table " + tableName + " created successfully.");
        } catch (SQLException e) {
            System.err.println("Error creating table " + tableName + ": " + e.getMessage());
        }
    }

    public void renameTable(String newName) {
        String sql = "ALTER TABLE " + tableName + " RENAME TO " + newName;
        try (Connection conn = dbConnection.connect(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table " + tableName + " renamed to " + newName + " successfully.");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void deleteTable() {
        String sql = "DROP TABLE " + tableName;
        try (Connection conn = dbConnection.connect(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table " + tableName + " deleted successfully.");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
