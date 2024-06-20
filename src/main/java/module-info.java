module app.dbviews {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens app to javafx.fxml;
    exports app;
    exports app.models;
    exports app.views;
    opens app.models;
    exports app.views.main;
    exports app.views.database;

}