import java.io.*;
import java.util.ArrayDeque;

// https://open.kattis.com/problems/grasshopper

class Grasshopper {
    public static void main(String...args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.equals("")) return;

                String[] nums = line.split(" ");
                int row = Integer.parseInt(nums[0]);
                int col = Integer.parseInt(nums[1]);
                int startX = Integer.parseInt(nums[2]) - 1;
                int startY = Integer.parseInt(nums[3]) - 1;
                int endX = Integer.parseInt(nums[4]) - 1;
                int endY = Integer.parseInt(nums[5]) - 1;

                ArrayDeque<int[]> ad = new ArrayDeque<>();
                ad.addLast(new int[]{startX, startY, 0});

                int seen[][] = new int[row][col];
                int ans = -1;
                while(ad.size() > 0) {
                    int[] point = ad.removeFirst();
                    int x = point[0];
                    int y = point[1];
                    int step = point[2];

                    if (x < 0 || x >= row || y < 0 || y >= col || seen[x][y] == 1) {
                        continue;
                    }

                    seen[x][y] = 1;

                    if (x == endX && y == endY) {
                        ans = step;
                        break;
                    }

                    ad.addLast(new int[] { x + 1, y + 2, step + 1 });
                    ad.addLast(new int[] { x + 1, y - 2, step + 1 });
                    ad.addLast(new int[] { x - 1, y + 2, step + 1 });
                    ad.addLast(new int[] { x - 1, y - 2, step + 1 });
                    ad.addLast(new int[] { x + 2, y + 1, step + 1 });
                    ad.addLast(new int[] { x + 2, y - 1, step + 1 });
                    ad.addLast(new int[] { x - 2, y + 1, step + 1 });
                    ad.addLast(new int[] { x - 2, y - 1, step + 1 });
                }

                if (ans == -1) {
                    System.out.println("impossible");
                } else {
                    System.out.println(String.valueOf(ans));
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}