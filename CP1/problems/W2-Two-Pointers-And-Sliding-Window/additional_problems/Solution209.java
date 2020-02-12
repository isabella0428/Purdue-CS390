class Solution209 {
    public int minSubArrayLen(int s, int[] nums) {
        int end = 0, sum = 0, start = 0;
        int min_len = nums.length + 1;
        
        for(end = 0; end < nums.length; ++end) {
            sum += nums[end];
            if (sum < s) continue;

            while(start <= end && sum >= s) {
                sum -= nums[start];
                start ++;
            }

            if (sum < s) {
                min_len = Math.min(min_len, end - start + 2);
            }
        }

        if (min_len > nums.length) return 0;
        return min_len;
    }
}