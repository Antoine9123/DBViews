package app.views.main;

import app.models.ConnectionManager;
import app.models.DatabaseManager;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.sql.SQLException;
import java.util.List;

public class SidePanelView extends BorderPane {

    private final Label headTitle;
    private final VBox tablesContainer;

    public SidePanelView() {
        String dbName = ConnectionManager.getInstance().getDbName();
        headTitle = new Label(dbName);
        headTitle.setTextFill(Color.WHITE);
        headTitle.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        Rectangle sidePanel = new Rectangle(300, 1000);
        sidePanel.setFill(Color.rgb(47, 48, 48));

        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(20));

        Label tablesTitle = new Label("TABLES");
        tablesTitle.setTextFill(Color.WHITE);
        tablesTitle.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        tablesContainer = new VBox(5);

        vbox.getChildren().addAll(headTitle, tablesTitle, tablesContainer);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(sidePanel, vbox);
        StackPane.setMargin(vbox, new Insets(20.0));

        this.setLeft(stackPane);

        // Load tables initially
        loadTables();
    }

    private void loadTables() {
        try {
            String dbName = ConnectionManager.getInstance().getDbName();
            DatabaseManager dbManager = new DatabaseManager();
            List<String> tables = dbManager.getAllUserTables();
            updateTables(tables);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void updateTables(List<String> tables) {
        tablesContainer.getChildren().clear();

        for (String tableName : tables) {
            Label tableLabel = new Label(tableName);
            tableLabel.setTextFill(Color.WHITE);
            tablesContainer.getChildren().add(tableLabel);
        }
    }
}


