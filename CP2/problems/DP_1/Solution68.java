class Solution {
    public int minPathSum(int[][] grid) {
        int sum = 0;
        for (int i = 0; i < grid.length; ++i) {
            sum += grid[i][0];
            grid[i][0] = sum;
        }

        sum = 0;
        for (int i = 0; i < grid[0].length; ++i) {
            sum += grid[0][i];
            grid[0][i] = sum;
        }

        for (int i = 1; i < grid.length; ++i) {
            for (int j = 1; j < grid[0].length; ++j) {
                grid[i][j] = Math.min(grid[i - 1][j], grid[i][j - 1]) + grid[i][j];
            }
        }
        return grid[grid.length - 1][grid[0].length - 1];
    }
}