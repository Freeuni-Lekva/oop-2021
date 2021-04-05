/**
 * RomanNumeral კლასის ტიპის ობიექტი, წარმოადგენს მთელ რიცხვს დიაპაზონიდან: [1, 3999].<br/>
 * მისი შექმნა შესაძლებელია, როგორც მთელი რიცხვის, ასევე სტრიქონის საშუალებით, რომელსაც აქვს რომაული რიცხვითი ჩანაწერის სახე.<br/>
 * მოცემული კლასი გვაძლევს, რომაული რიცხვითი ჩანაწერის არაბულ ჩანაწერში გადაყვანის საშუალებას და პირიქით.
 */
public class RomanNumeral {

    private final int arabicNumber;

    private final static int[] numbers = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    private final static String[] letters = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    /**
     * Constructor. ქმნის რომაულ რიცხვს, პარამეტრად გადაცემული მთელი რიცხვიდან.
     * @param arabicNumber მთელი რიცხვი [1, 3999] დიაპაზონიდან.
     * @throws NumberFormatException თუ გადაცემული მთელი რიცხვი არ არის მოთავსებული დიაპაზონში: [1, 3999].
     */
    public RomanNumeral(int arabicNumber) {
        if (arabicNumber < 1 || arabicNumber > 3999) {
            throw new NumberFormatException("Roman numeral must be in range: [1, 3999]");
        }
        this.arabicNumber = arabicNumber;
    }

    /**
     * Constructor. ქმნის რომაულ რიცხვის, პარამეტრად გადაცემული სტრიქონიდან.
     * მაგალითად, RomanNumeral("xvii") არის 17.
     * @param romanNumeral რომაული რიცხვი სტრიქონულ ფორმატში.
     * @throws NumberFormatException თუ გადაცემული სტრიქონი არ შეიცავს სწორ რომაულ რიცხვით ჩანაწერს.
     */
    public RomanNumeral(String romanNumeral) {
        if (romanNumeral.length() == 0) {
            throw new NumberFormatException("An empty string does not define a valid Roman numeral");
        }

        romanNumeral = romanNumeral.toUpperCase();

        int i = 0, rslt = 0;

        while (i < romanNumeral.length()) {
            int number = letter2Number(romanNumeral.charAt(i)); // გადავიყვანოთ მიმდინარე რომაული ციფრი შესაბამის რიცხვით მნიშვნელობაში

            ++i; // გადავინაცვლოთ სტრიქონის შემდეგ პოზიციაზე

            if (i == romanNumeral.length()) {
                // წაკითხული სიმბოლოს შემდეგ აღარ არის სტრიქონში სიმბოლოები.
                // წაკითხული სიმბოლო განვიხილოთ რომაულ ციფრად
                // და მისი შესაბამისი რიცხვითი მნიშვნელობა დაუმატოთ რეზულტატს.
                rslt += number;
            } else {
                // შევხედოთ შემდეგ სიმბოლოს სტრიქონში.
                // თუ მისი რიცხვითი მნიშვნელობა აღემატება, წინა წაკითხული სიმბოლოს რიცხვით მნიშვნელობას,
                // მაშინ ეს ორი სიმბოლო უნდა განვიხილოთ როგორც ერთი რომაული ციფრი, რიცხვითი მნიშვნელობით (nextNumber - number).
                int nextNumber = letter2Number(romanNumeral.charAt(i));
                if (nextNumber > number) {
                    // გავაერთიანოთ ორი სიმბოლო, განვიხილოთ ერთ რომაულ ციფრად 
                    // და მათი შესაბამისი რიცხვითი მნიშვნელობა დაუმატოთ რეზულტატს.
                    rslt += (nextNumber - number);
                    ++i; //გადავინაცვლოთ სტრიქონში შემდეგ პოზიციაზე.
                } else {
                    // არ გავაერთიანოთ ორი სიმბოლო, მხოლოდ პირველი წაკითხული სიმბოლო განვიხილოთ რომაულ ციფრად
                    // და მისი შესაბამისი რიცხვითი მნიშვნელობა დაუმატოთ რეზულტატს.
                    rslt += number;
                }
            }
        }

        if (rslt > 3999) {
            throw new NumberFormatException("Roman numeral must be in range: [1, 3999]");
        }

        this.arabicNumber = rslt;
    }

    /**
     * პოულობს რომაული ციფრის სიმბოლოს რიცხვით მნიშვნელობას.
     * @param letter რომაული ციფრი.
     * @return რომეული ციფრის რიცხვითი მნიშვნელობა.
     */
    private int letter2Number(char letter) {
        switch (letter) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default:
                throw new NumberFormatException("Illegal character \""
                    + letter
                    + "\" in Roman numeral");
        }
    }

    /**
     * @return აბრუნებს მთელ რიცხვს რომაულ ფორმატში.
     */
    @Override
    public String toString() {
        String romanNumeral = "";
        int number = this.arabicNumber;

        for (int i = 0; i < numbers.length; ++i) {
            while (number >= numbers[i]) {
                romanNumeral += letters[i];
                number -= numbers[i];
            }
        }

        return romanNumeral;
    }

    /**
     * @return აბრუნებს მთელ რიცხვს არაბულ ფორმატში.
     */
    public int toInt() {
        return this.arabicNumber;
    }
}
