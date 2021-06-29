package shared.filter.reflection;

import shared.filter.descriptor.*;

public class ExpressionBuilder {
    public static Expression build(ExpressionDescription ed) {
        if (ed instanceof CompareOperationDescription) {
            return buildCompareOperationExpression((CompareOperationDescription) ed);
        } else if (ed instanceof FieldValueDescription) {
            return buildFieldValueExpression((FieldValueDescription) ed);
        } else if (ed instanceof ConstValueDescription) {
            return buildConstValueExpression((ConstValueDescription) ed);
        } else if (ed instanceof AndExpressionDescription) {
            return buildAndExpression((AndExpressionDescription) ed);
        }
        throw new IllegalArgumentException(ed.toString());
    }

    private static Expression buildAndExpression(AndExpressionDescription ed) {
        AndFilter and = new AndFilter();
        for (ExpressionDescription e : ed.getChildren()) {
            and.add((Filter) build(e));
        }
        return and;
    }

    private static Expression buildConstValueExpression(ConstValueDescription ed) {
        return new ConstValue(ed.getValue());
    }

    private static Expression buildFieldValueExpression(FieldValueDescription ed) {
        return new FieldValue(ed.getFieldName());
    }

    private static Expression buildCompareOperationExpression(CompareOperationDescription ed) {
        switch (ed.getOperator()) {
            case EQUALS:
                return new Equals(build(ed.getLeft()), build(ed.getRight()));
            case LESS_THEN:
                if (ed instanceof IntegerCompareOperationDescription) {
                    return new LessThen(build(ed.getLeft()), build(ed.getRight()), new IntegerComparator());
                } else if (ed instanceof StringCompareOperationDescription) {
                    return new LessThen(build(ed.getLeft()), build(ed.getRight()), new StringComparator());
                }
                throw new IllegalArgumentException(ed.getOperator().toString());
            case GREATER_THEN:
                if (ed instanceof IntegerCompareOperationDescription) {
                    return new GreaterThen(build(ed.getLeft()), build(ed.getRight()), new IntegerComparator());
                } else if (ed instanceof StringCompareOperationDescription) {
                    return new GreaterThen(build(ed.getLeft()), build(ed.getRight()), new StringComparator());
                }
                throw new IllegalArgumentException(ed.getOperator().toString());
            default:
                throw new IllegalArgumentException(ed.getOperator().toString());
        }
    }
}
