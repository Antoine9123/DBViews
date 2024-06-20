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
    exports app.views.db;
    opens app.models;

}