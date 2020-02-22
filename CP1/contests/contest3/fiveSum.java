import java.io.*;
import java.util.*;

// https://www.hackerrank.com/contests/cp1-fall-2019-contest-3/challenges/five-sum
// TODO: 5/6   TLE

class Solution {
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String line = br.readLine().trim();
            int N = Integer.parseInt(line);

            Long[][] nums = new Long[5][N];
            for (int i = 0; i < 5; ++i) {
                int j = 0;
                line = br.readLine().trim();
                for (String num : line.trim().split(" ")) {
                    nums[i][j++] = Long.parseLong(num);
                }
            }

            int K = Integer.parseInt(br.readLine().trim());
            br.close();

            HashMap<Long, Long[]> firstPartSum = new HashMap<>();
            for (int i = 0; i < N; ++i) {
                for (int j = 0; j < N; ++j) {
                    for (int k = 0; k < N; ++k) {
                        firstPartSum.put(nums[0][i] + nums[1][j] + nums[2][k],
                                new Long[] { nums[0][i], nums[1][j], nums[2][k] });
                    }
                }
            }

            Boolean flag = false;
            int delta = 0;
            Vector<Long> ans = new Vector<>();
            while (!flag) {
                for (int p = 0; p < N; ++p) {
                    for (int q = 0; q < N; ++q) {
                        if (firstPartSum.containsKey(K - delta - nums[3][p] - nums[4][q])) {
                            flag = true;
                            Long[] firstThree = firstPartSum.get(K - delta - nums[3][p] - nums[4][q]);
                            for (int i = 0; i < 3; ++i) {
                                ans.add(firstThree[i]);
                            }
                            ans.add(nums[3][p]);
                            ans.add(nums[4][q]);
                            break;
                        }

                        if (firstPartSum.containsKey(K + delta - nums[3][p] - nums[4][q])) {
                            flag = true;
                            Long[] firstThree = firstPartSum.get(K + delta - nums[3][p] - nums[4][q]);
                            for (int i = 0; i < 3; ++i) {
                                ans.add(firstThree[i]);
                            }
                            ans.add(nums[3][p]);
                            ans.add(nums[4][q]);
                            break;
                        }
                    }
                    if (flag) {
                        break;
                    }
                }
                delta++;
            }

            System.out.print(ans.get(0));
            for(int i = 1; i < 5; ++i) {
                System.out.print(" " + ans.get(i));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
