import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Vector<T> {
    ReadWriteLock rwl = new ReentrantReadWriteLock();
    Lock read = rwl.readLock();
    Lock write = rwl.writeLock();

    public T get(int index){
        read.lock();
        T elem = null;
        read.unlock();
        return elem;
    }

    public void add(T elem){
        write.lock();

        write.unlock();
    }
}
