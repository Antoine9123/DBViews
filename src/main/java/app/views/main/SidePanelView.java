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

        setPadding(new Insets(20));



        VBox leftPanel = new VBox();
        leftPanel.setSpacing(10);


        String dbName = ConnectionManager.getInstance().getDbName();
        headTitle = new Label("DATABASE NAME:\n" + dbName);
        headTitle.setTextFill(Color.WHITE);
        headTitle.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        headTitle.setStyle("-fx-border-color: white; -fx-border-width: 1px; -fx-padding: 10px;");

        Label tablesTitle = new Label("TABLES");
        tablesTitle.setTextFill(Color.WHITE);
        tablesTitle.setFont(Font.font("Arial", FontWeight.BOLD, 24));


        tablesContainer = new VBox();



        leftPanel.getChildren().addAll(headTitle, tablesTitle, tablesContainer);


        Rectangle sidePanelBackground = new Rectangle(400, 1000);
        sidePanelBackground.setFill(Color.rgb(47, 48, 48));


        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(sidePanelBackground, leftPanel);

        setLeft(stackPane);

        loadTables();
    }

    public void updateTablesList(List<String> tables) {
        tablesContainer.getChildren().clear();
        for (String tableName : tables) {
            Label tableLabel = new Label(tableName);
            tableLabel.setTextFill(Color.WHITE);
            tablesContainer.getChildren().add(tableLabel);
        }
    }


    private void loadTables() {
        try {
            DatabaseManager dbManager = new DatabaseManager();
            List<String> tables = dbManager.getAllUserTables();
            updateTablesList(tables);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}


