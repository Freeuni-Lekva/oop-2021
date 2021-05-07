package V3;

public abstract class BinaryOperation implements Node {
    private Node left;
    private Node right;

    public BinaryOperation(Node left, Node right){
        this.left = left;
        this.right = right;
    }

    public double evaluate(){
        return doEvaluate(left.evaluate(), right.evaluate());
    }

    @Override
    public String toString(){
        return "(" + left.toString() + getOperator() + right.toString() + ")";
    }

    abstract char getOperator();
    abstract double doEvaluate(double left, double right);
}
