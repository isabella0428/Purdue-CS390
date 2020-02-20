class Solution {
    public int[] searchRange(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int pos = -1;
        while (left >= 0 && right >= 0 && left < nums.length && right < nums.length && left <= right) {
            int middle = (left + right) / 2;
            if (nums[middle] == target) {
                pos = middle;
                break;
            }
            if (nums[middle] < target) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }

        if (pos == -1) {
            return new int[] { -1, -1 };
        }

        left = pos;
        right = pos;
        while (left > 0 && nums[left - 1] == nums[pos]) {
            left--;
        }
        while (right < nums.length - 1 && nums[right + 1] == nums[pos]) {
            right++;
        }
        return new int[] { left, right };
    }
}