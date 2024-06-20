package app.views.waiting;

;

import app.views.DatabaseCreatorView;
import app.views.DatabaseSelectorView;
import app.views.waiting.SidePanelView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;


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
