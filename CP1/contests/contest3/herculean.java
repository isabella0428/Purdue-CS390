// https://www.hackerrank.com/contests/cp1-fall-2019-contest-3/challenges/herculean-numbers

import java.io.*;
import java.util.*;

class Solution {

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String line = br.readLine().trim();
            int N = Integer.parseInt(line);

            Long[] sixPower = new Long[1000];
            for (int i = 1; i <= 1000; ++i) {
                sixPower[i - 1] = (long) Math.pow(i, 6);
            }
            HashSet<Long> herculan = new HashSet<>();
            for (int i = 0; i < 1000; ++i) {
                for (int j = i + 1; j < 1000; ++j) {
                    herculan.add(sixPower[i] + sixPower[j]);
                }
            }

            for (int i = 1; Math.pow(i, 12) < Math.pow(10, 18); ++i) {
                herculan.add((long) Math.pow(i, 12));
            }

            List<Long> hNum = new ArrayList<>(herculan);
            Collections.sort(hNum);

            for (int i = 0; i < N; ++i) {
                line = br.readLine().trim();
                long down = Long.parseLong(line.split(" ")[0]);
                long up = Long.parseLong(line.split(" ")[1]);

                int downIdx = Collections.binarySearch(hNum, down);
                if (downIdx < -1) {
                    downIdx = -(downIdx + 1); // First larger element's index
                }

                int upIdx = Collections.binarySearch(hNum, up);
                if (upIdx < -1) {
                    upIdx = -(upIdx + 1) - 1; // Last smaller element's index
                }

                System.out.println(upIdx - downIdx + 1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}