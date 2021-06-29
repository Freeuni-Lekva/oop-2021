package shared.filter.reflection;

import shared.Student;
import shared.filter.descriptor.*;
import shared.filter.descriptor.CompareOperator;

public class Main {
    public static void main(String[] args) {
        ExpressionDescription ed = new AndExpressionDescription()
                .add(new StringCompareOperationDescription(
                        CompareOperator.EQUALS,
                        new FieldValueDescription<>("firstName"),
                        new StringValueDescription("Free")))
                .add(new IntegerCompareOperationDescription(
                        CompareOperator.LESS_THEN,
                        new FieldValueDescription<>("enrollmentYear"),
                        new IntegerValueDescription(2000)));
        Expression e = ExpressionBuilder.build(ed);
        Filter f = (Filter) e;
        Student st = new Student("Free", "Uni", 2000);
        System.out.println(f.evaluate(st));
    }
}
