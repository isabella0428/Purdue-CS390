class Solution {
    public int findPeakElement(int[] nums) {
        int left = 0, right = nums.length - 1;
        int ans = -1;
        if (nums.length < 2) {
            return 0;
        }

        while (left <= right) {
            int middle = (left + right) / 2;
            if (middle < 0 || middle >= nums.length) {
                break;
            }

            if (middle == 0) {
                if (nums[middle] > nums[middle + 1]) {
                    ans = middle;
                }
            }

            if (middle == nums.length - 1) {
                if (nums[middle] > nums[middle - 1]) {
                    ans = middle;
                }
            }

            if (middle > 0 && middle < nums.length - 1 && nums[middle] > nums[middle - 1] && nums[middle] > nums[middle + 1]){
                ans = middle;
            }

            if (middle < nums.length - 1 && nums[middle] < nums[middle + 1]) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return ans;
    }
}