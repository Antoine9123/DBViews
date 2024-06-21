package app.views.panel;

import app.models.ConnectionManager;
import app.models.DatabasesManager;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.sql.SQLException;
import java.util.List;

public class SidePanelView extends BorderPane {
    private final MainView mainView;
    private VBox tablesContainer;
    private VBox titleContainer;

    public SidePanelView(MainView mainView) {
        this.mainView = mainView;
        
        // MainContainer ----------------------------------------------->
        setPadding(new Insets(20));
        setBackground(new Background(new BackgroundFill(Color.rgb(47, 48, 48), null, null)));
        setWidth(500);

        VBox mainContainer = new VBox();
        mainContainer.setSpacing(10);

        // Title container --------------------------------------------------->
        titleContainer = new VBox();
        titleContainer.setPadding(new Insets(10));
        titleContainer.setStyle("-fx-border-color: white; -fx-border-width: 0.5px; -fx-padding: 10px;");

        Label titleLabel = new Label("CURRENT DATABASE");
        titleLabel.setTextFill(Color.WHITE);
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        String dbName = ConnectionManager.getInstance().getDbName();
        Label dbNameLabel = new Label(dbName);
        dbNameLabel.setTextFill(Color.WHITE);
        dbNameLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 20));

        titleContainer.getChildren().addAll(titleLabel, dbNameLabel);

        // Tables Titles ---------------------------------------------------->
        Label tablesTitle = new Label("TABLES");
        tablesTitle.setTextFill(Color.WHITE);
        tablesTitle.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        // TablesContainer --------------------------------------------------->

        tablesContainer = new VBox();
        loadTables();

        // Diplaying --------------------------------------------------------->
        mainContainer.getChildren().addAll(titleContainer, tablesTitle, tablesContainer);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(mainContainer);

        setCenter(stackPane);
    }

    private void loadTables() {
        try {
            DatabasesManager dbManager = new DatabasesManager();
            List<String> tables = dbManager.getAllUserTables();
            updateTablesList(tables);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void updateTablesList(List<String> tables) {
        tablesContainer.getChildren().clear();
        for (String tableName : tables) {
            Label tableLabel = new Label(tableName);
            tableLabel.setTextFill(Color.WHITE);
            tableLabel.setPadding(new Insets(10, 0, 10, 0));

            setHoverEffect(tableLabel);
            tableLabel.setOnMouseClicked(event -> handleTableLabelClick(tableName));

            tablesContainer.getChildren().add(tableLabel);
        }
    }

    private void setHoverEffect(Label label) {
        label.setOnMouseEntered(e -> {
            label.setTextFill(Color.YELLOW); 
        });
        label.setOnMouseExited(e -> {
            label.setTextFill(Color.WHITE);
        });
    }

    private void handleTableLabelClick(String tableName) {
        System.out.println("Clicked on table: " + tableName);
    }
}


