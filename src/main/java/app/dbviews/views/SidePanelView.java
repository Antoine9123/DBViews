package app.dbviews.views;

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
    public SidePanelView() {
        // Create the rectangle to act as the background for the side panel
        Rectangle sidePanel = new Rectangle(300.0, 1000.0);
        sidePanel.setFill(Color.rgb(47, 48, 48));

        // Create a VBox to hold the labels
        VBox vbox = new VBox();
        vbox.setSpacing(10.0);
        vbox.setPadding(new Insets(20.0));

        // Create and configure the "MY DATABASE" label
        Label headTitle = new Label("MY DATABASE");
        headTitle.setTextFill(Color.WHITE);
        headTitle.setFont(Font.font("Arial", FontWeight.BOLD, 24.0));

        // Create and configure the "TABLES" label
        Label tablesTitle = new Label("TABLES");
        tablesTitle.setTextFill(Color.WHITE);
        tablesTitle.setFont(Font.font("Arial", FontWeight.BOLD, 24.0));

        // Add the labels to the VBox
        vbox.getChildren().addAll(headTitle, tablesTitle);

        // Create a StackPane to hold the rectangle and the VBox
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(sidePanel, vbox);
        StackPane.setMargin(vbox, new Insets(20.0));

        // Add the StackPane to the left of the BorderPane
        this.setLeft(stackPane);
    }
}

