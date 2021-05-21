import java.util.Calendar;
import java.util.GregorianCalendar;

class Worker implements Runnable {
    private Main m;

    public Worker(Main m) {
        this.m = m;
    }

    @Override
    public void run() {
        try {
            m.doWork();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class Main {
    private String name;

    public Main(String name) {
        this.name = name;
    }

    public void doWork() throws InterruptedException {
        synchronized (Main.class) {
            System.out.printf("Start %s\n", name);
            Thread.sleep(2);
            System.out.printf("End %s\n", name);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Calendar c = new GregorianCalendar();
        long start = System.nanoTime();
        int n = 1000;
        Main[] m = new Main[n];
        Thread[] t = new Thread[n];
        for (int i = 0; i < n; i++) {
            m[i] = new Main(String.valueOf(i));
            final int k = i;
            t[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        m[k].doWork();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            t[i].start();
        }
        for (int i = 0; i < n; i++) {
            t[i].join();
        }
        long end = System.nanoTime();
        System.out.println(end - start);
    }
}
