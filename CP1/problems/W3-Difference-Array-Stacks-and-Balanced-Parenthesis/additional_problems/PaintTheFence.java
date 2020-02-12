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

// http://www.usaco.org/index.php?page=viewproblem2&cpid=226
// TODO: 8/10

class PaintTheFence {
    public static void main(String ... args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("paint.in")));
            String line = br.readLine().trim();
            int N = Integer.parseInt(line.split(" ", 2)[0]);
            int K = Integer.parseInt(line.split(" ", 2)[1]);
            HashMap<Integer, Integer> map = new HashMap<>();

            int p = 0;
            for (int i = 0; i < N; ++i) {
                line = br.readLine().trim();
                int delta = Integer.parseInt(line.split(" ", 2)[0]);
                String sign = line.split(" ", 2)[1];
                if (sign.equals("L")) {
                    delta *= -1;
                }
                int new_p = p + delta;
                int smaller = Math.min(new_p, p);
                int bigger = Math.max(new_p, p);

                int s_time = map.getOrDefault(smaller, 0);
                if (s_time == 0) {
                    map.put(smaller, 1);
                } else {
                    map.replace(smaller, s_time, s_time + 1);
                }

                int b_time = map.getOrDefault(bigger + 1, 0);
                if (b_time == 0) {
                    map.put(bigger + 1, -1);
                } else {
                    map.replace(bigger + 1, b_time - 1);
                    if (b_time - 1 == 0) {
                        map.remove(bigger + 1);
                    } 
                }

                p = new_p;
            }
            br.close();

            List<Integer> bounds = new ArrayList<>(map.keySet());
            Collections.sort(bounds);
            int paints = 0, prev = Integer.MIN_VALUE;
            int length = 0;
            for (int a : bounds) {
                paints += map.get(a);
                // System.out.println(String.format("Bound: %d Paint: %d", a, paints));
                if (paints >= K) {
                    if (prev == Integer.MIN_VALUE) {
                        prev = a;
                    }
                } else {
                    if (prev != Integer.MIN_VALUE) {
                        // System.out.println(String.format("Start:%d End:%d", prev, a-1));
                        length += (a - 1) - prev;
                        prev = Integer.MIN_VALUE;
                    }
                }
            }

            FileWriter fw = new FileWriter(new File("paint.out"));
            fw.write(String.valueOf(length));
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}