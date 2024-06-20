package app.views.database;

import app.models.DatabaseManager;
import app.views.MainWindowView;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.SQLException;

public class DatabaseCreatorView extends Stage {

    private final TextField dbNameField;
    private final TextField dbUserField;
    private final TextField dbPasswordField;

    public DatabaseCreatorView() {
        this.setTitle("Create Database");
        this.setWidth(500);
        this.setHeight(300);
        this.setResizable(false);
        this.initModality(Modality.APPLICATION_MODAL);

        Label dbNameLabel = new Label("Database Name:");
        dbNameField = new TextField();

        Label dbUserLabel = new Label("Username:");
        dbUserField = new TextField();

        Label dbPasswordLabel = new Label("Password:");
        dbPasswordField = new PasswordField();

        Button createButton = new Button("Create");
        createButton.setOnAction(e -> createDatabase());

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(dbNameLabel, dbNameField, dbUserLabel, dbUserField,
                dbPasswordLabel, dbPasswordField, createButton);

        Scene scene = new Scene(layout, 300, 200);
        this.setScene(scene);
    }

    public void showAndWait() {
        super.showAndWait();
    }

    private void createDatabase() {
        String dbName = dbNameField.getText();
        String dbUser = dbUserField.getText();
        String dbPassword = dbPasswordField.getText();

        if (!dbName.isEmpty() && !dbUser.isEmpty() && !dbPassword.isEmpty()) {
            try {
                DatabaseManager dbManager = new DatabaseManager();
                dbManager.createDatabase(dbName, dbUser, dbPassword);

                Stage currentStage = (Stage) this.getScene().getWindow();
                currentStage.close();

                Platform.runLater(() -> {
                    MainWindowView mainWindowView = new MainWindowView();
                    mainWindowView.show();
                });
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else {
            System.err.println("Please fill all fields");
        }
    }
}

