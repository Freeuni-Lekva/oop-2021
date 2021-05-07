package V3;

public class PlusNode extends BinaryOperation {

    public PlusNode(Node left, Node right){
        super(left, right);
    }

    @Override
    char getOperator() {
        return '+';
    }

    @Override
    double doEvaluate(double left, double right) {
        return left - right;
    }
}
