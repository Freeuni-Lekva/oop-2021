package shared.filter.descriptor;

public class FieldValueDescription<T> implements ExpressionDescription<T> {
    private final String fieldName;

    public FieldValueDescription(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}
