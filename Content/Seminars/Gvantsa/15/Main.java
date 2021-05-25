public class Main {
    public static void main(String[] args) {
        BlockingQueue<String> bq = new MyBlockingQueue(3);

        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println(i + " Enqueue started...");
                    try {
                        boolean result = bq.tryEnqueue(Integer.toString(i), 1);
                        System.out.println("Try result: " + result);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(i + " Enqueue ended...");
                }
            }
        });

        Thread consumer = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println(i + " Dequeue started...");
                    try {
                        String elem = bq.tryDequeue(1);
                        System.out.println(elem + " dequeued");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(i + " Dequeue ended...");
                }
            }
        });

        producer.start();
        consumer.start();
    }
}
