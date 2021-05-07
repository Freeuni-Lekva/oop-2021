package V2;

public class Main {

    public static void main(String[] args){

        Node left = new ValueNode(1);
        Node right = new MultiplyNode(new ValueNode(2), new ValueNode(3));
        Node result = new PlusNode(left, right);
        System.out.println(result.evaluate());
        System.out.println(result);
    }
}
