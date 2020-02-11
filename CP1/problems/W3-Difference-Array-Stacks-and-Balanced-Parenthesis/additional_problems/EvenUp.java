import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

// https://open.kattis.com/problems/evenup

class EvenUp {
    public static void main(String...args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String line = br.readLine().trim();
            int N = Integer.parseInt(line);

            line = br.readLine().trim();

            ArrayDeque<Integer> stack = new ArrayDeque<>();
            for (String num : line.split(" ", N)) {
                stack.addLast(Integer.parseInt(num));
            }
            br.close();

            Boolean flag = true;
            while (flag) {
                ArrayDeque<Integer> new_deque = new ArrayDeque<>();
                while (stack.size() >= 2) {
                    int first = stack.removeFirst();
                    int second = stack.getFirst();
                    int sum = first + second;
                    if (sum % 2 == 0) {
                        flag = false;
                        stack.removeFirst();
                    } else {
                        new_deque.addFirst(first);
                    }
                }

                if (stack.size() == 1)  new_deque.addFirst(stack.removeFirst());
                stack = new_deque.clone();

                if (flag || new_deque.size() < 2) break;
                flag = true;
            }
            
            System.out.println(stack.size());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}