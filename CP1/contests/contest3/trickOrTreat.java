import java.io.*;

// https://www.hackerrank.com/contests/cp1-fall-2019-contest-3/challenges/trick-r-treat

class Solution {
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String line = br.readLine().trim();
            int row = Integer.parseInt(line.split(" ")[0]);
            int col = Integer.parseInt(line.split(" ")[1]);
            int K = Integer.parseInt(line.split(" ")[2]);

            int treat[][] = new int[row + 1][col + 1];
            int plan[][] = new int[row + 1][col + 1];
            int originalSum = 0;
            for (int r = 0; r < row; ++r) {
                for (int c = 0; c < col; ++c) {
                    line = br.readLine().trim();
                    treat[r][c] = Integer.parseInt(line.split(" ")[0]);
                    plan[r][c] = Integer.parseInt(line.split(" ")[1]);
                    if (plan[r][c] == 0) {
                        originalSum += treat[r][c];
                    }
                }
            }
            br.close();

            int maxTreat = originalSum;
            for (int s = 0; s < row; ++s) {
                int sum = 0;
                for (int i = 0; i < K; ++i) {
                    if (plan[s][i] == 1) {
                        sum += treat[s][i];
                    }
                }
                maxTreat = Math.max(maxTreat, originalSum + sum);

                for (int i = 1; i + K - 1 < col; ++i) {
                    if (plan[s][i - 1] == 1) {
                        sum -= treat[s][i - 1];
                    }
                    if (plan[s][i + K - 1] == 1) {
                        sum += treat[s][i + K - 1];
                    }
                    maxTreat = Math.max(maxTreat, originalSum + sum);
                }

            }
            System.out.println(maxTreat);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}