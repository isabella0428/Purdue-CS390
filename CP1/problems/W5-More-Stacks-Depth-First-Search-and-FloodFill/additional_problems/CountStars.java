import java.io.*;

// https://open.kattis.com/problems/countingstars

class CountStars {
    public static void main(String...args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String line;
            
            int time = 1;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                int row = Integer.parseInt(line.split(" ")[0]);
                int col = Integer.parseInt(line.split(" ")[1]);

                char[][] grid = new char[row][col];

                for (int i = 0; i < row; ++i) {
                    line = br.readLine().trim();
                    int j = 0;
                    for (char c : line.toCharArray()) {
                        grid[i][j++] = c;
                    }
                }

                int ans = 0;
                for (int i = 0; i < row; ++i) {
                    for (int j = 0; j < col; ++j) {
                        if (grid[i][j] == '-') {
                            ++ans;
                            dfs(i, j, grid);
                        }
                    }
                }
                System.out.println(String.format("Case %d: %d", time, ans));
                ++time;
            }
            

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void dfs(int i, int j, char[][] grid) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != '-')
            return;
        
        grid[i][j] = '#';
        
        int delta_x[] = new int[]{1, -1, 0, 0};
        int delta_y[] = new int[]{0, 0, 1, -1};
        for (int d = 0; d < 4; ++d) {
            dfs(i + delta_x[d], j + delta_y[d], grid);
        }
        return;
    }
}