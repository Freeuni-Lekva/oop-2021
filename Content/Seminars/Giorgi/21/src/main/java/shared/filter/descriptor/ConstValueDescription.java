package shared.filter.descriptor;

public class ConstValueDescription<T> implements ExpressionDescription {
    private final T value;

    public ConstValueDescription(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}
