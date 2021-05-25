public interface BlockingQueue<T> {
    void enqueue(T elem) throws InterruptedException;

    T dequeue() throws InterruptedException;

    boolean tryEnqueue(T elem, int time) throws InterruptedException;

    T tryDequeue(int time) throws InterruptedException;

    int size();

    boolean isEmpty();
}