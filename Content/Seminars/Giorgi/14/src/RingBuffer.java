import java.util.concurrent.Semaphore;

public class RingBuffer {
    private int[] buffer;
    private int write_index;
    private int read_index;
    private MySemaphore empty;
    private MySemaphore full;

    public RingBuffer(int n) {
        buffer = new int[n];
        write_index = 0;
        read_index = 0;
        empty = new MySemaphore(n);
        full = new MySemaphore(0);
    }

    public void write(int k) {
        try {
             empty.acquire();
            buffer[write_index] = k;
            write_index = (write_index + 1) % buffer.length;
            System.out.printf("Write %d\n", k);
             full.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int read() {
        int ret = 0;
         try {
            full.acquire();
            ret = buffer[read_index];
            read_index = (read_index + 1) % buffer.length;
            System.out.printf("Read %d\n", ret);
             empty.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ret;
    }
}
