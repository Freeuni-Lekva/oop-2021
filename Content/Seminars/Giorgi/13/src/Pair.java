import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Pair {
    // private Lock lock;
    private int a;

    public Pair() {
        a = 0;
        // lock = new ReentrantLock();
    }

    public void inc() {
        // lock.lock();
        synchronized (Pair.class) {
            a++;
            System.out.println(sum());
        }
        // lock.unlock();
    }

    public int sum() {
        // lock.lock();
        // int ret;
        synchronized (Pair.class) {
           return a;
        }
        // lock.unlock();
        // return ret;
    }
}



cond = lock.newCondition();

cond.await() - requires lock to be locked
  1) register signal listener
  2) unlock lock on which it was created
  3) sleep .... waits for signal
  4) wakes up upon receiving signal and tries re acquires lock

cond.signal() - requires lock to be locked
  1) sends signal
