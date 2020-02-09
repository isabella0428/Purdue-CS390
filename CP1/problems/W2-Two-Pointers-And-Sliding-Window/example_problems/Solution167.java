class Solution167 {
    public int[] twoSum(int[] numbers, int target) {
        int l = 0, r = numbers.length - 1;
        int ret[] = new int[2];
        while (l < r) {
            int sum = numbers[l] + numbers[r];
            if (sum == target) {
                ret = new int[] { l + 1, r + 1 };
                break;
            }
            if (sum > target) {
                r--;
            } else {
                l++;
            }
        }
        return ret;
    }
}