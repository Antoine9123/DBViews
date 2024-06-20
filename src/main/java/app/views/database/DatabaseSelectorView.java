package app.views.database;

import app.models.ConnectionManager;
import app.services.ConfigLoader;
import app.views.MainWindowView;
import app.views.database.DatabaseCreatorView;
import app.views.database.DatabaseCredentialsView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.List;
import java.util.stream.Collectors;

public class DatabaseSelectorView extends Stage {

    private final ComboBox<String> dbComboBox;

    public DatabaseSelectorView() {
        this.setTitle("Open Database");
        this.initModality(Modality.APPLICATION_MODAL);

        dbComboBox = new ComboBox<>();
        dbComboBox.setPromptText("Select Database");

        Button openButton = new Button("Open");
        openButton.setOnAction(e -> openSelectedDatabase());

        Button newButton = new Button("New");
        newButton.setOnAction(e -> createNewDatabase());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(dbComboBox, openButton, newButton);

        Scene scene = new Scene(layout, 300, 200);
        this.setScene(scene);

        loadDatabases();
    }

    private void loadDatabases() {
        Path dbDirectory = Paths.get(ConfigLoader.getProperty("database.path"));
        try {
            List<String> databaseList = Files.list(dbDirectory)
                    .filter(Files::isRegularFile)
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .filter(fileName -> fileName.endsWith(".mv.db"))
                    .map(fileName -> fileName.replaceAll("\\.mv\\.db$", ""))
                    .collect(Collectors.toList());

            ObservableList<String> items = FXCollections.observableArrayList(databaseList);
            dbComboBox.setItems(items);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openSelectedDatabase() {
        String selectedDatabase = dbComboBox.getSelectionModel().getSelectedItem();
        if (selectedDatabase != null) {
            DatabaseCredentialsView credentialsView = new DatabaseCredentialsView(selectedDatabase);
            credentialsView.showAndWait();
            String user = credentialsView.getUser();
            String password = credentialsView.getPassword();
            if (user != null && password != null) {
                ConnectionManager.getInstance().setCredentials(selectedDatabase, user, password);
                MainWindowView mainWindow = new MainWindowView();
                mainWindow.show();
                this.close();
            } else {
                System.err.println("Please provide database credentials.");
            }
        } else {
            System.err.println("Please select a database");
        }
    }

    private void createNewDatabase() {
        DatabaseCreatorView creatorView = new DatabaseCreatorView();
        creatorView.showAndWait();
        loadDatabases();
    }
}
