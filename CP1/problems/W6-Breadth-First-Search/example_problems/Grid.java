import java.io.*;
import java.util.ArrayDeque;

// https://open.kattis.com/problems/grid

class Grid {
    public static void main(String...args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String line = br.readLine().trim();
            int row = Integer.parseInt(line.split(" ")[0]);
            int col = Integer.parseInt(line.split(" ")[1]);

            int[][] grid = new int[row][col];
            for (int r = 0; r < row; ++r) {
                line = br.readLine();
                int j = 0;
                for (char c : line.toCharArray()) {
                    grid[r][j++] = Integer.parseInt(String.valueOf(c));
                }
            }

            ArrayDeque<int[]> ad = new ArrayDeque<>();
            ad.addLast(new int[]{0, 0, 0});

            int ans = -1;
            int[][] seen = new int[row][col];

            while(ad.size() > 0) {
                int[] points = ad.removeFirst();

                if (points[0] < 0 || points[0] >= row || points[1] < 0 || points[1] >= col || seen[points[0]][points[1]] == 1) {
                    continue;
                }

                if (points[0] == row - 1 && points[1] == col - 1) {
                    ans = points[2];
                    break;
                }

                seen[points[0]][points[1]] = 1;

                int []deltaX = new int[]{1, -1, 0, 0};
                int []deltaY = new int[]{0, 0, 1, -1};

                int k = grid[points[0]][points[1]];
                for (int i = 0; i < 4; ++i) {
                    int[] newPoint = new int[]{points[0] + deltaX[i] * k, points[1] + deltaY[i] * k, points[2] + 1};
                    ad.addLast(newPoint);
                }
            }

            System.out.println(ans);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}