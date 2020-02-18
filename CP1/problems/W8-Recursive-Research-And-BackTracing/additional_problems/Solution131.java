import java.util.*;

class Solution {
    private List<List<String>> ans;

    public List<List<String>> partition(String s) {
        ans = new ArrayList<>();
        backtrace(0, new ArrayList<>(), s);
        return ans;
    }

    private void backtrace(int pos, List<String> list, String s) {
        if (pos == s.length()) {
            ans.add(new ArrayList<>(list));
            return;
        }

        for (int i = pos; i < s.length(); ++i) {
            String prev = s.substring(pos, i + 1);
            if (isPalindrome(prev)) {
                list.add(prev);
                backtrace(i + 1, list, s);
                list.remove(list.size() - 1);
            }
        }
    }

    private Boolean isPalindrome(String s) {
        int b = 0, e = s.length() - 1;
        while (b < e) {
            if (s.charAt(b++) != s.charAt(e--)) {
                return false;
            }
        }
        return true;
    }
}