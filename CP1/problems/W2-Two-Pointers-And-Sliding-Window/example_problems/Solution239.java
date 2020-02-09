class Solution239 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] ans = new int[nums.length - k + 1];

        if (k > nums.length)    return null;
        if (k == 0) return new int[0];

        // Set Start Value
        for (int i = 0; i < nums.length - k + 1; ++i) {
            ans[i] = Integer.MIN_VALUE;
        }

        // Calculate ans[0]
        for (int i = 0; i < k; ++i) {
            ans[0] = Math.max(ans[0], nums[i]);
        }

        for (int i = 1; i < nums.length - k + 1; ++i) {
            if (nums[i + k - 1] > ans[i - 1]) {
                ans[i] = nums[i + k - 1];
            } else {
                if (nums[i - 1] < ans[i - 1]) {
                    ans[i] = ans[i - 1];
                } else {
                    for(int j = i; j < i + k; ++j) {
                        ans[i] = Math.max(ans[i], nums[j]);
                    }
                }
            }
        }
        return ans;
    }
}