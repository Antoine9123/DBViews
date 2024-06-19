package app.views;

import app.models.DatabaseManager;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

public class MenuBarView {
    private final MenuBar menuBar;

    public MenuBarView() {
        menuBar = new MenuBar();
        setupMenu();
    }

    public MenuBar getMenuBar() {
        return menuBar;
    }

    private void setupMenu() {

        Menu fileMenu = new Menu("Files");

        MenuItem createDbItem = new MenuItem("Create DB");
        MenuItem openDbItem = new MenuItem("Open DB");

        createDbItem.setOnAction(e -> this.openCreateDbForm());

        openDbItem.setOnAction(e -> {
            System.out.println("Open DB clicked");
        });

        fileMenu.getItems().addAll(createDbItem, openDbItem);

        menuBar.getMenus().add(fileMenu);
    }



    private void openCreateDbForm() {
        Stage formStage = new Stage();
        formStage.setTitle("Create New Database");
        formStage.setResizable(false);
        formStage.setWidth(500);
        formStage.setHeight(300);

        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setPadding(new Insets(20));

        Label userLabel = new Label("User:");
        TextField userField = new TextField();

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();

        Label dbNameLabel = new Label("Database Name:");
        TextField dbNameField = new TextField();

        Label pathLabel = new Label("Path:");
        TextField pathField = new TextField();
        pathField.setEditable(false);

        Button browseButton = new Button("Browse");
        browseButton.setOnAction(e -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("Select Installation Directory");
            File selectedDirectory = directoryChooser.showDialog(formStage);
            if (selectedDirectory != null) {
                pathField.setText(selectedDirectory.getAbsolutePath());
            }
        });

        Button submitButton = new Button("Submit");
        submitButton.setOnAction(e -> {
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
                // Here you can proceed with further operations on the database
            } catch (SQLException ex) {
                ex.printStackTrace();
                // Handle exceptions appropriately in a real application
            }

            formStage.close();
        });

        gridPane.add(userLabel, 0, 0);
        gridPane.add(userField, 1, 0);
        gridPane.add(passwordLabel, 0, 1);
        gridPane.add(passwordField, 1, 1);
        gridPane.add(dbNameLabel, 0, 2);
        gridPane.add(dbNameField, 1, 2);
        gridPane.add(pathLabel, 0, 3);
        gridPane.add(pathField, 1, 3);
        gridPane.add(browseButton, 2, 3);
        gridPane.add(submitButton, 1, 4);

        Scene formScene = new Scene(gridPane, 550, 350);
        formStage.setScene(formScene);
        formStage.show();
    }
}
