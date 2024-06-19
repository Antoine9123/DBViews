package app.views;

import app.controllers.OpenDbFormController;
import app.controllers.NewDbFormController;
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

        createDbItem.setOnAction(e -> openCreateDbForm());
        openDbItem.setOnAction(e -> openConnectDbForm());

        fileMenu.getItems().addAll(createDbItem, openDbItem);

        menuBar.getMenus().add(fileMenu);
    }

    private void openCreateDbForm() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/views/CreateDbForm.fxml"));
            Parent root = loader.load();

            NewDbFormController controller = loader.getController();
            Stage formStage = new Stage();
            controller.setCreateFormStage(formStage);

            Scene scene = new Scene(root);
            formStage.setScene(scene);
            formStage.setTitle("Create New Database");
            formStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openConnectDbForm() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/views/ConnectDbForm.fxml"));
            Parent root = loader.load();

            OpenDbFormController controller = loader.getController();
            Stage formStage = new Stage();
            controller.setFormStage(formStage);
            controller.setSidePanelView(sidePanelView);

            Scene scene = new Scene(root);
            formStage.setScene(scene);
            formStage.setTitle("Connect to Database");
            formStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
