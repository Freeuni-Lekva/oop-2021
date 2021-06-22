package api;

public interface Filter<T> {
    public boolean filter(T elem);
}
