import java.io.*;

class islands {
    private static char [][]grids;

    public static void main(String...args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String line = br.readLine().trim();
            int row = Integer.parseInt(line.split(" ")[0]);
            int col = Integer.parseInt(line.split(" ")[1]);
            grids = new char[row][col];

            for (int i = 0; i < row; ++i) {
                line = br.readLine().trim();
                int j = 0;
                for (char c : line.toCharArray()) {
                    grids[i][j] = c;
                    ++j;
                }
            }

            int islandNum = 0;
            for (int i = 0; i < row; ++i) {
                for (int j = 0; j < col; ++j) {
                    if (grids[i][j] == 'L') {
                        dfs(i, j);
                        ++islandNum;
                    }
                }
            }

            System.out.println(islandNum);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void dfs(int i, int j) {
        if (i < 0 || i >= grids.length || j < 0 || j >= grids[0].length || grids[i][j] == 'W') {
            return;
        }

        grids[i][j] = 'W';

        int delta_x[] = {1, -1, 0,  0};
        int delta_y[] = {0,  0, 1, -1};
        for (int d = 0; d < 4; ++d) {
            dfs(i + delta_x[d], j + delta_y[d]);
        }
        return;
    }
}