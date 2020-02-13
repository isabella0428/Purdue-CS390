import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

// https://www.hackerrank.com/contests/cp-1-fall-2019-contest-1-late-submissions/challenges/dilberts-hotel

class dilbertHotel {
    public static void main(String...args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int N = Integer.parseInt(br.readLine().trim());
            HashMap<Integer, Integer> map = new HashMap<>();

            String line;
            for (int i = 0; i < N; ++i) {
                line = br.readLine().trim();
                int rooms = Integer.parseInt(line.split(" ")[0]);
                int arrive = Integer.parseInt(line.split(" ")[1]);
                int leave = Integer.parseInt(line.split(" ")[2]) + arrive;

                int arrive_count = map.getOrDefault(arrive, 0);
                if (map.containsKey(arrive)) {
                    map.replace(arrive, arrive_count, arrive_count + rooms);
                } else {
                    map.put(arrive, rooms);
                }

                int leave_count = map.getOrDefault(leave, 0);
                if (map.containsKey(leave)) {
                    map.replace(leave, leave_count, leave_count - rooms);
                } else {
                    map.put(leave, -rooms);
                }
            }

            List<Integer> bounds = new ArrayList<>(map.keySet());
            Collections.sort(bounds);

            int sum = 0, max_rooms = Integer.MIN_VALUE;
            for (int b : bounds) {
                sum += map.get(b);
                max_rooms = Math.max(sum, max_rooms);
            }

            if (max_rooms < 0) max_rooms = 0;
            System.out.println(max_rooms);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}