import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Vector;
import java.util.Comparator;
import java.util.Collections;

// 1/3  WA  no idea

class Solution {
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String line = br.readLine().trim();
            int nTests = Integer.parseInt(line);

            for (int i = 0; i < nTests; ++i) {
                line = br.readLine().trim();
                int employee = Integer.parseInt(line.split(" ")[0]);
                int bike = Integer.parseInt(line.split(" ")[1]);

                int dist[][] = new int[employee][bike];
                for (int e = 0; e < employee; ++e) {
                    line = br.readLine().trim();
                    int j = 0;
                    for (String num : line.split(" ")) {
                        dist[e][j++] = Integer.parseInt(num);
                    }
                }


                HashMap<Integer, Integer> map = new HashMap<>();
                HashSet<Integer> used = new HashSet<>();

                Comparator<int[]> cmp = new Comparator<int[]>() {
                    @Override
                    public int compare(int[]a, int[]b) {
                        return a[2] - b[2];
                    }
                };

                while(map.keySet().size() < employee) {
                    Vector<int[]> judge = new Vector<>();
                    for (int e = 0; e < employee; ++e) {
                        if (map.keySet().contains(e)) {
                            continue;
                        }

                        int minDist = Integer.MAX_VALUE;
                        int idx = -1;
                        for (int m = 0; m < bike; ++m) {
                            if (!used.contains(m) && minDist > dist[e][m]) {
                                idx = m;
                                minDist = dist[e][m];
                            }
                        }
                        judge.add(new int[] { e, idx, minDist });
                    }
                    Collections.sort(judge, cmp);

                    for (int[] temp : judge) {
                        if (!used.contains(temp[1])) {
                            map.put(temp[0], temp[1]);
                            used.add(temp[1]);
                        }
                    }
                }

                System.out.println(String.format("Case #%d", i));
                System.out.print(map.get(0));
                for (int t = 1; t < employee; ++t) {
                    System.out.print(" " + map.get(t));
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}