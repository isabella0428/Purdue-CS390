import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.File;

// http://www.usaco.org/index.php?page=viewproblem2&cpid=260

class BreedProximity {
    public static void main(String ... args) {
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream("proximity.in")));
            
            String line = bf.readLine();
            int n_cows = Integer.parseInt(line.split(" ", 2)[0]);
            int interval = Integer.parseInt(line.split(" ", 2)[1]);
            int[] prev = new int[1000000];

            int crowded_breed_id = -1;
            for(int i = 1; i <= n_cows; ++i) {
                int breed_id = Integer.parseInt(bf.readLine().trim());
                if ((prev[breed_id] != 0) && (i - prev[breed_id] <= interval)) {
                    if(breed_id > crowded_breed_id)
                        crowded_breed_id = breed_id;
                }
                prev[breed_id] = i;
            }

            bf.close();
            
            File outFile = new File("proximity.out");
            FileWriter fw = new FileWriter(outFile);
            fw.write(String.valueOf(crowded_breed_id));
            fw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}