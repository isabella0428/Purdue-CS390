import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

// http://www.usaco.org/index.php?page=viewproblem2&cpid=341

class Goldilocks {
    public static void main(String...args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("milktemp.in")));
            String line = br.readLine().trim();
            String[] nums = line.split(" ", 4);
            int N = Integer.parseInt(nums[0]);
            int X = Integer.parseInt(nums[1]);
            int Y = Integer.parseInt(nums[2]);
            int Z = Integer.parseInt(nums[3]);

            HashMap<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < N; ++i) {
                line = br.readLine().trim();
                int start = Integer.parseInt(line.split(" ", 2)[0]);
                int end = Integer.parseInt(line.split(" ", 2)[1]);

                if (end < start) {
                    int temp = start;
                    start = end;
                    end = temp;
                }

                int s_time = map.getOrDefault(start, 0);
                if (s_time == 0) {
                    map.put(start, Y - X);
                } else {
                    map.replace(start, s_time, s_time + Y - X);
                }

                int b_time = map.getOrDefault(end + 1, 0);
                if (b_time == 0) {
                    map.put(end + 1, Z - Y);
                } else {
                    map.replace(end + 1, b_time + Z - Y);
                    if (b_time - 1 == 0) {
                        map.remove(end + 1);
                    }
                }
            }
            br.close();

            int total = N * X, max_total = Integer.MIN_VALUE;
            List<Integer> seen = new ArrayList<>(map.keySet());
            Collections.sort(seen);

            for (int a : seen) {
                total += map.get(a);
                max_total = Math.max(max_total, total);
            }

            FileWriter fw = new FileWriter(new File("milktemp.out"));
            fw.write(String.valueOf(max_total));
            fw.close();

        } catch (IOException e) {

        }



    }
}