import java.util.*;

class Solution {
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        ArrayDeque<int[]> dqPacific = new ArrayDeque<>();
        ArrayDeque<int[]> dqAtlantic = new ArrayDeque<>();

        if (matrix.length == 0) {
            return new ArrayList<List<Integer>>();
        }

        int [][]pacific = new int[matrix.length][matrix[0].length];
        int [][]altantic = new int[matrix.length][matrix[0].length];

        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[0].length; ++j) {
                pacific[i][j] = -1;
                altantic[i][j] = -1;
            }
        }

        for (int j = 0; j < matrix[0].length; ++j) {
            pacific[0][j] = 1;
            dqPacific.addFirst(new int[]{0, j, 1});
        }

        for (int i = 0; i < matrix.length; ++i) {
            pacific[i][0] = 1;
            dqPacific.addFirst(new int[]{i, 0, 1});
        }

        for (int j = 0; j < matrix[0].length; ++j) {
            altantic[matrix.length - 1][j] = 1;
            dqAtlantic.addFirst(new int[] {matrix.length - 1, j, 1 });
        }

        for (int i = 0; i < matrix.length; ++i) {
            altantic[i][matrix[0].length - 1] = 1;
            dqAtlantic.addFirst(new int[] { i, matrix[0].length - 1, 1 });
        }

        while(dqPacific.size() > 0) {
            int[] nums = dqPacific.removeFirst();
            int x = nums[0];
            int y = nums[1];
            int step = nums[1];
            
            if (x < 0 || y < 0 || x >= matrix.length || y >= matrix[0].length) {
                continue;
            }

            int []deltaX = new int[]{1, -1, 0, 0};
            int []deltaY = new int[]{0, 0, 1, -1};
            for (int i = 0; i < 4; ++i) {
                int newX = x + deltaX[i];
                int newY = y + deltaY[i];
                if (newX < 0 || newY < 0 || newX >= matrix.length || newY >= matrix[0].length 
                   || matrix[newX][newY] < matrix[x][y] || pacific[newX][newY] != -1)
                    continue;
                dqPacific.addLast(new int[]{newX, newY, step + 1});
                pacific[newX][newY] = step + 1;
            }
        }

        while (dqAtlantic.size() > 0) {
            int[] nums = dqAtlantic.removeFirst();
            int x = nums[0];
            int y = nums[1];
            int step = nums[1];

            if (x < 0 || y < 0 || x >= matrix.length || y >= matrix[0].length) {
                continue;
            }

            int[] deltaX = new int[] { 1, -1, 0, 0 };
            int[] deltaY = new int[] { 0, 0, 1, -1 };
            for (int i = 0; i < 4; ++i) {
                int newX = x + deltaX[i];
                int newY = y + deltaY[i];
                if (newX < 0 || newY < 0 || newX >= matrix.length || newY >= matrix[0].length
                        || altantic[newX][newY] != -1 || matrix[newX][newY] < matrix[x][y])
                    continue;
                dqAtlantic.addLast(new int[] { newX, newY, step + 1 });
                altantic[newX][newY] = step + 1;
            }
        }

        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[0].length; ++j) {
                if (altantic[i][j] != -1 && pacific[i][j] != -1) {
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(i);
                    list.add(j);
                    ans.add(list);
                }
            }
        }
        
        return ans;
    }
}