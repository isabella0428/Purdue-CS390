import java.util.List;
import java.util.Vector;

class Solution {
    private Vector<String> ans = new Vector<>();

    public List<String> generateParenthesis(int n) {
        addParenthesis(1, "(", n);
        return ans;
    }

    private void addParenthesis(int left, String cur, int n) {
        if (cur.length() == 2 * n) {
            if (left == n) ans.add(cur);
            return;
        }

        if (2 * left < cur.length()) return;

        addParenthesis(left + 1, cur+'(', n);
        addParenthesis(left, cur + ')', n);
        return;
    }
}