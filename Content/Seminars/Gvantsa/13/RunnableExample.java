public class RunnableExample implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }

    public static void main(String[] args){
        Thread thread = new Thread(() -> {
            System.out.println("new runnable: " + Thread.currentThread().getName());
        });
        thread.start();
    }
}
