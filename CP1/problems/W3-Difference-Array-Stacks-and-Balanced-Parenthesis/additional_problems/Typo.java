import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

// http://www.usaco.org/index.php?page=viewproblem2&cpid=188
class Typo {
    public static void main(String...args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("typo.in")));
            String line = br.readLine().trim();
            br.close();

            int left_count = 0, right_count = 0, left_paren = 0, ans = 0;
            Boolean toggle = false;
            for (char c : line.toCharArray()) {
                if (c == '(') {
                    left_paren++;
                    if (left_paren >= 2) left_count++;
                } else {
                    right_count ++;
                    if (left_paren <= 0) {
                        ans = right_count;
                        left_paren += 2;
                        toggle = true;
                    }
                    left_paren--;
                    if (left_paren < 2) left_count = 0;
                }
            }

            if (toggle && left_paren != 0) ans = 0;
            if (left_paren == 2) ans += left_count;

            FileWriter fw = new FileWriter(new File("typo.out"));
            fw.write(String.valueOf(ans));
            fw.close();

        } catch (IOException e) {

        }
        

    }
}