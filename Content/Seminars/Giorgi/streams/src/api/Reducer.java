package api;

public interface Reducer<T, O> {
    public O reduce(T elem, O init);
}
