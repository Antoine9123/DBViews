package app.dbviews.views;

import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class MainView extends VBox {
    public MainView(double spacing) {
        super(spacing);
        ObservableList components = this.getChildren();
        Label nameLabel = new Label("Name :");
        TextField name = new TextField();
        Button click = new Button("Click here");
        components.addAll(nameLabel, name, click);
    }
}