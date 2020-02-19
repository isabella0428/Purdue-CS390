import java.io.*;
import java.util.*;

class Solution {
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String line = br.readLine().trim();
            int row = Integer.parseInt(line.split(" ")[0]);
            int col = Integer.parseInt(line.split(" ")[1]);

            char island[][] = new char[row][col];
            for (int r = 0; r < row; ++r) {
                line = br.readLine().trim();
                int j = 0;
                for (char ch : line.toCharArray()) {
                    island[r][j++] = ch;
                }
            }

            int islandId = 1;
            int[][] seen = new int[row][col];
            for (int r = 0; r < row; ++r) {
                for (int c = 0; c < col; ++c) {
                    if (island[r][c] != '0' && seen[r][c] == 0) {
                        dfs(r, c, island, islandId, seen);
                        islandId++;
                    }
                }
            }

            int maxS = -1;
            for (int i = 1; i <= islandId; ++i) {
                int size = 0;
                HashSet<Integer> got = new HashSet<>();
                for (int r = 0; r < row; ++r) {
                    for (int c = 0; c < col; ++c) {
                        if (seen[r][c] == i) {
                            size++;
                            got.add(island[r][c] - '0');
                        }
                    }
                }

                if (got.size() == 9) {
                    maxS = Math.max(maxS, size);
                }
            }
            System.out.println(maxS);

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private static void dfs(int r, int c, char[][] island, int islandId, int[][] seen) {
        if (r < 0 || r >= island.length || c < 0 || c >= island[0].length || island[r][c] == '0') {
            return;
        }

        if (seen[r][c] != 0) {
            return;
        }

        seen[r][c] = islandId;
        dfs(r + 1, c, island, islandId, seen);
        dfs(r - 1, c, island, islandId, seen);
        dfs(r, c + 1, island, islandId, seen);
        dfs(r, c - 1, island, islandId, seen);
    }
}