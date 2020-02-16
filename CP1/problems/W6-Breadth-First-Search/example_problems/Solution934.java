import java.util.ArrayDeque;

class Solution {
    public static int shortestBridge(int[][] A) {
        ArrayDeque<int[]> island1 = new ArrayDeque<>();
        ArrayDeque<int[]> island2 = new ArrayDeque<>();

        int seen[][] = new int[A.length][A[0].length];
        for (int i = 0; i < A.length; ++i) {
            for (int j = 0; j < A[0].length; ++j) {
                if (A[i][j] == 1) {
                    if (island1.size() == 0){
                        island1 = dfs(i, j, new ArrayDeque<int[]>(), A, seen, true);
                    } else {
                        island2 = dfs(i, j, new ArrayDeque<int[]>(), A, seen, false);
                    }
                }
            }
        }

        // Connect island1 to island2
        int flap[][] = new int[A.length][A[0].length];
        while(island1.size() > 0) {
            int[] p = island1.removeFirst();
            int x = p[0];
            int y = p[1];

            seen[x][y] = 1;
            
            if (findInIsland2(x, y, island2)) {
                return flap[x][y] - 1;
            }

            int[] deltaX = new int[] { 1, -1, 0, 0  };
            int[] deltaY = new int[] { 0,  0, 1, -1 };
            for (int d = 0; d < 4; ++d) {
                int newX = x + deltaX[d], newY = y + deltaY[d];
                if (newX < 0 || newX >= A.length || newY  < 0 || newY >= A[0].length || seen[newX][newY] == 1)
                    continue;

                island1.addLast(new int[]{newX, newY});
                flap[newX][newY] = flap[x][y] + 1;
                seen[newX][newY] = 1;
            }
        }
        return -1;
    }

    private static ArrayDeque<int[]> dfs(int i, int j, ArrayDeque<int[]> ad, int[][]A, int[][] seen, Boolean flag) {
        if (i < 0 || i >= A.length || j < 0 || j >= A[0].length || A[i][j] != 1) {
            return ad;
        }

        if (flag) seen[i][j] = 1;
        ad.addLast(new int[]{i, j});
        A[i][j] = 0;

        int []deltaX = new int[]{1, -1, 0, 0};
        int []deltaY = new int[]{0, 0, 1, -1};
        for (int d = 0; d < 4; ++d) {
            dfs(i + deltaX[d], j + deltaY[d], ad, A, seen, flag);
        }
        return ad;
    }

    private static Boolean findInIsland2 (int i, int j, ArrayDeque<int[]> ad) {
        for (int p = 0; p < ad.size(); ++p) {
            int[] temp = ad.removeFirst();
            if (i == temp[0] && j == temp[1]) return true;
            ad.addLast(temp);
        }
        return false;
    }

    public static void main(String...args) {
        int n = shortestBridge(new int[][]{{1,1,1,1,1},{1,0,0,0,1},{1,0,1,0,1},{1,0,0,0,1},{1,1,1,1,1}});
        System.out.println(n);
    }
}