package shared.filter.reflection;

public interface Expression<T> {
    public T evaluate(Object o);
}
