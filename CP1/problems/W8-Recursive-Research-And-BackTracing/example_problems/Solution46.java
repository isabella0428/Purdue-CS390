import java.util.*;

class Solution {
    private List<List<Integer>> ans = new Vector<>();

    public List<List<Integer>> permute(int[] nums) {
        for (int n : nums) {
            ArrayList<Integer> temp = new ArrayList<>();
            temp.add(n);
            permutation(temp, nums);
        }
        return ans;
    }

    private void permutation(List<Integer> l, int[] nums) {
        if (l.size() == nums.length) {
            ans.add(l);
            return;
        }

        for (int n : nums) {
            if (!l.contains(n)) {
                ArrayList<Integer> temp = new ArrayList<>();
                for (int a : l) {
                    temp.add(a);
                }
                temp.add(n);
                permutation(temp, nums);
            }
        }
    }
}