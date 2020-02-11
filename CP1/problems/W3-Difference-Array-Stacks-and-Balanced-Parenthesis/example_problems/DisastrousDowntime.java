import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

// https://open.kattis.com/problems/downtime

class DisastrousDowntime {
    public static void main(String...args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String line = br.readLine().trim();
            int N = Integer.parseInt(line.split(" ", 2)[0]);
            int K = Integer.parseInt(line.split(" ", 2)[1]);
            int []times =  new int[N];
            HashMap<Integer, Integer> map = new HashMap<>();

            for (int i = 0; i < N; ++i) {
                line = br.readLine().trim();
                times[i] = Integer.parseInt(line);
            }

            Arrays.sort(times);

            for (int t : times) {
                int count = map.getOrDefault(t, 0);
                if (count == 0) {
                    map.put(t, 1);
                } else {
                    map.replace(t, count, count+1);
                }

                count = map.getOrDefault(t + 1000, 0);
                if (count == 0) {
                    map.put(t+1000, -1);
                } else {
                    map.replace(t+1000, count, count-1);
                }
            }

            List<Integer> timestamps = new ArrayList<>();
            timestamps.addAll(map.keySet());
            Collections.sort(timestamps);

            int max_num = 0, prev = 0;
            for (int t : timestamps) {
                int sum = prev + map.get(t);
                max_num = Math.max(max_num, sum);
                prev = sum;
            }

            int ans = max_num / K;
            if (max_num % K > 0)
                ans++;
            System.out.println(ans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }   
}