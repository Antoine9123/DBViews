package app.views;

import app.controllers.OpenDbFormController;
import app.controllers.NewDbFormController;
import app.views.db.DatabaseCreatorView;
import app.views.db.DatabaseSelectorView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuBarView {
    private final MenuBar menuBar;
    private final SidePanelView sidePanelView;

    public MenuBarView(SidePanelView sidePanelView) {
        this.sidePanelView = sidePanelView;
        menuBar = new MenuBar();
        setupMenu();
    }

    public MenuBar getMenuBar() {
        return menuBar;
    }

    private void setupMenu() {
        Menu fileMenu = new Menu("Files");

        MenuItem createDbItem = new MenuItem("Create DB");
        MenuItem openDbItem = new MenuItem("Open DB");

        createDbItem.setOnAction(e -> openDatabaseCreator());
        openDbItem.setOnAction(e -> openDatabaseSelector());

        fileMenu.getItems().addAll(createDbItem, openDbItem);

        menuBar.getMenus().add(fileMenu);
    }
    private void openDatabaseCreator() {
        DatabaseCreatorView creatorView = new DatabaseCreatorView();
        creatorView.showAndWait();
    }

    private void openDatabaseSelector() {
        DatabaseSelectorView selectorView = new DatabaseSelectorView();
        selectorView.showAndWait();
    }

}
