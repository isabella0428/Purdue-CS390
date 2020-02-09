import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Vector;

// http://www.usaco.org/index.php?page=viewproblem2&cpid=527

class Cow {
    public static void main(String ... args) {
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream("cow.in")));

            int n_ch = Integer.parseInt(bf.readLine().trim());
            String line = bf.readLine().trim();
            long c_count = 0, co_count = 0, cow_count = 0;
            for(int i = 0; i < n_ch; ++i) {
                Character c = line.charAt(i);
                switch(c) {
                    case 'C':
                        c_count += 1;
                        break;
                    case 'O':
                        co_count += c_count;
                        break;
                    case 'W':
                        cow_count += co_count;
                }
            }

            bf.close();

            File outFile = new File("cow.out");
            FileWriter fw = new FileWriter(outFile);
            fw.write(String.valueOf(cow_count));
            fw.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}