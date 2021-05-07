import java.util.Scanner;

public class ConsoleView extends BasicView{
    @Override
    public void show() {
        System.out.println("Console mode on");
        Scanner scanner = new Scanner(System.in);
        while(true){
            String input = scanner.nextLine();
            fireInputChanged(input);
        }
    }

    @Override
    public void displayChanged(String display) {
        System.out.println(display);
    }
}
