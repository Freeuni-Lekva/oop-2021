public class FizzBuzz {

    public FizzBuzz(){
        System.out.println("FizzBuzz created!");
    }

    public String evaluate(int i){
        if (i % 15 == 0 || (containsDigit(i, "3") && containsDigit(i,"5")))
            return "FizzBuzz";

        if (i % 3 == 0 || containsDigit(i, "3"))
            return "Fizz";

        if (i % 5 == 0 || containsDigit(i, "5"))
            return "Buzz";

        return String.valueOf(i);
    }

    private boolean containsDigit(int num, String digit){
        return String.valueOf(num).contains(digit);
    }
}
