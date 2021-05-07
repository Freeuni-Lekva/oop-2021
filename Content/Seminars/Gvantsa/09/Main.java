public class Main {

    public static void main(String[] args){
        CalcBrain calcBrain = new CalcBrain();
        BasicView calcView = new ConsoleView();
        CalcController calcController = new CalcController(calcBrain, calcView);
        calcController.start();
    }
}
