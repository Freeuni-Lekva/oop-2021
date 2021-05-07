public class CalcBrain {
    private char operator;
    private String display;
    private String prevDisplay;
    private DisplayChange displayChange;


    public CalcBrain(){
        display = "0";
        prevDisplay = "0";
    }

    public void pushOperator(char operator){
        this.operator = operator;
        prevDisplay = display;
        display = "0";
    }

    public void pushDigit(String digit){
        display = "" + digit.charAt(0);
        fireDisplayChanged(display);
    }

    public void registerDisplayChanged(DisplayChange displayChange){
        this.displayChange = displayChange;
    }

    public void fireDisplayChanged(String display){
        displayChange.displayChanged(display);
    }

    public void evaluate(){
        double first = Double.valueOf(display);
        double second = Double.valueOf(prevDisplay);
        double result = 0;
        switch (operator){
            case '+' : result = first + second; break;
            case '-' : result = first - second; break;
            case '*' : result = first * second; break;
            case '/' : result = first / second; break;
        }
        String resultStr = String.valueOf(result);
        display = resultStr;
        fireDisplayChanged(resultStr);
    }
}
