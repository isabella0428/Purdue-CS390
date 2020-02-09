import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.File;

// http://www.usaco.org/index.php?page=viewproblem2&cpid=572

class BreedCounting {
    public static void main(String ... args) {
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream("bcount.in")));
            String line;
            line = bf.readLine();

            int n_cows = Integer.parseInt(line.split(" ", 2)[0]);
            int n_queries = Integer.parseInt(line.split(" ", 2)[1]);
            int[] breed_cows = new int[n_cows];

            int[] ones = new int[n_cows + 1];
            int[] twos = new int[n_cows + 1];
            int[] threes = new int[n_cows + 1];
            for(int i = 0; i < n_cows; ++i) {
                breed_cows[i] = Integer.parseInt(bf.readLine().trim());
                ones[i + 1] = ones[i];
                twos[i + 1] = twos[i];
                threes[i + 1] = threes[i];
                if(breed_cows[i] == 1) {
                    ones[i + 1] = ones[i] + 1;
                } else {
                    if (breed_cows[i] == 2) {
                        twos[i + 1]  = twos[i] + 1;
                    } else {
                        threes[i + 1] = threes[i] + 1;
                    }
                }
            }

            File outFile = new File("bcount.out");
            FileWriter fw = new FileWriter(outFile);

            for(int i = 0; i < n_queries; ++i) {
                line = bf.readLine();
                int start = Integer.parseInt(line.split(" ", 2)[0].trim()) - 1;
                int end   = Integer.parseInt(line.split(" ", 2)[1].trim()) - 1;
                fw.write(String.valueOf(ones[end + 1] - ones[start]) + " ");
                fw.write(String.valueOf(twos[end + 1] - twos[start]) + " ");
                fw.write(String.valueOf(threes[end + 1] - threes[start]) + "\n");
            }

            fw.close();
            bf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}