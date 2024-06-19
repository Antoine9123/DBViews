package app.views.db;

import app.models.DatabaseManager;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseOpenerView {

    private final Stage stage;
    private final String selectedDatabase;

    public DatabaseOpenerView(String selectedDatabase) {
        this.selectedDatabase = selectedDatabase;

        stage = new Stage();
        stage.setTitle("Open Database");
        stage.initModality(Modality.APPLICATION_MODAL);

        TextField userField = new TextField();
        userField.setPromptText("Username");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        Button openButton = new Button("Open");
        openButton.setOnAction(e -> openDatabase(userField.getText(), passwordField.getText()));

        VBox layout = new VBox(10);
        layout.getChildren().addAll(userField, passwordField, openButton);

        Scene scene = new Scene(layout, 300, 200);
        stage.setScene(scene);
    }

    public void showAndWait() {
        stage.showAndWait();
    }

    private void openDatabase(String user, String password) {
        try {
            DatabaseManager dbManager = new DatabaseManager();
            Connection connection = dbManager.openDatabase(selectedDatabase, user, password);
            if (connection != null) {
                System.out.println("Database opened successfully!");
                stage.close();
            } else {
                System.err.println("Failed to open database.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
