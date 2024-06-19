package app.views.db;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.List;

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
        List<String> databaseList = List.of("Database1", "Database2", "Database3");

        ObservableList<String> items = FXCollections.observableArrayList(databaseList);
        dbComboBox.setItems(items);
    }

    private void openSelectedDatabase() {
        String selectedDatabase = dbComboBox.getSelectionModel().getSelectedItem();
        if (selectedDatabase != null) {
            DatabaseOpenerView openerView = new DatabaseOpenerView(selectedDatabase);
            openerView.showAndWait();
        } else {
            System.err.println("Veuillez sélectionner une base de données.");
        }
    }

}
