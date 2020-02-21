import java.io.*;
import java.util.*;

// http://www.usaco.org/index.php?page=viewproblem2&cpid=508
// 6/10 TLE


class CowRoutingII {
    public static void main(String...args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("cowroute.in")));
            String line = br.readLine();
            String[] nums = line.split(" ");
            int start = Integer.parseInt(nums[0]);
            int end = Integer.parseInt(nums[1]);
            int N = Integer.parseInt(nums[2]);

            HashMap<Integer, HashMap<Integer, Integer>> map = new HashMap<>();

            int cost[] = new int[N];
            for (int r = 0; r < N; ++r) {
                line = br.readLine().trim();
                cost[r] = Integer.parseInt(line.split(" ")[0]);
                int cityNum = Integer.parseInt(line.split(" ")[1]);

                int city[] = new int[cityNum];
                line = br.readLine().trim();
                int c = 0;
                for (String num : line.split(" ")) {
                    city[c++] = Integer.parseInt(num);
                }

                for (int i1 = 0; i1 < cityNum; ++i1) {
                    for (int i2 = i1 + 1; i2 < cityNum; ++i2) {
                        int c1 = city[i1];
                        int c2 = city[i2];
                        if (map.containsKey(c1)) {
                            HashMap<Integer, Integer> c1map = map.get(c1);
                            if (c1map.containsKey(c2)) {
                                c1map.replace(c2, c1map.get(c2), Math.min(c1map.get(c2), cost[r]));
                                map.replace(c1, map.get(c2), c1map);
                            } else {
                                c1map.put(c2, cost[r]);
                                map.replace(c1, map.get(c1), c1map);
                            }
                        } else {
                            HashMap<Integer, Integer> c1map = new HashMap<>();
                            c1map.put(c2, cost[r]);
                            map.put(c1, c1map);
                        }
                    }
                }
            }
            br.close();

            int ans = Integer.MAX_VALUE;
            HashMap<Integer, Integer> startMap =  map.get(start);
            if (startMap.containsKey(end)) {
                ans = startMap.get(end);
            } 

            for (int middle : startMap.keySet()) {
                if (map.containsKey(middle)) {
                    HashMap<Integer, Integer> middleMap = map.get(middle);
                    if (middleMap.containsKey(end)) {
                        ans = Math.min(startMap.get(middle) + middleMap.get(end), ans);
                    }
                }
            }

            System.out.println(map);

            if (ans == Integer.MAX_VALUE) {
                ans = -1;
            }

            FileWriter fw = new FileWriter(new File("cowroute.out"));
            fw.write(String.valueOf(ans));
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}