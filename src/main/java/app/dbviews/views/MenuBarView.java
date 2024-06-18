package app.dbviews.views;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class MenuBarView {
    private final MenuBar menuBar;

    public MenuBarView() {
        menuBar = new MenuBar();
        setupMenu();
    }

    private void setupMenu() {

        Menu fileMenu = new Menu("Files");

        MenuItem createDbItem = new MenuItem("Create DB");
        MenuItem openDbItem = new MenuItem("Open DB");

        createDbItem.setOnAction(e -> {
            System.out.println("Create DB clicked");
        });

        openDbItem.setOnAction(e -> {
            System.out.println("Open DB clicked");
        });

        fileMenu.getItems().addAll(createDbItem, openDbItem);

        menuBar.getMenus().add(fileMenu);
    }

    public MenuBar getMenuBar() {
        return menuBar;
    }
}
