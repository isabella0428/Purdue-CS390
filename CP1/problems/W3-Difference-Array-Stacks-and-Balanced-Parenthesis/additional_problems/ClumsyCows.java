import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

// http://www.usaco.org/index.php?page=viewproblem2&cpid=190
// TODO: 9/16

class ClumsyCows {
    public static void main(String...args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("clumsy.in")));
            String line = br.readLine().trim();
            br.close();

            int left_count = 0, toggle = 0;
            for (char c : line.toCharArray()) {
                if (c == '(') {
                    left_count++;
                    continue;
                } 
                if (left_count > 0) {
                    left_count--;
                    continue;
                }
                left_count += 2; 
                toggle++;
            }
            toggle += left_count / 2;

            FileWriter fw = new FileWriter(new File("clumsy.out"));
            fw.write(String.valueOf(toggle));
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}