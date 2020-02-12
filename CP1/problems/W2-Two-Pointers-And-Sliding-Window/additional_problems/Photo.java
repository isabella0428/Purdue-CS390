import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.Vector;
import java.util.Comparator;
import java.util.Collections;

// http://www.usaco.org/index.php?page=viewproblem2&cpid=280

class Photo {
    public static void main(String...args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("photo.in")));
            String line = br.readLine().trim();
            // int N = Integer.parseInt(line.split(" ", 2)[0]);
            int K = Integer.parseInt(line.split(" ", 2)[1]);
            Vector<int[]> bad = new Vector<>();

            for (int i = 0; i < K; ++i) {
                line = br.readLine().trim();
                int prev = Integer.parseInt(line.split(" ", 2)[0]);
                int next = Integer.parseInt(line.split(" ", 2)[1]);
                if (prev > next) {
                    int temp = prev;
                    prev = next;
                    next = temp;
                }
                bad.add(new int[]{prev, next});
            }
            br.close();

            Comparator<int[]> cmp = new Comparator<int[]>() {
                @Override
                public int compare(int[] a, int[] b) {
                    return a[0] - b[0];
                }
            };

            Collections.sort(bad, cmp);
            
            int ans = 1;
            int left = 0, right = 1;
            while (right < bad.size()) {
                int left_rvalue = bad.get(left)[1];
                while (right < bad.size() && left_rvalue > bad.get(right)[0]) {
                    if (bad.get(right)[1] < left_rvalue)
                        left_rvalue = bad.get(right)[1];
                    right++;
                }
                ans += 1;
                left = right;
            }

            File outFile = new File("photo.out");
            FileWriter fw = new FileWriter(outFile);
            fw.write(String.valueOf(ans));
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}