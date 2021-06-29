package shared.filter.descriptor;

public class StringCompareOperationDescription extends CompareOperationDescription<String> {
    public StringCompareOperationDescription(CompareOperator op, ExpressionDescription<String> left, ExpressionDescription<String> right) {
        super(op, left, right);
    }
}
