package app.models.tables;

import java.util.ArrayList;
import java.util.List;

public class TableStructure {
    private List<Field> fields;

    public TableStructure() {
        this.fields = new ArrayList<>();
    }

    public void addField(String fieldName, String fieldType) {
        fields.add(new Field(fieldName, fieldType));
    }

    public List<Field> getFields() {
        return fields;
    }

}

