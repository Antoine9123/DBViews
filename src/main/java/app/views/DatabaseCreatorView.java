package app.views;

import app.models.DatabaseManager;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.SQLException;

public class DatabaseCreatorView {

    private final Stage stage;
    private final TextField dbNameField;
    private final TextField dbUserField;
    private final TextField dbPasswordField;

    public DatabaseCreatorView() {
        stage = new Stage();
        stage.setTitle("Create Database");
        stage.setWidth(500);
        stage.setHeight(300);
        stage.setResizable(false);

        stage.initModality(Modality.APPLICATION_MODAL);

        Label dbNameLabel = new Label("Database Name:");
        dbNameField = new TextField();

        Label dbUserLabel = new Label("Username:");
        dbUserField = new TextField();

        Label dbPasswordLabel = new Label("Password:");
        dbPasswordField = new TextField();

        Button createButton = new Button("Create");
        createButton.setOnAction(e -> createDatabase());

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(dbNameLabel, dbNameField, dbUserLabel, dbUserField,
                dbPasswordLabel, dbPasswordField, createButton);

        Scene scene = new Scene(layout, 300, 200);
        stage.setScene(scene);
    }

    public void showAndWait() {
        stage.showAndWait();
    }

    private void createDatabase() {
        String dbName = dbNameField.getText();
        String dbUser = dbUserField.getText();
        String dbPassword = dbPasswordField.getText();

        if (!dbName.isEmpty() && !dbUser.isEmpty() && !dbPassword.isEmpty()) {
            try {
                DatabaseManager.createDatabase(dbName, dbUser, dbPassword);
                stage.close();
            } catch (SQLException ex) {
                ex.printStackTrace();

            }
        } else {
            System.err.println("Please fill all fields");

        }
    }
}
