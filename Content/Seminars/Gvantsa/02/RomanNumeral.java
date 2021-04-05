/**
 * core.RomanNumeral კლასის ტიპის ობიექტი, წარმოადგენს მთელ რიცხვს დიაპაზონიდან: [1, 3999].<br/>
 * მისი შექმნა შესაძლებელია, როგორც მთელი რიცხვის, ასევე სტრიქონის საშუალებით, რომელსაც აქვს რომაული რიცხვითი ჩანაწერის სახე.<br/>
 * მოცემული კლასი გვაძლევს, რომაული რიცხვითი ჩანაწერის არაბულ ჩანაწერში გადაყვანის საშუალებას და პირიქით.
 */
public class RomanNumeral {
    private int arabicNumber;

    private final static int[] numbers = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    private final static String[] letters = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    /**
     * Constructor. ქმნის რომაულ რიცხვს, პარამეტრად გადაცემული მთელი რიცხვიდან.
     * @param arabicNumber მთელი რიცხვი [1, 3999] დიაპაზონიდან.
     * @throws NumberFormatException თუ გადაცემული მთელი რიცხვი არ არის მოთავსებული დიაპაზონში: [1, 3999].
     */
    public RomanNumeral(int arabicNumber) {
        if (arabicNumber < 1 || arabicNumber > 3999)
            throw new NumberFormatException("Number not in range!");
        this.arabicNumber = arabicNumber;
    }

    /**
     * Constructor. ქმნის რომაულ რიცხვის, პარამეტრად გადაცემული სტრიქონიდან.
     * მაგალითად, core.RomanNumeral("xvii") არის 17.
     * @param romanNumeral რომაული რიცხვი სტრიქონულ ფორმატში.
     * @throws NumberFormatException თუ გადაცემული სტრიქონი არ შეიცავს სწორ რომაულ რიცხვით ჩანაწერს.
     */
    public RomanNumeral(String romanNumeral) {
        if (romanNumeral.length() == 0)
            throw new NumberFormatException("ზომა 0ზე მეტი უნდა იყოს!");

        romanNumeral = romanNumeral.toUpperCase();

        int i = 0;
        int result = 0;
        while (i < romanNumeral.length() - 1){
            char elem = romanNumeral.charAt(i);
            int currValue = letter2Number(elem);
            char nextElem = romanNumeral.charAt(i + 1);
            int nextValue = letter2Number(nextElem);
            if (nextValue > currValue){
                result -= currValue;
            }else{
                result += currValue;
            }
            i++;
        }

        char elem = romanNumeral.charAt(romanNumeral.length() - 1);
        result += letter2Number(elem);

        this.arabicNumber = result;
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
                throw new NumberFormatException("სიმბოლო არასწორია!");
        }
    }

    /**
     * @return აბრუნებს მთელ რიცხვს რომაულ ფორმატში.
     */
    @Override
    public String toString() {
        String result = "";
        int currValue = this.arabicNumber;

        for(int i = 0; i < numbers.length; i++){
            while (numbers[i] <= currValue){
                result += letters[i];
                currValue -= numbers[i];
            }
        }

        return result;
    }

    /**
     * @return აბრუნებს მთელ რიცხვს არაბულ ფორმატში.
     */
    public int toInt() {
        return this.arabicNumber;
    }
}