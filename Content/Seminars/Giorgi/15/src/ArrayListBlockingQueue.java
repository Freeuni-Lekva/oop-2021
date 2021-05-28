import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ArrayListBlockingQueue<T> {
    private List<T> elems;
    private int capacity;
    private Lock lock;
    private Condition insertedCond;
    private Condition removedCond;

    public void lock() {
        lock.lock();
    }


    public ArrayListBlockingQueue(int capacity) {
        elems = new ArrayList<>();
        this.capacity = capacity;
        lock = new ReentrantLock();
        insertedCond = lock.newCondition();
        removedCond = lock.newCondition();
    }

    public void push(T elem) {
        lock.lock();
        while (elems.size() == capacity) {
            try {
                removedCond.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        elems.add(elem);
        insertedCond.signal();
        lock.unlock();
    }

    public boolean tryPush(T elem, long millis) {
        lock.lock();
        while (elems.size() == capacity && millis > 0) {
            try {
                millis = removedCond.awaitNanos(1000000 * millis) / 1000000;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (millis >= 0) {
            elems.add(elem);
            insertedCond.signal();
            lock.unlock();
            return true;
        } else {
            lock.unlock();
            return false;
        }
    }

    public T pop() {
        lock.lock();
        while (elems.size() == 0) {
            try {
                insertedCond.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        T ret = elems.remove(0);
        removedCond.signal();
        lock.unlock();
        return ret;
    }

    public T tryPop(long millis) {
        return null;
    }
}
