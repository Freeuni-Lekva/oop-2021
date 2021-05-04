import java.io.Console;
import java.io.IOException;

public class ConsoleCalcView extends BasicCalcView {
    public ConsoleCalcView() {
        registerInputListener(new NoOpInputListener());
    }

    @Override
    public void displayChanged(String display) {
        System.out.println(">>> " + display);
    }

    @Override
    public void show() {
        while (true) {
            try {
                byte[] bytes = System.in.readNBytes(1);
                if (bytes.length >= 1) {
                    char input = (char) bytes[0];
                    fireInputListener(input);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
