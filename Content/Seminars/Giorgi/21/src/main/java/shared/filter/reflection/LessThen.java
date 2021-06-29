package shared.filter.reflection;

import java.util.Comparator;

public class LessThen<T> extends BinaryOperator<Boolean, T> implements Filter {
    private final Comparator<T> cmp;

    public LessThen(Expression<T> left, Expression<T> right, Comparator<T> cmp) {
        super(left, right);
        this.cmp = cmp;
    }

    @Override
    public Boolean evaluate(Object o) {
        return cmp.compare(left.evaluate(o), right.evaluate(o)) < 0;
    }
}
