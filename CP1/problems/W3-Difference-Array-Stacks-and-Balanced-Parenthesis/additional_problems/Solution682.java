import java.util.ArrayDeque;

class Solution682 {
    public int calPoints(String[] ops) {
        int sum = 0;

        ArrayDeque<Integer> stack = new ArrayDeque<>();

        for (String s : ops) {
            int this_round = 0;
            if (s.equals("+")) {
                int prev_one = stack.removeLast();
                int prev_two = stack.getLast();
                stack.addLast(prev_one);
                this_round = prev_one + prev_two;
                stack.add(this_round);
            } else {
                if (s.equals("D")) {
                    this_round = 2 * stack.getLast();
                    stack.add(this_round);
                } else {
                    if (s.equals("C")) {
                        this_round = -stack.removeLast();
                    } else {
                        this_round = Integer.parseInt(s);
                        stack.add(this_round);
                    }
                }
            }
            sum += this_round;
        }
        return sum;
    }
}