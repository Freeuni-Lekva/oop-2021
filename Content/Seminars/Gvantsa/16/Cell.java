import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Cell implements Runnable {
    private boolean[][] grid;
    private int x;
    private int y;
    private int numIterations;
    private CyclicBarrier barrier;

    public Cell(boolean[][] grid, int x, int y, int numIterations, CyclicBarrier barrier){
        this.grid = grid;
        this.x = x;
        this.y = y;
        this.numIterations = numIterations;
        this.barrier = barrier;
    }

    private boolean getNextState(){
        int aliveCount = 0;

        for (int i = -1; i < 2; i++){
            for (int j = -1; j < 2; j++){
                if (x + i >= 0 && x + i < grid.length
                        && y + j >= 0 && y + j < grid[0].length ) {
                    if (grid[x + i][y + j] && !(i == 0 && j == 0)) {
                        aliveCount++;
                    }
                }
            }
        }

        boolean isAlive = grid[x][y];
        boolean nextState = isAlive;

        if (isAlive && aliveCount < 2){
            nextState = false;
        }else {
            if (isAlive && (aliveCount == 2 || aliveCount == 3)){
                nextState = true;
            }else{
                if (!isAlive && aliveCount > 3){
                    nextState = true;
                }
            }
        }

        return nextState;
    }

    @Override
    public void run() {
        for (int iteration = 0; iteration < numIterations; iteration++){
            boolean nextState = getNextState();
            try {
                barrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
            grid[x][y] = nextState;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int n = 20;
        int m = 20;
        boolean[][] grid = new boolean[n][m];
        initialize(grid);
        Thread[][] cells = new Thread[n][m];
        CyclicBarrier barrier = new CyclicBarrier(n * m, new Runnable() {
            @Override
            public void run() {
                System.out.println("------------------------");
                for (int i = 0; i < n; i++){
                    for (int j = 0; j < m; j++){
                        if (grid[i][j]){
                            System.out.print("x");
                        }else{
                            System.out.print(".");
                        }
                    }
                    System.out.println();
                }
                System.out.println();
            }
        });
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                cells[i][j] = new Thread(new Cell(grid, i, j, 3, barrier));
                cells[i][j].start();
            }
        }

        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                cells[i][j].join();
            }
        }
    }

    public static void initialize(boolean[][] grid) {
        Random r = new Random();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = r.nextBoolean();
            }
        }
    }

}
