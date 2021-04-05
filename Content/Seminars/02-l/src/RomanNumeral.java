public class RomanNumeral {

    private int arabic;

    private final static int[] numbers = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    private final static String[] letters = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    public RomanNumeral(String roman) {
        arabic = 0;
        for (int i = 0; i < roman.length(); i++) {
            int current = toNumber(roman.charAt(i));
            if (i + 1 == roman.length()) {
                arabic += current;
            } else {
                int next = toNumber(roman.charAt(i + 1));
                if (next > current) {
                    arabic += next - current;
                    i++;
                } else {
                    arabic += current;
                }
            }
        }
    }

    private int toNumber(char c) {
        for (int i = 0; i < letters.length; i++) {
            if (letters[i].equals("" + c)) {
                return numbers[i];
            }
        }
        throw new NumberFormatException("" + c);
    }

    public RomanNumeral(int arabic) {
        this.arabic = arabic;
    }

    public int toInt() {
        return arabic;
    }

    public String toString() {
        int cur = arabic;
        String ret = "";
        for (int i = 0; i < numbers.length; i++) {
            while (cur >= numbers[i]) {
                cur -= numbers[i];
                ret += letters[i];
            }
        }
        return ret;
    }
}
