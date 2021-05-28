import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

class Cell implements Runnable {
    private boolean[][] grid;
    private int x;
    private int y;
    private CyclicBarrier barrier;
    private int numIterations;

    public Cell(boolean[][] grid, int x, int y, CyclicBarrier barrier, int numIterations) {
        this.grid = grid;
        this.x = x;
        this.y = y;
        this.barrier = barrier;
        this.numIterations = numIterations;
    }
    @Override
    public void run() {
        for (int iter = 0; iter <= numIterations; iter++) {
            boolean newState = calculateNewState();
            try {
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            grid[x][y] = newState;
        }
    }

    private boolean calculateNewState() {
        boolean newState = false;
        int aliveCount = 0;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                int k = x + i;
                int l = y + j;
                if (0 <= k && k < grid.length && 0 <= l && l < grid[0].length) {
                    if (grid[k][l]) {
                        aliveCount++;
                    }
                }
            }
        }
        if (grid[x][y]) {
            aliveCount--;
        }
        if (grid[x][y] && aliveCount < 2) {
            newState = false;
        } else if (grid[x][y] && aliveCount > 3) {
            newState = false;
        } else if (!grid[x][y] && aliveCount == 3) {
            newState = true;
        } else {
            newState = grid[x][y];
        }
        return newState;
    }
}

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int n = 20;
        int m = 20;
        boolean[][] grid = new boolean[n][m];
        initialize(grid);
        Thread[][] cells = new Thread[n][m];
        CyclicBarrier barrier = new CyclicBarrier(n * m, new Runnable() {
            @Override
            public void run() {
                System.out.println("======================");
                System.out.println();
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        if (grid[i][j]) {
                            System.out.printf("*");
                        } else {
                            System.out.printf(".");
                        }
                    }
                    System.out.println();
                }
                System.out.println();
            }
        });
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                cells[i][j] = new Thread(new Cell(grid, i, j, barrier, 3));
                cells[i][j].start();
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
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
