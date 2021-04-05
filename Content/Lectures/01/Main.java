public class Main {
    public static void main(String[] args) {
	StringBuilder b = new StringBuilder();
	b.append("a").append("bc");
	String x = b.toString();
	// x = "abc";
	System.out.println("abc" == "abc");
	System.out.println("abc" == x);
    }
}
