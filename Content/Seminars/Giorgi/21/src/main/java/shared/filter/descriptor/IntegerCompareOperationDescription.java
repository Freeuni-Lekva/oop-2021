package shared.filter.descriptor;

public class IntegerCompareOperationDescription extends CompareOperationDescription<Integer> {
    public IntegerCompareOperationDescription(CompareOperator op, ExpressionDescription<Integer> left, ExpressionDescription<Integer> right) {
        super(op, left, right);
    }
}
