import java.util.ArrayDeque;

class Solution921 {
    public int minAddToMakeValid(String S) {
        ArrayDeque<Character> stack = new ArrayDeque<>();

        int ans = 0;
        for (char c: S.toCharArray()) {
            if (c == '(') {
                stack.addLast(Character.valueOf(c));
            } else {
                if (stack.size() == 0 || stack.getLast() != '(') {
                    ans++;
                } else {
                    stack.removeLast();
                }
            }
        }
        ans += stack.size();
        return ans;
    }
}