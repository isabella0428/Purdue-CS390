import java.util.HashSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

class MeetInTheMiddle {
    /*
     * Given a set of n integers where n <= 40. Each of them is at most 1012, find
     * the maximum subset sum less than or equal S where S <= 1018. 
     * Input : set[] = {38, 34, 18, 12, 6, 5} and S = 42
     * Output : 41, which is sum of {18, 12, 6, 5}
     */
    public static int getMaximumSum(int[] num, int K) {
        int half = (num.length - 1) / 2;
        HashSet<Integer> sums = new HashSet<>();

        for (int i = 0; i < (int)Math.pow(2, half + 1); ++i) {
            String temp = Integer.toBinaryString(i);
            int sum = 0;

            while(temp.length() < half + 1) {
                temp = '0' + temp;
            }

            // System.out.println(temp);
            for (int j = 0; j < temp.length(); ++j) {
                if (temp.charAt(j) == '1') {
                    sum += num[j];
                }
            }
            sums.add(sum);
        }

        ArrayList<Integer> seen = new ArrayList<>(sums);
        Collections.sort(seen);
        int[] array = new int[seen.size()];
        for (int i = 0; i< seen.size(); ++i) {
            array[i] = seen.get(i);
            System.out.println(array[i]);
        }
        System.out.println();

        int ans = Integer.MIN_VALUE;
        int delta = Integer.MAX_VALUE;

        for (int i = 0; i < (int)Math.pow(2, num.length - half - 1); ++i) {
            String temp = Integer.toBinaryString(i);
            int sum = 0;
            for (int j = 0; j < temp.length(); ++j) {
                if (temp.charAt(j) == '1') {
                    sum += num[half + 1 + j];
                }
            }
            
            int idx = Arrays.binarySearch(array, K - sum);

            if (sum > K) {
                continue;
            }

            if (idx > 0) {
                ans = K;
                break;
            }

            if (idx == -array.length - 1) {
                if (delta > K - array[array.length - 1] - sum) {
                    delta = K - array[array.length - 1] - sum;
                    ans = array[array.length - 1] + sum;
                }
            }

            if (idx == -1) {
                continue;
            }

            if (delta > K - array[-idx - 2] - sum) {
                delta = K - array[-idx - 2] - sum;
                ans = array[-idx - 2] + sum;
            }
        }
        return ans;
    }

    public static void main (String...args) {
        System.out.println(getMaximumSum(new int[]{38, 34, 18, 12, 6, 5}, 42));
    }
}