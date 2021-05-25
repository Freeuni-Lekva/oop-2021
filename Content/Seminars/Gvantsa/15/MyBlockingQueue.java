import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyBlockingQueue<T> implements BlockingQueue<T> {
    private final int maxSize;
    private final List<T> elements;
    private final Lock lock;
    private final Condition enqueueCond;
    private final Condition dequeueCond;

    public MyBlockingQueue(int maxSize){
        this.maxSize = maxSize;
        elements = new ArrayList<>();
        lock = new ReentrantLock();
        enqueueCond = lock.newCondition();
        dequeueCond = lock.newCondition();
    }

    @Override
    public void enqueue(T elem) throws InterruptedException {
        lock.lock();
        while (elements.size() >= maxSize){
            enqueueCond.await();
        }
        elements.add(elem);
        dequeueCond.signal();
        lock.unlock();
    }

    @Override
    public T dequeue() throws InterruptedException {
        lock.lock();
        while (elements.size() == 0){
            dequeueCond.await();
        }
        T elem = elements.remove(0);
        enqueueCond.signal();
        lock.unlock();
        return elem;
    }

    @Override
    public boolean tryEnqueue(T elem, int time) throws InterruptedException {
        lock.lock();
        boolean elapsed = elements.size() < maxSize;
        System.out.println("preElapsed: " + elapsed);
        while (elements.size() >= maxSize){
            elapsed = enqueueCond.await(time, TimeUnit.SECONDS);
        }

        System.out.println("postElapsed: " + elapsed);


        if (!elapsed){
            lock.unlock();
            return false;
        }

        elements.add(elem);
        dequeueCond.signal();
        lock.unlock();
        return true;
    }

    @Override
    public T tryDequeue(int time) throws InterruptedException {
        lock.lock();
        boolean elapsed = elements.size() != 0;
        while (elements.size() == 0){
            elapsed = dequeueCond.await(time, TimeUnit.SECONDS);
        }

        if (!elapsed){
            lock.unlock();
            return null;
        }

        T elem = elements.remove(0);
        enqueueCond.signal();
        lock.unlock();
        return elem;
    }

    @Override
    public int size() {
        lock.lock();
        int size = elements.size();
        lock.unlock();
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
}
