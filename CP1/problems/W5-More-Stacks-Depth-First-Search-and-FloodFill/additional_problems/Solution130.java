import java.util.Vector;

class Solution {
    private char[][] grid;
    private Vector<int[]> notOkay;

    public void solve(char[][] board) {
        grid = board;
        notOkay = new Vector<>();

        if (board.length == 0)
            return;

        int i = 0, j = 0;
        for (j = 0; j < board[0].length; ++j) {
            if (grid[i][j] == 'O' && !ifContains(i, j)) {
                dfs(i, j, true);
            }
        }

        i = board.length - 1;
        for (j = 0; j < board[0].length; ++j) {
            if (grid[i][j] == 'O' && !ifContains(i, j)) {
                dfs(i, j, true);
            }
        }

        j = 0;
        for (i = 0; i < board.length; ++i) {
            if (grid[i][j] == 'O' && !ifContains(i, j)) {
                dfs(i, j, true);
            }
        }

        j = board[0].length - 1;
        for (i = 0; i < board.length; ++i) {
            if (grid[i][j] == 'O' && !ifContains(i, j)) {
                dfs(i, j, true);
            }
        }

        for (i = 1; i < grid.length - 1; ++i) {
            for (j = 1; j < grid[0].length - 1; ++j) {
                if (grid[i][j] == 'O' || !ifContains(i, j)) {
                    dfs(i, j, false);
                }
            }
        }
        board = grid;
        return;
    }

    private void dfs(int i, int j, Boolean flag) {
        if (i < 0 || j < 0 || i > grid.length - 1 || j > grid[0].length - 1 || grid[i][j] == 'X') {
            return;
        }

        if (ifContains(i, j))
            return;

        if (flag) {
            notOkay.add(new int[] { i, j });
        } else {
            grid[i][j] = 'X';
        }

        dfs(i + 1, j, flag);
        dfs(i - 1, j, flag);
        dfs(i, j + 1, flag);
        dfs(i, j - 1, flag);
        return;
    }

    private Boolean ifContains(int i, int j) {
        for (int[] a : notOkay) {
            if (a[0] == i && a[1] == j) {
                return true;
            }
        }
        return false;
    }
}