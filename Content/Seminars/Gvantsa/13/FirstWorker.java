public class FirstWorker extends Thread{
    private String name;

    public FirstWorker(String name){
        super(name);
        this.name = name;
    }

    @Override
    public void run(){
        Thread currThread = Thread.currentThread();
        for(int i = 0; i < 1000000; i++) {
            if (i % 100000 == 0) {
                System.out.println(currThread.getName() + " " + i);
            }
        }
    }

    public static void main(String[] args){
        System.out.println("Start...");
        FirstWorker w1 = new FirstWorker("first");
        FirstWorker w2 = new FirstWorker("second");

        w1.setPriority(Thread.MIN_PRIORITY);
        w2.setPriority(Thread.MAX_PRIORITY);

        w1.run();
        w2.run();


        try {
            w1.join();
            w2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("End...");
    }
}
