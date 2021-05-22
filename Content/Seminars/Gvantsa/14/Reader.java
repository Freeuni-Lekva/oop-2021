import java.util.List;
import java.util.concurrent.Semaphore;

public class Reader implements Runnable {
    private final List<Character> buffer;
    private final int BUFFER_LENGTH;
    private final Semaphore newCharacter;

    public Reader(List<Character> buffer, int BUFFER_LENGTH, Semaphore newCharacter) {
        this.buffer = buffer;
        this.BUFFER_LENGTH = BUFFER_LENGTH;
        this.newCharacter = newCharacter;
    }

    @Override
    public void run() {
        for (int i = 0; i < BUFFER_LENGTH; i++) {
            try {
                newCharacter.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            char c = buffer.get(i);
            System.out.println(c + " was read");
        }
    }
}
