package app.models.tables;

public record Field(String fieldName, String fieldType) {

    @Override
    public String toString() {
        return fieldName + " " + fieldType;
    }
}

