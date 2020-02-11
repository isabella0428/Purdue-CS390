import java.util.ArrayDeque;

class Solution20 {
    public boolean isValid(String s) {
        ArrayDeque<String> stack = new ArrayDeque<>();

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.addLast(String.valueOf(c));
            } else {
                if (stack.size() == 0) return false;
                String last;
                switch(c) {
                    case ')':   if (!(last = stack.removeLast()).equals("(")) return false; break;
                    case '}':   if (!(last = stack.removeLast()).equals("{")) return false; break;
                    case ']':   if (!(last = stack.removeLast()).equals("[")) return false; break;
                }
            }
        }
        if (stack.size() == 0) return true;
        return false;
    }
}