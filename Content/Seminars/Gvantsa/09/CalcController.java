public class CalcController implements InputListener {
    private CalcView view;
    private CalcBrain brain;

    public CalcController(CalcBrain brain, BasicView view){
        this.view = view;
        this.brain= brain;
        brain.registerDisplayChanged(view);
        view.registerInputListener(this);
    }

    public void start(){
        view.show();
    }

    @Override
    public void inputChanged(String input) {
        char digit = input.charAt(0);
        if (Character.isDigit(digit)){
            brain.pushDigit(input);
        }else if ("+-*/".indexOf(digit) != -1){
            brain.pushOperator(digit);
        }else if (digit == '=')
                brain.evaluate();
    }
}
