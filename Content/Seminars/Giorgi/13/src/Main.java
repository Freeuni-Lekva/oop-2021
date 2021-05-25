class Worker extends Thread {
    private Pair p;

    public Worker(Pair p) {
        this.p = p;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            p.inc();
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class MyWorker implements Runnable {
    private Pair p;

    public MyWorker(Pair p) {
        this.p = p;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            p.inc();
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Pair p = new Pair();
        new MyWorker(p).run();

        Worker x = new Worker(p);
        Thread y = new Thread(new MyWorker(p));

        x.start();
        // y.start();

        x.join();
        // y.join();

        System.out.println(p.sum());
    }
}
