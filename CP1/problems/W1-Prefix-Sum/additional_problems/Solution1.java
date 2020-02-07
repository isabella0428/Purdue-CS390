// https://leetcode.com/problems/two-sum/submissions/

import java.util.HashMap;

class Solution1 {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int []ret = new int[2];

        for (int i = 0; i < nums.length; ++i) {
            int n = nums[i];
            int index = map.getOrDefault(target - n, -1);
            if (index != -1) {
                ret =  new int[] {index, i};
                break;
            }
            map.put(n, i);
        }
        return ret;
    }
}