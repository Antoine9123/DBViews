package app.views.database;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class DatabaseCredentialsView extends Stage {

    private final TextField userField;
    private final PasswordField passwordField;
    private String user;
    private String password;

    public DatabaseCredentialsView(String dbName) {
        this.setTitle("Logging for " + dbName);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);

        Label userLabel = new Label("Username:");
        Label passwordLabel = new Label("Password:");

        userField = new TextField();
        userField.setPromptText("Enter your username");

        passwordField = new PasswordField();
        passwordField.setPromptText("Enter your password");

        Button submitButton = new Button("Logging");
        submitButton.setOnAction(e -> submitCredentials());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(userLabel, userField, passwordLabel, passwordField, submitButton);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 400, 200);
        this.setScene(scene);
    }

    public void showAndWait() {
        super.showAndWait();
    }

    private void submitCredentials() {
        user = userField.getText();
        password = passwordField.getText();
        this.close();
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}

