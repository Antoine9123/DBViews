package app.views.db;

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

public class DatabaseSelectorView {

    private final Stage stage;
    private final ComboBox<String> dbComboBox;

    public DatabaseSelectorView() {
        stage = new Stage();
        stage.setTitle("Open Database");
        stage.initModality(Modality.APPLICATION_MODAL);

        dbComboBox = new ComboBox<>();
        dbComboBox.setPromptText("Select Database");

        Button openButton = new Button("Open");
        openButton.setOnAction(e -> openSelectedDatabase());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(dbComboBox, openButton);

        Scene scene = new Scene(layout, 300, 200);
        stage.setScene(scene);

        loadDatabases();
    }

    public void showAndWait() {
        stage.showAndWait();
    }

    private void loadDatabases() {
        Path dbDirectory = Paths.get("src/main/resources/app/db");
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
        System.out.println(selectedDatabase);
        if (selectedDatabase != null) {
            DatabaseOpenerView openerView = new DatabaseOpenerView(selectedDatabase);
            openerView.showAndWait();
            stage.close();
        } else {
            System.err.println("Veuillez sélectionner une base de données.");
        }
    }

}
