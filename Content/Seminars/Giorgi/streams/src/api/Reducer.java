package api;

public interface Reducer<T> {
    public T reduce(T elem, T init);
}
