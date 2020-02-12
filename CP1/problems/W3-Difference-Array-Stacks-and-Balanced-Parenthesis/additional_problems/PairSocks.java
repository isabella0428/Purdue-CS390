import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

// https://open.kattis.com/problems/pairingsocks

class PairSocks {
    public static void main(String...args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String line = br.readLine().trim();
            int n_pairs = Integer.parseInt(line);

            ArrayDeque<Integer> org_stack = new ArrayDeque<>();
            ArrayDeque<Integer> add_stack = new ArrayDeque<>();
            line = br.readLine().trim();
            for (String num : line.split(" ", 2 * n_pairs)) {
                org_stack.addLast(Integer.parseInt(num));
            }

            Boolean flag = true;
            int count = 0;
            while(true) {
                int left = 0, right = 0;
                while(org_stack.size() > 0) {
                    if (add_stack.size() == 0) {
                        int first = org_stack.removeFirst();
                        add_stack.addFirst(first);
                        count++;
                        continue;
                    }

                    left = org_stack.removeFirst();
                    right = add_stack.getFirst();
                    if (right == left) {
                        flag = false;
                        count ++;
                        add_stack.removeFirst();
                        continue;
                    } else {
                        count ++;
                        add_stack.addFirst(left);
                    }
                }
                
                ArrayDeque<Integer> temp = new ArrayDeque<>();
                temp = add_stack;
                add_stack = org_stack;
                org_stack = temp;
                if (flag) break;
                flag = true;
            }

            if (org_stack.size() == 0) {
                System.out.println(count);
            } else {
                System.out.println("impossible");
            }


        }   catch (IOException e) {
            e.printStackTrace();
        }
    }
}