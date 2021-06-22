import api.LinkedList;
import api.Sequence;

public class Main {
    public static void main(String[] args) {
        LinkedList<Integer> l = new LinkedList<>();
        l.add(1);
        l.add(2);
        l.add(3);
        l.add(4);
        Sequence<Integer> seq = l;
        Sequence<Integer> x2 = seq.map(x -> x * 2);
        for (int x : x2) {
            System.out.println(x);
        }
        System.out.println("----");
        Sequence<Integer> lt5 = x2.filter(x -> x < 5);
        for (int x : lt5) {
            System.out.println(x);
        }
        System.out.println("----");
        int sum = lt5.reduce((x, y) -> x + y, 0);
        System.out.println(sum);
    }
}
