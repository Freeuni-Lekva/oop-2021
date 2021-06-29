package shared.filter.reflection;

public class ConstValue<T> implements Expression<T> {
    private final T value;

    public ConstValue(T value) {
        this.value = value;
    }

    @Override
    public T evaluate(Object o) {
        return value;
    }
}
