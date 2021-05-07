public abstract class BasicView implements CalcView{
    private InputListener inputListener;

    public void registerInputListener(InputListener inputListener){
        this.inputListener = inputListener;
    }

    public void fireInputChanged(String input){
        inputListener.inputChanged(input);
    }
}
