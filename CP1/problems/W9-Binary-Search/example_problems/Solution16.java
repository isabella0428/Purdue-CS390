import java.util.*;

class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);

        int absDelta = Integer.MAX_VALUE;
        int ans = -1;
        for (int i = 0; i < nums.length; ++i) {
            int low = 0, high = nums.length - 1;
            int newTarget = target - nums[i];

            while (low < high) {
                if (low == i)
                    low++;
                if (high == i)
                    high--;
                if (low >= high) {
                    break;
                }
                int newSum = nums[low] + nums[high];
                if (Math.abs(newSum + nums[i] - target) < absDelta) {
                    absDelta = Math.abs(newSum + nums[i] - target);
                    ans = newSum + nums[i];
                }

                if (newSum == newTarget) {
                    break;
                }

                if (newSum > newTarget) {
                    high--;
                } else {
                    low++;
                }
            }
        }
        return ans;
    }
}