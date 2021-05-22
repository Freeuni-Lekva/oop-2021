import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        List<Character> buffer = new ArrayList<>();
        int BUFFER_LENGTH = 10;

        Semaphore newCharacter = new Semaphore(0);

        Thread writer = new Thread(new Writer(buffer, BUFFER_LENGTH, newCharacter));
        Thread reader = new Thread(new Reader(buffer, BUFFER_LENGTH, newCharacter));

        writer.start();
        Thread.sleep(100);
        reader.start();

        writer.join();
        reader.join();
    }
}
