package api;

public interface Mapper<T, Q> {
    public Q map(T elem);
}
