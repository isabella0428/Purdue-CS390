import java.io.*;
import java.util.*;

// https://www.hackerrank.com/contests/cp1-fall-2019-contest-2/challenges/catchingfear/
// TODO: 3/6    3 WA ->still buggy

class Solution {

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String line = br.readLine().trim();
            String nums[] = line.split(" ");
            int row = Integer.parseInt(nums[0]);
            int col = Integer.parseInt(nums[1]);
            int A = Integer.parseInt(nums[2]);

            int[][] grid = new int[row][col];
            for (int i = 0; i < row; ++i) {
                line = br.readLine().trim();
                int j = 0;
                for (char c : line.toCharArray()) {
                    grid[i][j++] = c - '0';
                }
            }
            br.close();

            HashMap<Integer, Integer> timePeople = new HashMap<>();
            timePeople.put(0, 0);
            ArrayDeque<int[]> ad = new ArrayDeque<>();
            for (int i = 0; i < row; ++i) {
                for (int j = 0; j < col; ++j) {
                    if (grid[i][j] == 0)
                        ad.addLast(new int[]{i, j, 0});
                }
            }

            while (ad.size() > 0) {
                ArrayDeque<int[]> backUp = new ArrayDeque<>();
                Vector<int[]> changed = new Vector<>();
                while(ad.size() > 0) {
                    int[] array = ad.removeFirst();
                    int i = array[0];
                    int j = array[1];
                    int time = array[2];

                    if (i < 0 || i >= row|| j < 0 || j >= col || grid[i][j] == -1) {
                        continue;
                    }

                    double count = 0, fearNum = 0;
                    int[] deltaX = new int[]{1, -1, 0, 0};
                    int[] deltaY = new int[]{0, 0, 1, -1};
                    for (int d = 0; d < 4; ++d) {
                        int ans = testNeighbour(i + deltaX[d], j + deltaY[d], grid);
                        if (ans >= 0) {
                            count ++;
                            if (ans == 1)
                                fearNum++;
                        }
                    }

                    if (count == 0 || (fearNum / count) < (grid[i][j] / 4.0)) {
                        continue;
                    }

                    changed.add(new int[]{i, j});
                    
                    if (timePeople.containsKey(time)) {
                        timePeople.replace(time, timePeople.get(time), timePeople.get(time) + 1);
                    } else {
                        timePeople.put(time, timePeople.get(time - 1) + 1);
                    }

                    if (timePeople.get(time)>= A) {
                        System.out.println(time);
                        return;
                    }

                    for (int d = 0; d < 4; ++d) {
                        backUp.addLast(new int[]{i + deltaX[d], j + deltaY[d], time + 1});
                    }
                }

                while(backUp.size() > 0) {
                    ad.addLast(backUp.removeFirst());
                }

                for (int a[] : changed) {
                    grid[a[0]][a[1]] = -1;
                }
            }
            System.out.println("To victory and beyond!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int testNeighbour(int i, int j, int [][]grid) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid.length) {
            return -1;
        }

        if (grid[i][j] != -1) {
            return 0;
        }

        return 1;
    }
}