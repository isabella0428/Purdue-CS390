import java.util.*;

class Solution {
    private List<List<String>> ans = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        // First row queen in which column
        for (int j = 0; j < n; ++j) {
            List<Integer> placed = new ArrayList<>();
            placed.add(j);
            findQueen(1, placed, n);
        }
        return ans;
    }

    private void findQueen(int row, List<Integer> placed, int n) {
        if (row == n) {
            List<String> solution = new ArrayList<>();

            for (int i = 0; i < n; ++i) {
                String temp = "";
                for (int j = 0; j < n; ++j) {
                    if (j == placed.get(i)) {
                        temp += 'Q';
                    } else {
                        temp += '.';
                    }
                }

                solution.add(temp);
            }
            ans.add(solution);
            return;
        }

        for (int i = 0; i < n; ++i) {
            if (placed.contains(i))
                continue;

            // Check Diagnose
            Boolean flag = true;
            for (int k = 0; k < row; ++k) {
                if (Math.abs(placed.get(k) - i) == Math.abs(row - k)) {
                    flag = false;
                    break;
                }
            }

            if (!flag)
                continue;

            ArrayList<Integer> t = new ArrayList<>();
            for (int p : placed) {
                t.add(p);
            }
            t.add(i);
            findQueen(row + 1, t, n);
        }
        return;
    }
}