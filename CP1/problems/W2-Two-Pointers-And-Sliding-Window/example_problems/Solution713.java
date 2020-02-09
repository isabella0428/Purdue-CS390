class Solution713 {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k == 0) {return 0;}
        int pro = 1, start = 0, ret = 0;
        for(int end = 0; end < nums.length; ++end) {
            pro *= nums[end];

            while (start <= end && pro >= k) {
                pro /= nums[start];
                start ++;
            }

            if (pro >= k) continue;
            ret += end - start + 1;
        }
        return ret;
    }
}