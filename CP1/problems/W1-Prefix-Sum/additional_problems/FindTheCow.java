import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Vector;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.File;

class FindTheCow{
    public static void main(String ... args) {
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream("cowfind.in")));
            String line = bf.readLine().trim();
            bf.close();

            Vector<Integer> front_leg = new Vector<>(); // ))
            Vector<Integer> hind_leg = new Vector<>();  // ((

            for(int i = 0; i < line.length() - 1; ++i) {
                Character cur = line.charAt(i);
                Character next = line.charAt(i + 1);

                if(cur == '(' && next == '(') {
                    hind_leg.add(i);
                }

                if(cur == ')' && next == ')') {
                    front_leg.add(i);
                }
            }

            int count = 0, j = 0;
            for (int i = 0; i < hind_leg.size(); ++i) {
                int x = hind_leg.get(i);
                while (j < front_leg.size()) {
                    int y = front_leg.get(j);
                    if (y > x) break;
                    ++j;
                }
                count += front_leg.size() - j;
            }

            File outFile = new File("cowfind.out");
            FileWriter fw = new FileWriter(outFile);
            fw.write(String.valueOf(count));
            fw.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}