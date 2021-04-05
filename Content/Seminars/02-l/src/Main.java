import java.util.function.BooleanSupplier;

public class Main {
    public static void main(String[] args) {
        RomanNumeral r;
        try {
            r = new RomanNumeral(Integer.valueOf(args[0]));
        } catch (NumberFormatException e) {
            r = new RomanNumeral(args[0]);
        }
        System.out.println(r.toInt());
        System.out.println(r.toString());
    }
}
