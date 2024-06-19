package app.controllers;

import app.models.DatabaseManager;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

public class CreateDbFormController {
    @FXML
    private TextField userField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField dbNameField;
    @FXML
    private TextField pathField;

    private Stage formStage;

    public void setFormStage(Stage formStage) {
        this.formStage = formStage;
    }

    @FXML
    private void handleBrowse() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select Installation Directory");
        File selectedDirectory = directoryChooser.showDialog(formStage);
        if (selectedDirectory != null) {
            pathField.setText(selectedDirectory.getAbsolutePath());
        }
    }

    @FXML
    private void handleSubmit() {
        String user = userField.getText();
        String password = passwordField.getText();
        String dbName = dbNameField.getText();
        String path = pathField.getText();
        String dbPath = path + "/" + dbName;

        DatabaseManager dbManager = new DatabaseManager();
        try {
            dbManager.createDatabase(dbPath, user, password);
            Connection connection = dbManager.openDatabase(dbPath, user, password);
            System.out.println("Database created and connected successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        formStage.close();
    }
}

