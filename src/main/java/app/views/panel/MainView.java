package app.views.panel;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

import java.sql.*;

public class MainView extends VBox {
    public MainView(double spacing) {
        super(spacing);

        Label nameLabel = new Label("MAIN VIEW... INCOMING");
        getChildren().add(nameLabel);
    }

    /*public void loadTableData(String tableName, ObservableList<ObservableList<String>> tableData) {
        clearContent();

        nameLabel.setText("Table: " + tableName);

        tableView = new TableView<>();
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Adding columns dynamically based on table metadata
        try {
            Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
            Statement stmt = conn.createStatement();

            // Get columns metadata
            ResultSet rsColumns = stmt.executeQuery("SELECT * FROM " + tableName + " WHERE 1 = 0");
            ResultSetMetaData rsmd = rsColumns.getMetaData();
            int columnCount = rsmd.getColumnCount();

            for (int i = 1; i <= columnCount; i++) {
                final int j = i - 1;
                TableColumn<ObservableList<String>, String> column = new TableColumn<>(rsmd.getColumnName(i));
                column.setCellValueFactory(param -> {
                    ObservableList<String> row = param.getValue();
                    return new SimpleStringProperty(row.get(j));
                });
                tableView.getColumns().add(column);
            }

            // Fetch data rows
            ResultSet rsData = stmt.executeQuery("SELECT * FROM " + tableName);
            while (rsData.next()) {
                ObservableList<String> rowData = FXCollections.observableArrayList();
                for (int i = 1; i <= columnCount; i++) {
                    rowData.add(rsData.getString(i));
                }
                tableData.add(rowData);
            }

            tableView.setItems(tableData);

            rsColumns.close();
            rsData.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        getChildren().add(tableView);
}*/}