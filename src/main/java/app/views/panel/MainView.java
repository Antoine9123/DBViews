package app.views.panel;

import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class MainView extends VBox {
    public MainView(double spacing) {
        super(spacing);

        ObservableList components = this.getChildren();

        Label nameLabel = new Label("MAIN VIEW... INCOMING");

        components.addAll(nameLabel);
    }
}