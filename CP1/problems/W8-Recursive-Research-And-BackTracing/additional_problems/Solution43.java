import java.util.*;

class Solution {
    private List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        findSubSet(new ArrayList<>(), nums, 0);
        return ans;
    }

    private void findSubSet(List<Integer> cur, int nums[], int start) {
        ans.add(cur);

        for (int i = start; i < nums.length; ++i) {
            ArrayList<Integer> temp = new ArrayList<>();
            for (int a : cur) {
                temp.add(a);
            }
            temp.add(nums[i]);
            findSubSet(temp, nums, i + 1);
        }
    }
}