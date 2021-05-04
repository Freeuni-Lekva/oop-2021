public abstract class BasicCalcView implements CalcView {
    private InputListener inputListener;

    @Override
    public void registerInputListener(InputListener inputListener) {
        this.inputListener = inputListener;
    }

    protected void fireInputListener(char input) {
        inputListener.inputAvailable(input);
    }
}
