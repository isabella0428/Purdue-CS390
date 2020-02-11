import java.util.ArrayDeque;;

class Solution735 {
    public int[] asteroidCollision(int[] asteroids) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        for (int a : asteroids) {
            if (stack.size() == 0 || a > 0 || stack.getLast() < 0) {
                stack.addLast(Integer.valueOf(a));
            } else {
                int opponent;

                while ((opponent = stack.getLast()) <= Math.abs(a)) {
                    if (opponent == Math.abs(a)) {
                        stack.removeLast();
                        break;
                    }

                    stack.removeLast();

                    if (stack.size() == 0 || stack.getLast() < 0) {
                        stack.addLast(a);
                        break;
                    }
                }
            }
        }
        
        int[] ans = new int[stack.size()];
        for (int t = ans.length - 1; t >= 0; --t) {
            ans[t] = stack.removeLast();
        }
        return ans;
    }
}