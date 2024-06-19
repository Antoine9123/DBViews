package app.controllers;

import app.models.DatabaseManager;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.sql.Connection;
import java.sql.SQLException;

public class NewDbFormController {
    private Stage createFormStage;


    @FXML
    private TextField userField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField dbNameField;


    public void setCreateFormStage(Stage createFormStage) {
        this.createFormStage = createFormStage;
    }


    @FXML
    private void handleSubmit() {
        String user = userField.getText();
        String password = passwordField.getText();
        String dbName = dbNameField.getText();

        String dbPath = "./src/main/resources/app/db/" + dbName;


        DatabaseManager dbManager = new DatabaseManager();
        try {
            dbManager.createDatabase(dbPath, user, password);
            Connection connection = dbManager.openDatabase(dbPath, user, password);
            System.out.println("Database created and connected successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        createFormStage.close();
    }
}

