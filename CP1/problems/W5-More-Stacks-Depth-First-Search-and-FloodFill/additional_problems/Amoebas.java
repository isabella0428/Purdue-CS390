import java.io.*;

// https://open.kattis.com/problems/amoebas

class Amoebas {
    public static void main(String...args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String line = br.readLine().trim();
            int row = Integer.parseInt(line.split(" ")[0]);
            int col = Integer.parseInt(line.split(" ")[1]);

            char[][] grid = new char[row][col];
            int i = 0, j = 0;
            for (i = 0; i < row; ++i) {
                line = br.readLine();
                j = 0;
                for (char c : line.toCharArray()) {
                    grid[i][j++] = c;
                }
            }

            int ans = 0;
            for (i = 0; i < row; ++i) {
                for (j = 0; j < col; ++j) {
                    if (grid[i][j] == '#') {
                        ++ans;
                        dfs(i, j, grid);
                    }
                }
            }

            System.out.println(ans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void dfs(int i, int j, char[][] grid) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != '#') {
            return;
        }

        grid[i][j] = '.';
        
        int []deltaX = new int[] {1, -1, 0, 0, 1, 1, -1, -1};
        int []deltaY = new int[] {0, 0, 1, -1, -1, 1, -1, 1};
        for (int d = 0; d < 8; ++d) {
            dfs(i + deltaX[d], j + deltaY[d], grid);
        }
    }
}