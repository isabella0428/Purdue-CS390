class Solution {
    private char[][]boxes;

    public int numIslands(char[][] grid) {
        boxes = grid;
        int num = 0;
        for (int i = 0; i < boxes.length; ++i) {
            for (int j = 0; j < boxes[0].length; ++j) {
                if (boxes[i][j] == '1') {
                    dfs(i, j);
                    ++num;
                }
            }
        }
        return num;
    }

    private void dfs(int i, int j) {
        if (i < 0 || j < 0 || i >= boxes.length || j >= boxes[0].length || boxes[i][j] == '0')
            return;
        
        boxes[i][j] = '0';
        dfs(i - 1, j);
        dfs(i + 1, j);
        dfs(i, j - 1);
        dfs(i, j + 1);
        return;
    }
}