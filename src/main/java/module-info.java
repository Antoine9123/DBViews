module app.dbviews {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens app.dbviews to javafx.fxml;
    exports app.dbviews;
}