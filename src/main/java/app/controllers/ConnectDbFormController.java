package app.controllers;

import app.models.DatabaseManager;
import app.views.SidePanelView;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectDbFormController {
    @FXML
    private TextField userField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField pathField;

    private Stage formStage;
    private SidePanelView sidePanelView;

    public void setFormStage(Stage formStage) {
        this.formStage = formStage;
    }

    public void setSidePanelView(SidePanelView sidePanelView) {
        this.sidePanelView = sidePanelView;
    }

    @FXML
    private void handleBrowse() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Database File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Database Files", "*.db", "*.mv.db"));
        File selectedFile = fileChooser.showOpenDialog(formStage);
        if (selectedFile != null) {
            pathField.setText(selectedFile.getAbsolutePath());
        }
    }

    @FXML
    private void handleSubmit() {
        String user = userField.getText();
        String password = passwordField.getText();
        String dbPath = pathField.getText();

        DatabaseManager dbManager = new DatabaseManager();
        try {
            Connection connection = dbManager.openDatabase(dbPath, user, password);
            System.out.println("Database connected successfully!");
            sidePanelView.setDatabaseName(new File(dbPath).getName());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        formStage.close();
    }
}
