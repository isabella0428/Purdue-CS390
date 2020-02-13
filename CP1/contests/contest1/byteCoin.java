import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// https://www.hackerrank.com/contests/cp-1-fall-2019-contest-1-late-submissions/challenges/bytecoin-happiness/submissions/code/1320680606

class byteCoin {
    public static void main(String...args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int N = Integer.parseInt(br.readLine().trim());
            int price[] = new int[N];

            if (N == 1 || N == 0) {
                System.out.println(0);
                return;
            }

            String line = br.readLine().trim();
            int j = 0;
            for (String num : line.split(" ")) {
                price[j] = Integer.parseInt(num);
                ++j;
            }

            long sum = 0;
            for (int i = 1; i < N; ++i) {
                int cur = price[i];
                int prev = price[i - 1];
                if (cur > prev) {
                    sum += Math.pow(cur - prev, 2);
                } else {
                    sum += Math.pow(cur - prev, 3);
                }
            }

            long remove_gain = 0, max_gain = Integer.MIN_VALUE;
            for (int k = 1;  k < N - 1; ++k) {
                remove_gain = 0;

                int cur = price[k];
                int next = price[k + 1];
                int prev = price[k - 1];

                if (cur >= prev) {
                    remove_gain -= Math.pow(cur - prev, 2);
                } else {
                    remove_gain -= Math.pow(cur - prev, 3);
                }

                if (next >= cur) {
                    remove_gain -= Math.pow(next - cur, 2);
                } else {
                    remove_gain -= Math.pow(next - cur, 3);
                }

                if (next >= prev) {
                    remove_gain += Math.pow(next - prev, 2);
                } else {
                    remove_gain += Math.pow(next - prev, 3);
                }

                max_gain = Math.max(max_gain, remove_gain);
            }

            long total_gain = sum + max_gain;
            System.out.println(total_gain);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}