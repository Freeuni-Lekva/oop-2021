public class RingBufferMain {
    public static void main(String[] args) throws InterruptedException {
        RingBuffer b = new RingBuffer(5);
        Thread w = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    b.write(i);
                }
            }
        });
        Thread r = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                b.read();
            }
        });
        w.start();
        r.start();
        w.join();
        r.join();
    }
}
