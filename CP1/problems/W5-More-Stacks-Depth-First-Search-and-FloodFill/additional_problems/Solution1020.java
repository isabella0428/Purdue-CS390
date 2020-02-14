class Solution {
    public int numEnclaves(int[][] A) {
        int i = 0, j = 0;
        for (j = 0; j < A[0].length; ++j) {
            if (A[i][j] == 1)
                dfs(i, j, A);
        }

        i = A.length - 1;
        for (j = 0; j < A[0].length; ++j) {
            if (A[i][j] == 1)
                dfs(i, j, A);
        }

        j = 0;
        for (i = 0; i < A.length; ++i) {
            if (A[i][j] == 1)
                dfs(i, j, A);
        }

        j = A[0].length - 1;
        for (i = 0; i < A.length; ++i) {
            if (A[i][j] == 1)
                dfs(i, j, A);
        }

        int ans = 0;
        for (i = 0; i < A.length; ++i) {
            for (j = 0; j < A[0].length; ++j) {
                if (A[i][j] == 1) {
                    ans += dfs(i, j, A);
                }
            }
        }
        return ans;
    }

    private int dfs(int i, int j, int[][] A) {
        if (i < 0 || j < 0 || i >= A.length || j >= A[0].length || A[i][j] == 0) {
            return 0;
        }

        A[i][j] = 0;
        return 1 + dfs(i - 1, j, A) + dfs(i + 1, j, A) + dfs(i, j - 1, A) + dfs(i, j + 1, A);
    }
}