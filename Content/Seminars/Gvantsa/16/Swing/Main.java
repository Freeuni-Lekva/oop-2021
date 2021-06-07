package Swing;

public class Main {

    public static void main(String[] args){
        StudentStore store = new StudentStore();
        SwingView view = new SwingView(store);
        view.show();
    }
}
