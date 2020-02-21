import java.util.*;
import java.io.*;

// http://www.usaco.org/index.php?page=viewproblem2&cpid=509
// TODO: Memory Exceeded

class AllAboutTheBase {
    public static void main(String...args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("whatbase.in")));
            String line = br.readLine().trim();
            String n1 = line.split(" ")[0];
            String n2 = line.split(" ")[1];
            br.close();

            while (n1.length() < 3) {
                n1 = "0" + n1;
            }

            while (n2.length() < 3) {
                n2 = "0" + n2;
            }

            HashMap<Long, Integer> seen = new HashMap<>();
            for (int base = 10; base <= 15000; ++base) {
                long sum = 0;
                for (int i = 0; i < 3; ++i) {
                    sum += (n1.charAt(i) - '0') * Math.pow(base, 2 - i);
                }
                seen.put(sum, base);
            }
            
            int first = -1, second = -1;
            for (int base = 10; base <= 15000; ++base) {
                long sum = 0;
                for (int i = 0; i < 3; ++i) {
                    sum += (n2.charAt(i) - '0') * Math.pow(base, 2 - i);
                }
                if (seen.containsKey(sum)) {
                    first = seen.get(sum);
                    second = base;
                    break;
                }
            }

            FileWriter fw = new FileWriter(new File("whatbase.out"));
            fw.write(String.valueOf(first) + " " + String.valueOf(second));
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}