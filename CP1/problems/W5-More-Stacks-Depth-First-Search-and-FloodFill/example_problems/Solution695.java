class Solution695 {
    private int[][] boxes;

    public int maxAreaOfIsland(int[][] grid) {
        boxes = grid;
        int max_area = Integer.MIN_VALUE;
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[0].length; ++j) {
                if (boxes[i][j] == 1) {
                    max_area = Math.max(max_area, dfs(i, j));
                }
            }
        }
        return Math.max(max_area, 0);
    }
    
    private int dfs(int i, int j) {
        int up = 0, down = 0, left = 0, right = 0;
        boxes[i][j] = 0;

        if (i != 0 && boxes[i - 1][j] == 1) {
            up = dfs(i - 1, j);
        }

        if (i != boxes.length - 1 && boxes[i + 1][j] == 1) {
            down = dfs(i + 1, j);
        }

        if (j != 0 && boxes[i][j - 1] == 1) {
            left = dfs(i, j - 1);
        }

        if (j != boxes[0].length - 1 && boxes[i][j + 1] == 1) {
            right = dfs(i, j + 1);
        }

        return 1 + up + down + left + right;
    }
}