import java.io.*;

// https://open.kattis.com/problems/10kindsofpeople

// Tried so hard to optimize speed
// 22/25    TLE   :(

class TenKindOfPeople {
    private static String[][] grid;
    public static void main(String...args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
            String line = br.readLine().trim();
            int row = Integer.parseInt(line.split(" ")[0]);
            int col = Integer.parseInt(line.split(" ")[1]);

            grid = new String[row][col];
            for (int i = 0; i < row; ++i) {
                line = br.readLine().trim();
                int j = 0;
                for (char c : line.toCharArray()) {
                    grid[i][j++] = String.valueOf(c);
                }
            }

            int id = 3;
            for (int i = 0; i < row; ++i) {
                for (int j = 0; j < col; ++j) {
                    if (grid[i][j].equals("0")) {
                        if (id % 2 == 1) id++;
                        dfs(i, j, "0", id);
                    }

                    if (grid[i][j].equals("1")) {
                        if (id % 2 == 0) id++;
                        dfs(i, j, "1", id);
                    }
                    ++id;
                }
            }

            int queries = Integer.parseInt(br.readLine().trim());
            for (int i = 0; i < queries; ++i) {
                line = br.readLine();
                String num[] = line.split(" ");
                int startX = Integer.parseInt(num[0]) - 1;
                int startY = Integer.parseInt(num[1]) - 1;
                int endX = Integer.parseInt(num[2]) - 1;
                int endY = Integer.parseInt(num[3]) - 1;

                Boolean ans = (grid[startX][startY].equals(grid[endX][endY]));

                if (ans) {
                    pw.println(Integer.parseInt(grid[startX][startY]) % 2 != 0 ? "decimal" : "binary");
                } else {
                    pw.println("neither");
                }
                pw.flush();
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void dfs(int i, int j, String num, int id) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || !grid[i][j].equals(num)) {
            return;
        }

        grid[i][j] = String.valueOf(id);
        
        int deltaX[] = new int[]{1, -1, 0, 0};
        int deltaY[] = new int[]{0, 0, 1, -1};
        for (int d = 0; d < 4; ++d) {
            dfs(i + deltaX[d], j + deltaY[d], num, id);
        }
    }
}