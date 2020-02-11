import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.PriorityQueue;

// http://www.usaco.org/index.php?page=viewproblem2&cpid=104

class HaybaleStacking {
    public static void main(String...args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("stacking.in")));
            String line = br.readLine();
            int N_heaps = Integer.parseInt(line.split(" ", 2)[0]);
            int N_additions = Integer.parseInt(line.split(" ", 2)[1]);
            int []height_diff = new int[N_heaps + 1];

            for (int i = 0; i < N_additions; ++i) {
                line = br.readLine();
                int start = Integer.parseInt(line.split(" ", 2)[0]);
                int end = Integer.parseInt(line.split(" ", 2)[1]);
                height_diff[start]++;
                height_diff[end+1]--;
            }
            br.close();

            int k;
            if (N_heaps % 2 == 1) {
                k = (1 + N_heaps) / 2;
            } else {
                k = N_heaps / 2 + 1;
            }

            PriorityQueue<Integer> pq = new PriorityQueue<>();
            for (int i = 0; i < N_heaps; ++i) {
                int height = height_diff[i];
                if (i > 0) {
                    height += height_diff[i - 1];
                }
                pq.add(height);
                if (pq.size() >= k) {
                    pq.poll();
                }
            }

            int ans = 0;
            if (N_heaps % 2 == 1) {
                ans = pq.peek();
            } else {
                ans += pq.poll();
                ans += pq.poll();
            }

            File outFile = new File("stacking.out");
            FileWriter fw = new FileWriter(outFile);
            fw.write(String.valueOf(ans));
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}