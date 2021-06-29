package shared.filter.descriptor;

public class CompareOperationDescription<T> extends BinaryOperatorDescription<Boolean, T> {
    private final CompareOperator op;

    public CompareOperationDescription(CompareOperator op, ExpressionDescription<T> left, ExpressionDescription<T> right) {
        super(left, right);
        this.op = op;
    }

    public CompareOperator getOperator() {
        return op;
    }
}
