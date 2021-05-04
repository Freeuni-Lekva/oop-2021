public class CalcController implements InputListener {
    private final CalcBrain brain;
    private final CalcView view;

    public CalcController(CalcBrain brain, CalcView view) {
        this.brain = brain;
        this.view = view;
        brain.registerDisplayChangeListener(view);
        view.registerInputListener(this);
    }

    public void start() {
        view.show();
    }

    @Override
    public void inputAvailable(char input) {
        if (Character.isDigit(input)) {
            brain.pushDigit(input);
        } else if ("+-*/".indexOf(input) != -1) {
            brain.pushOperation(input);
        } else if (input == '=') {
            brain.evaluate();
        }
    }
}
