import java.util.ArrayDeque;

class Solution150 {
    public int evalRPN(String[] tokens) {
        ArrayDeque<String> stack = new ArrayDeque<>();
        for (String s : tokens) {
            if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")) {
                int right = Integer.parseInt(stack.removeLast());
                int left = Integer.parseInt(stack.removeLast());
                switch (s) {
                    case "+":
                        stack.add(String.valueOf(left+right));
                        break;
                    case "-":
                        stack.add(String.valueOf(left-right));
                        break;
                    case "*":
                        stack.add(String.valueOf(left * right));
                        break;
                    case "/":
                        int value = left / right;
                        stack.add(String.valueOf(value));
                }
            } else {
                stack.addLast(s);;
            }
        }
        return Integer.parseInt(stack.getFirst());
    }
}