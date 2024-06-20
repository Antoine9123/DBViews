package app;

import app.services.ConfigLoader;
import app.views.MainView;
import app.views.MenuBarView;
import app.views.SidePanelView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.IOException;

public class App extends Application {

    @Override
    public void start(Stage mainStage) throws IOException {
        int WINDOW_WIDTH = 1600;
        int WINDOW_HEIGHT = 1000;
        String WINDOW_TITLE = "My Database Manager";

        BorderPane root = new BorderPane();

        MainView mainView = new MainView(10);
        SidePanelView sidePanelView = new SidePanelView();
        MenuBarView menuBarView = new MenuBarView(sidePanelView);



        Scene mainScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        root.setCenter(mainView);
        root.setLeft(sidePanelView);
        root.setTop(menuBarView.getMenuBar());


        mainStage.setScene(mainScene);
        mainStage.setTitle(WINDOW_TITLE);
        mainStage.setResizable(false);
        mainStage.centerOnScreen();
        mainStage.show();
    }

    public static void main(String[] args)
    {
        launch();
    }
}