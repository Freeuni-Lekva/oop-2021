public class Main {
    public static void main(String[] args) throws InterruptedException {
        ArrayListBlockingQueue<Integer> q = new ArrayListBlockingQueue(1);
        Thread x = new Thread(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
           int e = q.pop();
            System.out.printf("X POP: %d\n", e);
        });
        Thread z = new Thread(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int e = q.pop();
            System.out.printf("Y POP: %d\n", e);
        });
        Thread y = new Thread(() -> {
            while (!q.tryPush(1, 100));
            System.out.println("PUSH: 1");
            while (!q.tryPush(2, 100));
            System.out.println("PUSH: 2");
        });
        x.start();
        z.start();
        Thread.sleep(10);
        y.start();
        x.join();
        y.join();
        z.join();
    }
}
