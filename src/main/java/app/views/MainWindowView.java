package app.views;

import app.views.panel.MainView;
import app.views.panel.MenuBarView;
import app.views.panel.SidePanelView;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainWindowView extends Stage {

    public MainWindowView() {
        int WINDOW_WIDTH = 1600;
        int WINDOW_HEIGHT = 1000;
        String WINDOW_TITLE = "My Database Manager";

        BorderPane root = new BorderPane();

        MainView mainView = new MainView(10);
        SidePanelView sidePanelView = new SidePanelView(mainView);
        MenuBarView menuBarView = new MenuBarView(sidePanelView);

        Scene mainScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        root.setCenter(mainView);
        root.setLeft(sidePanelView);
        root.setTop(menuBarView);

        this.setScene(mainScene);
        this.setTitle(WINDOW_TITLE);
        this.setResizable(false);
        this.centerOnScreen();
    }}
