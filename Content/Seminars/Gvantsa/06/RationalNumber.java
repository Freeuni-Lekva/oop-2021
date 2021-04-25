public class RationalNumber {

    private int numerator;
    private int denominator;

    public RationalNumber(int numerator, int denominator){

    }

    public RationalNumber(int numerator){

    }

    public RationalNumber(RationalNumber number){
        numerator = number.numerator;
        denominator = number.denominator;
    }

    public RationalNumber add(RationalNumber a){

    }

    public RationalNumber mult(RationalNumber a){

    }

    @Override
    public int hashCode(){
        return Integer.valueOf(numerator).hashCode() & Integer.valueOf(denominator).hashCode();
    }
}
