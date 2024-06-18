package app.dbviews.database;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DatabaseManager {
    private final DatabaseConnection dbConnection;
    private final ExecutorService executor;

    public DatabaseManager(DatabaseConnection dbConnection) {
        this.dbConnection = dbConnection;
        this.executor = Executors.newCachedThreadPool();
    }

    public void createDatabase(String dbName) {
        executor.submit(() -> {
            try (Connection conn = dbConnection.getConnection();
                 Statement stmt = conn.createStatement()) {
                String sql = "CREATE DATABASE " + dbName;
                stmt.execute(sql);
                System.out.println("Database created successfully.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public void executeSqlFile(String filePath) {
        executor.submit(() -> {
            try (Connection conn = dbConnection.getConnection();
                 Statement stmt = conn.createStatement()) {
                String sql = new String(Files.readAllBytes(Paths.get(filePath)));
                stmt.execute(sql);
                System.out.println("Executed SQL file successfully.");
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void exportDatabaseToSql(String dbName, String filePath) {
        executor.submit(() -> {

            System.out.println("Exporting database to SQL file is not implemented yet.");
        });
    }

    public void shutdown() {
        executor.shutdown();
    }
}
