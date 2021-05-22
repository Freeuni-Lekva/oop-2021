import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MySemaphore {
    private int permits;
    private Lock lock = new ReentrantLock();
    private LinkedList<Condition> conditions = new LinkedList<>();

    public MySemaphore(int permits) {
        this.permits = permits;
    }

    public void acquire() throws InterruptedException {
        lock.lock();
        try {
            Condition newCondition = lock.newCondition();
            conditions.add(newCondition);
            while (permits <= 0) {
                newCondition.await();
            }
            permits--;
        } finally {
            lock.unlock();
        }
    }

    public void release() {
        lock.lock();
        permits++;
        if (conditions.size() > 0)
            conditions.pop().signal();
        lock.unlock();
    }
}
