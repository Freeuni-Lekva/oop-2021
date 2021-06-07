---
nav_order: 16
title: 16. სრედები / SwingUI, Cyclic barrier
parent: გვანცა
grand_parent: სემინარები
---

# 16

[კოდი](https://github.com/Freeuni-Lekva/oop-2021/tree/main/Content/Seminars/Gvantsa/16)

```java
    public static void main(String[] args) {
        int n = 20;
        int m = 20;
        boolean[][] grid = new boolean[n][m];
        initialize(grid);

    }

    public static void initialize(boolean[][] grid) {
        Random r = new Random();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = r.nextBoolean();
            }
        }
    }
```
