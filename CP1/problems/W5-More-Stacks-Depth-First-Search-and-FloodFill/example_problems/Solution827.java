import java.util.HashSet;

class Solution827 {
    private int[][] boxes;

    public int largestIsland(int[][] grid) {
        boxes = grid;

        int rows = grid.length;
        int cols = grid[0].length;
        int[] areas = new int[rows * cols + 3];

        // Calculate the area of each island, Give them id
        int area_id = 2;
        int max_area = Integer.MIN_VALUE;
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (boxes[i][j] == 1) {
                    areas[area_id] = dfs(i, j, area_id);
                    max_area = Math.max(max_area, areas[area_id]);
                    area_id++;
                }
            }
        }

        int new_area = 0;
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                int up = 0, down = 0, left = 0, right = 0;

                HashSet<Integer> seen_id = new HashSet<>();
                if (boxes[i][j] == 0) {
                    if (i != 0 && boxes[i - 1][j] >= 2 && !seen_id.contains(boxes[i - 1][j])) {
                        up = areas[boxes[i - 1][j]];
                        seen_id.add(boxes[i - 1][j]);
                    }

                    if (i != rows - 1 && boxes[i + 1][j] >= 2 && !seen_id.contains(boxes[i + 1][j])) {
                        down = areas[boxes[i + 1][j]];
                        seen_id.add(boxes[i + 1][j]);
                    }

                    if (j != 0 && boxes[i][j - 1] >= 2 && !seen_id.contains(boxes[i][j - 1])) {
                        left = areas[boxes[i][j - 1]];
                        seen_id.add(boxes[i][j - 1]);
                    }

                    if (j != cols - 1 && boxes[i][j + 1] >= 2 && !seen_id.contains(boxes[i][j + 1])) {
                        right = areas[boxes[i][j + 1]];
                        seen_id.add(boxes[i][j + 1]);
                    }

                    new_area = 1 + up + down + left + right;
                    max_area = Math.max(new_area, max_area);
                }
            }
        }
        return Math.max(max_area, new_area);
    }

    private int dfs(int i, int j, int area_id) {
        if (i < 0 || j < 0 || i >= boxes.length || j >= boxes[0].length || boxes[i][j] != 1)
            return 0;

        boxes[i][j] = area_id;
        return 1 + dfs(i - 1, j, area_id) + dfs(i + 1, j, area_id) + dfs(i, j - 1, area_id) + dfs(i, j + 1, area_id);
    }
}