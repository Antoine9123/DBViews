package app.models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {

    private final ConnectionManager dbConnection;

    public DatabaseManager() {
        this.dbConnection = ConnectionManager.getInstance();
    }

    public void createDatabase(String dbName, String user, String password) throws SQLException {
        dbConnection.setCredentials(dbName, user, password);
        try (Connection connection = dbConnection.connect();
             Statement statement = connection.createStatement()) {
            createExampleTable1(statement);
            createExampleTable2(statement);
            createExampleTable3(statement);

            System.out.println("Connected to database: " + dbName);
        }
    }

    public List<String> getAllUserTables() throws SQLException {
        List<String> tables = new ArrayList<>();
        try (Connection connection = dbConnection.connect()) {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet resultSet = metaData.getTables(null, null, "%", new String[]{"TABLE"});


            int count = 0;
            while (resultSet.next()) {
                String tableCat = resultSet.getString("REF_GENERATION");
                // This isn't best practice but skip all the default tables
                if (count < 15) {
                    count++;
                    continue;
                }
                String tableName = resultSet.getString("TABLE_NAME");
                tables.add(tableName);
            }
        }
        if (tables.isEmpty()){
            return null;
        }
        else return tables;

    }

    private void createExampleTable1(Statement statement) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS example_table1 (" +
                "id INT PRIMARY KEY AUTO_INCREMENT," +
                "name VARCHAR(255) NOT NULL," +
                "age INT" +
                ")";
        statement.execute(sql);
        System.out.println("Created table: example_table1");
    }

    private void createExampleTable2(Statement statement) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS example_table2 (" +
                "id INT PRIMARY KEY AUTO_INCREMENT," +
                "address VARCHAR(255) NOT NULL," +
                "phone VARCHAR(20)" +
                ")";
        statement.execute(sql);
        System.out.println("Created table: example_table2");
    }

    private void createExampleTable3(Statement statement) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS example_table3 (" +
                "id INT PRIMARY KEY AUTO_INCREMENT," +
                "email VARCHAR(255) NOT NULL," +
                "status BOOLEAN DEFAULT true" +
                ")";
        statement.execute(sql);
        System.out.println("Created table: example_table3");
    }
}



