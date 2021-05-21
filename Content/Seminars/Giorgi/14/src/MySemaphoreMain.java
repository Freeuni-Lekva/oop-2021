public class MySemaphoreMain {
    public static void main(String[] args) throws InterruptedException {
        MySemaphore s = new MySemaphore(0);
        Thread[] t = new Thread[5];
        for (int i = 0; i < 5; i++) {
            t[i] = new Thread(() -> {
                try {
                    s.acquire(1);
                    System.out.println("ACQUIRED");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            t[i].start();
        }
        Thread.sleep(100);
        s.release(10);
        for (int i = 0; i < 5; i++) {
            t[i].join();
        }
    }
}
