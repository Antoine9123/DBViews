package app;

import app.models.ConnectionManager;
import javafx.application.Application;
import javafx.stage.Stage;
import app.views.database.DatabaseSelectorView;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Initialize the ConnectionManager instance
        ConnectionManager.getInstance();

        // Launch the DatabaseSelectorView
        DatabaseSelectorView databaseSelectorView = new DatabaseSelectorView();
        databaseSelectorView.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}