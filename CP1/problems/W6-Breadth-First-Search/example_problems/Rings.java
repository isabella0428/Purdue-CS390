import java.io.*;
import java.util.ArrayDeque;

// https://open.kattis.com/problems/rings2
// Wrong...Not quite understand why

class Rings {
    public static void main(String...args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String line = br.readLine().trim();
            int row = Integer.parseInt(line.split(" ")[0]);
            int col = Integer.parseInt(line.split(" ")[1]);

            String [][]grid = new String[row][col];
            for (int r = 0; r < row; ++r) {
                line = br.readLine().trim();
                int j = 0;
                for (char c : line.toCharArray()) {
                    grid[r][j++] = String.valueOf(c);
                }
            }

            ArrayDeque<int[]> ad = new ArrayDeque<>();
            for (int j = 0; j < col; ++j) {
                if (grid[0][j].equals("T")) {
                    ad.add(new int[] { 0, j, 1 });
                }
            }

            for (int j = 0; j < col; ++j) {
                if (grid[row - 1][j].equals("T")) {
                    ad.add(new int[] { row - 1, j, 1 });
                }
            }

            for (int i = 0; i < row; ++i) {
                if (grid[i][0].equals("T")) {
                    ad.add(new int[] { i, 0, 1 });
                }
            }

            for (int i = 0; i < row; ++i) {
                if (grid[i][col - 1].equals("T")) {
                    ad.add(new int[] { i, col - 1, 1 });
                }
            }

            for (int i = 1; i < row; ++i) {
                for (int j = 1; j < col; ++j) {
                    if(grid[i][j].equals(".")) {
                        ad.add(new int[] { i - 1, j, 1});
                        ad.add(new int[] { i + 1, j, 1 });
                        ad.add(new int[] { i, j + 1, 1 });
                        ad.add(new int[] { i, j - 1, 1 });
                    }
                }
            }

            int ans = 0;
            while (ad.size() > 0) {
                int[] point = ad.removeFirst();
                int x = point[0], y = point[1];
                if (x < 0 ||  x >= row || y < 0 || y >= col || !grid[x][y].equals("T")) {
                    continue;
                }

                grid[x][y] = String.valueOf(point[2]);

                int[] deltaX = new int[]{1, -1, 0, 0};
                int[] deltaY = new int[]{0, 0, 1, -1};
                for (int i = 0; i < 4; ++i) {
                    int[] newPoint = new int[]{x + deltaX[i], y + deltaY[i], point[2] + 1};
                    ans = Math.max(ans, point[2] + 1);
                    ad.add(newPoint);
                }
            }

            for (int i = 0; i < row; ++i) {
                for (int j = 0; j < col; ++j) {
                    if (ans < 10) {
                        System.out.print(".");
                    } else {
                        if (grid[i][j].equals(".") || Integer.parseInt(grid[i][j]) < 10) {
                            System.out.println("..");
                        } else {
                            System.out.println(".");
                        }
                    }
                    System.out.print(grid[i][j]);
                }
                System.out.print("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}