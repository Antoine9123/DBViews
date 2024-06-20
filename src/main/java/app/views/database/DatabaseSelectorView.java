package app.views.database;

import app.models.ConnectionManager;
import app.services.ConfigLoader;
import app.views.MainWindowView;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.List;
import java.util.stream.Collectors;

public class DatabaseSelectorView extends Stage {

    private final ListView<String> dbListView;

    public DatabaseSelectorView() {
        this.setTitle("Open Database");
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);

        dbListView = new ListView<>();
        dbListView.setPrefHeight(200);
        dbListView.setPlaceholder(new Text("No databases found"));

        Label title = new Label("Select a database :");

        Button openButton = new Button("Open");
        openButton.setOnAction(e -> openSelectedDatabase());
        openButton.setDisable(true);

        Button newButton = new Button("New");
        newButton.setOnAction(e -> createNewDatabase());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(title, dbListView, openButton, newButton);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 400, 300);


        this.setScene(scene);

        showDatabases();

        openButton.disableProperty().bind(Bindings.isEmpty(dbListView.getSelectionModel().getSelectedItems()));
    }

    private void showDatabases() {
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
            dbListView.setItems(items);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openSelectedDatabase() {
        String selectedDatabase = dbListView.getSelectionModel().getSelectedItem();
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
        showDatabases();
    }
}
