import java.util.List;
import java.util.ArrayList;

class Solution {
    private List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        combine(new ArrayList<>(), n, k, 1);
        return ans;
    }

    private void combine(List<Integer> cur, int n, int k, int start) {
        if (cur.size() == k) {
            ans.add(cur);
            return;
        }

        for (int i = start; i <= n; ++i) {
            ArrayList<Integer> temp = new ArrayList<>();
            for (int a : cur) {
                temp.add(a);
            }
            temp.add(i);
            combine(temp, n, k, i + 1);
        }
    }
}