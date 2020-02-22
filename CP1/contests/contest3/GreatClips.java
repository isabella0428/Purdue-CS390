import java.io.*;
import java.util.*;

// https://www.hackerrank.com/contests/cp1-fall-2019-contest-3/challenges/great-clips
// TODO: 1/4   WA

class Solution {

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String line = br.readLine().trim();
            int N = Integer.parseInt(line);

            Vector<int[]> ad = new Vector<>();
            for (int i = 0; i < N; ++i) {
                line = br.readLine().trim();
                ad.add(new int[]{i, Integer.parseInt(line.split(" ")[0]), Integer.parseInt(line.split(" ")[1])});
            }

            Comparator<int[]> cmp = new Comparator<int[]>(){
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o1[1] - o2[1];
                }
            };
            Collections.sort(ad, cmp);

            int cur_time = 0;
            Vector<Integer> waitTime = new Vector<>();
            Vector<int[]> temp = new Vector<>();
            while(ad.size() > 0 || temp.size() > 0) {
                if (temp.size() == 0) {
                    cur_time = ad.get(0)[1];
                    temp.add(ad.remove(0));
                }
                
                int[] guest = temp.remove(0);
                waitTime.add(cur_time - guest[1]);

                int end_time = cur_time + guest[2];
                cur_time = end_time;
                
                while (ad.size() > 0 && ad.get(0)[1] < end_time) {
                    temp.add(ad.remove(0));
                }
                
                Comparator<int[]> comparator = new Comparator<int[]>() {
                    @Override
                    public int compare(int[] o1, int[] o2) {
                        return o1[0] - o2[0];
                    }
                };

                Collections.sort(temp, comparator);

                while (ad.size() > 0 && ad.get(0)[1] <= end_time) {
                    temp.add(ad.remove(0));
                }
            }

            if (N % 2 == 1) {
                System.out.println(waitTime.get((N + 1) / 2 - 1));
            } else {
                System.out.println((double)(waitTime.get(N / 2 - 1) + waitTime.get(N / 2)) / 2);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}