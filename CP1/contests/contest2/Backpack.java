import java.io.*;
import java.util.*;

class Solution {

    public static void main(String[] args) {
        try {   
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String line = br.readLine().trim();
            int times = Integer.parseInt(line);


            for (int t = 0; t < times; ++t) {
                line = br.readLine().trim();

                Boolean notOkay = false;

                ArrayDeque<Character> stack = new ArrayDeque<>();
                for (char ch : line.toCharArray()) {
                    if (ch == '$' || ch == '@' || ch == '#') {
                        stack.addLast(ch);
                        continue;
                    }

                    if (ch == '=') {
                        continue;
                    }

                    char target = '0';
                    if (ch == 'd') {
                        target = '$';
                    }

                    if (ch == 'n') {
                        target = '@';
                    }

                    if (ch == 't') {
                        target = '#';
                    }

                    Boolean flag = false;
                    while(stack.size() > 0) {
                        char c = stack.removeLast();
                        if (c == target) {
                            flag = true;
                            break;
                        }
                    }
                    if (!flag) {
                        System.out.println("-1 -1 -1");
                        notOkay = true;
                        break;
                    }
                }

                if (notOkay) continue;

                int gold = 0, amber = 0, crystal = 0;
                while(stack.size() > 0) {
                    char c = stack.removeLast();
                    if (c == '$') {
                        gold++;
                    }

                    if (c == '@') {
                        amber++;
                    }

                    if (c == '#') {
                        crystal++;
                    }
                }

                System.out.println(String.valueOf(gold) + " " + String.valueOf(amber) + " " + String.valueOf(crystal));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}