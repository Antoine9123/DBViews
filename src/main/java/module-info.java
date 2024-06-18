module app.dbviews {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens app.dbviews to javafx.fxml;
    exports app.dbviews;
    exports app.dbviews.database;
}