package shared.filter.reflection;

public class Equals<T> extends BinaryOperator<Boolean, T> implements Filter {
    public Equals(Expression<T> left, Expression<T> right) {
        super(left, right);
    }

    @Override
    public Boolean evaluate(Object o) {
        return left.evaluate(o).equals(right.evaluate(o));
    }
}
