import java.util.ArrayDeque;

class Solution856 {
    public int scoreOfParentheses(String S) {
        ArrayDeque<String> stack = new ArrayDeque<>();

        for(char c : S.toCharArray()) {
            if (c == '(') {
                stack.addLast(String.valueOf(c));
            } else {
                int count = 0;
                String last;
                while(!(last = stack.removeLast()).equals("(")) {
                    count += Integer.parseInt(last);
                }
                if (count == 0) {
                    count = 1;
                } else {
                    count *= 2;
                }
                stack.add(String.valueOf(count));
            }
        }

        int ans = 0;
        for (String a : stack) {
            ans += Integer.parseInt(a);
        }
        return ans;
    }
}