package shared.filter.reflection;

public abstract class BinaryOperator<R, T> implements Expression<R> {
    protected final Expression<T> left;
    protected final Expression<T> right;

    public BinaryOperator(Expression<T> left, Expression<T> right) {
        this.left = left;
        this.right = right;
    }
}
