import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.File;
import java.util.Comparator;
import java.util.Arrays;

// http://www.usaco.org/index.php?page=viewproblem2&cpid=413

class TheLazyCow {
    public static void main(String...args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("lazy.in")));
            String line = br.readLine().trim();
            int N = Integer.parseInt(line.split(" ", 2)[0]);
            int K = Integer.parseInt(line.split(" ", 2)[1]);

            int positions_grass[][] = new int[N][2];

            int max_position = Integer.MIN_VALUE;
            for (int i = 0; i < N; ++i) {
                line = br.readLine().trim();
                positions_grass[i][1] = Integer.parseInt(line.split(" ", 2)[0]);
                positions_grass[i][0] = Integer.parseInt(line.split(" ", 2)[1]);
                max_position = Math.max(positions_grass[i][0], max_position);
            }
            br.close();

            Comparator<int[]> cmp = new Comparator<int[]>() {
                @Override
                public int compare(int[] a, int[] b) {
                    return a[0] - b[0];
                }
            };
            Arrays.sort(positions_grass, cmp);

            int max_grass = Integer.MIN_VALUE;

            // Store prefix sum
            int prefix_sum[] = new int[N + 1];
            int sum = 0;
            for (int i = 1; i <= N; ++i) {
                sum += positions_grass[i - 1][1];
                prefix_sum[i] = sum;
            }

            if (max_position - positions_grass[0][0] <= 2 * K) {
                File outFile = new File("lazy.out");
                FileWriter fw = new FileWriter(outFile);
                fw.write(String.valueOf(sum));
                fw.close();
                return; 
            }

            int left = 0, right = 0;
            for (int p = positions_grass[0][0] + K; p <= max_position - K; ++p) {
                while (left <= p && p - positions_grass[left][0] > K) ++left;
                while (right < N && positions_grass[right][0] - p <= K ) right++;

                int grass = prefix_sum[right] - prefix_sum[left];
                max_grass = Math.max(max_grass, grass);
            }

            System.out.println(String.format("Left:%d Right:%d", left, right));

            File outFile = new File("lazy.out");
            FileWriter fw = new FileWriter(outFile);
            fw.write(String.valueOf(max_grass));
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}