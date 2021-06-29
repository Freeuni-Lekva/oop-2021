package shared.filter.descriptor;

import java.util.Arrays;
import java.util.List;

public class BinaryOperatorDescription<R, T> implements ExpressionDescription<R> {
    private final ExpressionDescription<T> left;
    private final ExpressionDescription<T> right;

    public BinaryOperatorDescription(ExpressionDescription<T> left, ExpressionDescription<T> right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public List<ExpressionDescription> getChildren() {
        return Arrays.asList(left, right);
    }

    public ExpressionDescription<T> getLeft() {
        return left;
    }

    public ExpressionDescription<T> getRight() {
        return right;
    }
}
