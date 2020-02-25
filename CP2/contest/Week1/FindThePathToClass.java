import java.io.*;

// https://www.xhackerrank.com/contests/cp-2-fall-2019-week-1-introduction-contest/challenges/find-the-way-to-the-class/
// 4/5  37.5

class Solution {

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String line = br.readLine().trim();
            int routes = Integer.parseInt(line.split(" ")[0]);
            int landmarks = Integer.parseInt(line.split(" ")[1]);

            int[][] dist = new int[landmarks][landmarks];
            for (int i = 0; i < landmarks; ++i) {
                for (int j = 0; j < landmarks; ++j) {
                    if (i == j) {
                        dist[i][j] = 0;
                        continue;
                    }
                    dist[i][j] = Integer.MAX_VALUE;
                }
            }

            for (int i = 0; i < routes; ++i) {
                line = br.readLine().trim();
                String[] num = line.split(" ");
                int start = Integer.parseInt(num[0]) - 1;
                int end = Integer.parseInt(num[1]) - 1;
                int length = Integer.parseInt(num[2]);

                if (start > end) {
                    int temp = start;
                    start = end;
                    end = temp;
                }

                dist[start][end] = Math.min(dist[start][end], length);
                for (int k = 0; k < start; ++k) {
                    if (dist[k][start] == Integer.MAX_VALUE)
                        continue;
                    dist[k][end] = Math.min(dist[k][start] + dist[start][end], dist[k][end]);;
                }
            }
            System.out.println(dist[0][landmarks - 1]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}