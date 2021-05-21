import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MySemaphore {
    private Lock l;
    // private Condition c;
    private List<Condition> listener;
    private int value;

    public MySemaphore(int initial_value) {
        l = new ReentrantLock();
        // c = l.newCondition();
        listener = new ArrayList<>();
        value = initial_value;
    }

    public void acquire(int k) throws InterruptedException {
        l.lock();
        while (value < k) {
            Condition c = l.newCondition();
            listener.add(c);
            c.await();
        }
        value -= k;
        l.unlock();
    }

    public void release(int k) {
        l.lock();
        value += k;
        int len = Math.min(value, listener.size());
        for (int i = 0; i < len; i++) {
            listener.remove(listener.size() - 1).signal();
        }
        l.unlock();
    }

    public void release() {
        release(1);
    }

    public void acquire() throws InterruptedException {
        acquire(1);
    }
}
