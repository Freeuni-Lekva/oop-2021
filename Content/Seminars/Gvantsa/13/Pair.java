class Pair {
    private int a;
    private int b;

    public Pair(int a, int b){
        this.a = a;
        this.b = b;
    }

    public synchronized void inc(){
        a++;
        b++;
    }

    public synchronized int sum(){
        return a + b;
    }
}

class PairWorker extends Thread{
    private Pair pair;

    public PairWorker(Pair pair){
        this.pair = pair;
    }

    @Override
    public void run(){
        for (int i = 0; i < 10000; i++) {
            pair.inc();
        }
    }

    public static void main(String[] args){
        Pair pair = new Pair(0,0);
        PairWorker w1 = new PairWorker(pair);
        PairWorker w2 = new PairWorker(pair);
        PairWorker w3 = new PairWorker(pair);

        w1.start();
        w2.start();
        w3.start();

        try {
            w1.join();
            w2.join();
            w3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(pair.sum());

    }
}
