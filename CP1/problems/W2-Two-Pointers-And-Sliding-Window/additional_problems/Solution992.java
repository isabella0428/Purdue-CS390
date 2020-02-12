import java.util.HashMap;

class Solution992 {
    public static int subarraysWithKDistinct(int[] A, int K) {
        int end = 0, start_left = 0, start_right = 0, count = 0;
        HashMap<Integer, Integer> seen1 = new HashMap<>();
        HashMap<Integer, Integer> seen2 = new HashMap<>();

        for (end = 0; end < A.length; ++end) {
            int times = seen1.getOrDefault(A[end], 0);
            if (times != 0) {
                seen1.replace(A[end], times, times + 1);
            } else {
                seen1.put(A[end], 1);
            }

            times = seen2.getOrDefault(A[end], 0);
            if (times != 0) {
                seen2.replace(A[end], times, times + 1);
            } else {
                seen2.put(A[end], 1);
            }

            while (start_left <= end && seen1.keySet().size() > K) {
                times = seen1.get(A[start_left]);
                if (times == 1) {
                    seen1.remove(A[start_left]);
                } else {
                    seen1.replace(A[start_left], times, times - 1);
                    if (seen1.get(A[start_left]) == 0) {
                        seen1.remove(A[start_left]);
                    }
                }
                ++start_left; 
            }

            while (start_right <= end && seen2.keySet().size() >= K) {
                times = seen2.get(A[start_right]);
                if (times == 1) {
                    seen2.remove(A[start_right]);
                } else {
                    seen2.replace(A[start_right], times, times - 1);
                    if (seen2.get(A[start_right]) == 0) {
                        seen2.remove(A[start_right]);
                    }
                }
                ++start_right;
            }

            count += start_right - start_left;
        }
        return count;
    }

    public static void main(String...args) {
        System.out.println(subarraysWithKDistinct(new int[]{ 1, 2, 1, 2, 3}, 2));
    }
}