public class CalcBrain {
    private String display;
    private String prevDisplay;
    private char operation;
    private boolean shouldClear;
    private DisplayChangeListener displayListener;

    public CalcBrain() {
        display = "0";
        shouldClear = false;
    }

    public String getDisplay() {
        return display;
    }

    public void pushDigit(char c) {
        if (shouldClear) {
            prevDisplay = display;
            display = "";
            shouldClear = false;
        }
        display += c;
        if (display.charAt(0) == '0' && display.length() > 1 && display.charAt(1) != '.') {
            display = display.substring(1);
        }
        fireDisplayChangeListener();
    }

    public void pushOperation(char c) {
        operation = c;
        shouldClear = true;
    }

    public void registerDisplayChangeListener(DisplayChangeListener listener) {
        displayListener = listener;
    }

    private void fireDisplayChangeListener() {
        displayListener.displayChanged(display);
    }

    public void evaluate() {
        double left = Double.valueOf(prevDisplay);
        double right = Double.valueOf(display);
        double result = 0;
        switch (operation) {
            case '+':
                result = left + right;
                break;
            case '-':
                result = left - right;
                break;
            case '*':
                result = left * right;
                break;
            case '/':
                result = left / right;
                break;
        }
        display = String.valueOf(result);
        fireDisplayChangeListener();
    }
}
