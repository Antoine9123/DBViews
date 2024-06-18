package app.dbviews;

import app.dbviews.views.MainView;
import app.dbviews.views.MenuBarView;
import app.dbviews.views.SidePanelView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.IOException;

public class App extends Application {
    private final String WINDOW_TITLE = "My Database Manager";
    private final Integer WINDOW_WIDTH = 1600;
    private final Integer WINDOW_HEIGHT = 1000;

    @Override
    public void start(Stage stage) throws IOException {

        BorderPane root = new BorderPane();

        MainView mainView = new MainView(10);
        SidePanelView sidePanelView = new SidePanelView();
        MenuBarView menuBarView = new MenuBarView();

        root.setCenter(mainView);
        root.setLeft(sidePanelView);
        root.setTop(menuBarView.getMenuBar());

        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

        stage.setScene(scene);
        stage.setTitle(WINDOW_TITLE);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}