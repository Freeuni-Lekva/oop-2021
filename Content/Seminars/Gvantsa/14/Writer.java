import java.util.List;
import java.util.concurrent.Semaphore;

public class Writer implements Runnable {
    private final List<Character> buffer;
    private final int BUFFER_LENGTH;
    private final Semaphore newCharacter;

    public Writer(List<Character> buffer, int BUFFER_LENGTH, Semaphore newCharacter) {
        this.buffer = buffer;
        this.BUFFER_LENGTH = BUFFER_LENGTH;
        this.newCharacter = newCharacter;
    }

    @Override
    public void run() {
        for (int i = 0; i < BUFFER_LENGTH; i++) {
            char c = (char) ('a' + i);
            buffer.add(c);
            System.out.println(c + " was added");
            newCharacter.release();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
