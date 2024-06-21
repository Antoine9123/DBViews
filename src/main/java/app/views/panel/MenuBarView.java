package app.views.panel;

import app.views.database.DatabaseCreatorView;
import app.views.database.DatabaseSelectorView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;


public class MenuBarView extends MenuBar {

    private final SidePanelView sidePanelView;

    public MenuBarView(SidePanelView sidePanelView) {
        this.sidePanelView = sidePanelView;
        setupMenu();
    }

    private void setupMenu() {
        Menu fileMenu = new Menu("Files");

        MenuItem createDbItem = new MenuItem("Create DB");
        MenuItem openDbItem = new MenuItem("Open DB");

        createDbItem.setOnAction(e -> openDatabaseCreator());
        openDbItem.setOnAction(e -> openDatabaseSelector());

        fileMenu.getItems().addAll(createDbItem, openDbItem);

        getMenus().add(fileMenu);
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
