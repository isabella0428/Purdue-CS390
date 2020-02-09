import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Arrays;

// http://www.usaco.org/index.php?page=viewproblem2&cpid=89

class CowLineUp {
    public static void main(String ... args) {
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream("lineup.in")));

            int n_cows = Integer.parseInt(bf.readLine().trim());
            int [][]cows = new int[n_cows][2];
            HashSet<Integer> breed_ids = new HashSet<>();

            for (int i = 0; i < n_cows; ++i) {
                String line = bf.readLine().trim();
                cows[i][0] = Integer.parseInt(line.split(" ", 2)[0]);
                cows[i][1] = Integer.parseInt(line.split(" ", 2)[1]);
                breed_ids.add(cows[i][1]);
            }
            bf.close();

            // Sort cows location and breed id
            Comparator<int[]> comp = new Comparator<int[]>() {
                @Override
                public int compare(int[] a, int[] b) {
                    for (int i = 0; i < a.length; ++i) {
                        if (a[i] < b[i]) return -1;
                        if (a[i] > b[i]) return 1;
                    }
                    return 0;
                }
            };

            Arrays.sort(cows, comp);

            int l = 0, r = 0, ans = Integer.MAX_VALUE;
            HashMap<Integer, Integer> breed_shoot = new HashMap<>();
            while(l <= r && r < n_cows) {
                while (r < n_cows && breed_shoot.keySet().size() < breed_ids.size()) {
                    int b_id = cows[r][1];

                    if (breed_shoot.containsKey(b_id)) {
                        breed_shoot.replace(b_id, breed_shoot.get(b_id), breed_shoot.get(b_id) + 1);
                    } else {
                        breed_shoot.put(b_id, 1);
                    }
                    r++;
                }

                while (l <= r && breed_shoot.keySet().size() == breed_ids.size()) {
                    int b_id = cows[l][1];

                    if (breed_shoot.get(b_id) > 1) {
                        breed_shoot.replace(b_id, breed_shoot.get(b_id), breed_shoot.get(b_id) - 1);
                    } else {
                        breed_shoot.remove(b_id);
                    }
                    l++;
                }

                int width = cows[r - 1][0] - cows[l - 1][0];
                if (width < ans) {
                    ans = width;
                }
            }
            
            File outFile = new File("lineup.out");
            FileWriter fw = new FileWriter(outFile);
            fw.write(String.valueOf(ans));
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}