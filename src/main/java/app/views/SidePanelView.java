package app.views;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class SidePanelView extends BorderPane {
    private final Label headTitle;

    public SidePanelView() {
        headTitle = new Label("No Database.");
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

        vbox.getChildren().addAll(headTitle, tablesTitle);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(sidePanel, vbox);
        StackPane.setMargin(vbox, new Insets(20.0));

        this.setLeft(stackPane);
    }

    public void setHeadTitle(String dbName) {
        if (headTitle != null) {
            headTitle.setText(dbName);
        } else {
            System.err.println("headTitle is null");
        }
    }
}


