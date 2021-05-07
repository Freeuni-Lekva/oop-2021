package V1;

public class Node {
    private double value;
    private char operator;
    private Node left;
    private Node right;

    public Node(double value) {
        this.value = value;
        this.operator = '$';
    }

    public Node(char operator, double left, double right) {
        this(operator, new Node(left), new Node(right));
    }

    public Node(char operator, Node left, Node right) {
        this.operator = operator;
        this.left = left;
        this.right = right;
    }

    public double evaluate() {
        switch (operator){
            case '$' : return value;
            case '+' : return left.evaluate() + right.evaluate();
            case '-' : return left.evaluate() - right.evaluate();
            case '*' : return left.evaluate() * right.evaluate();
            case '/' : return left.evaluate() / right.evaluate();
            default : throw new RuntimeException("Invalid operator!");
        }
    }
    
    @Override
    public String toString() {
        switch (operator){
            case '$' : return "(" + value + ")";
            case '+' : return "(" + left.toString() + "+" +right.toString() + ")";
            case '-' : return "(" + left.toString() + "-" +right.toString() + ")";
            case '*' : return "(" + left.toString() + "*" +right.toString() + ")";
            case '/' : return "(" + left.toString() + "/" +right.toString() + ")";
            default : throw new RuntimeException("Invalid operator!");
        }
    }
}