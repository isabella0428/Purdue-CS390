import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

class FairPhotography {
    public static void main(String...args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("fairphoto.in")));
            String line = br.readLine().trim();
            int N = Integer.parseInt(line);

            Vector<int[]> position_breed = new Vector<>();           // "G" for 1 and "H" for -1
            for (int i = 0; i < N; ++i) {
                line = br.readLine().trim();
                int p = Integer.parseInt(line.split(" ", 2)[0]);
                String b = line.split(" ", 2)[1];

                if (b.equals("G")) {
                    position_breed.add(new int[]{p, 1});
                } else {
                    position_breed.add(new int[]{p, -1});
                }
            }
            br.close();

            Comparator<int[]> cmp = new Comparator<int[]>() {
                @Override
                public int compare(int[] a, int[] b) {
                    return a[0] - b[0];
                }
            };

            Collections.sort(position_breed, cmp);
            for (int i = 0; i < N; ++i) {
                System.out.println(String.format("p: %d breed:%d", position_breed.get(i)[0], position_breed.get(i)[1]));
            }

            HashMap<Integer, Integer> map = new HashMap<>();
            map.put(0, 0);

            int []prefix_sum = new int[N+1];
            int max_len = 0;
            for (int i = 1; i < N + 1; ++i) {
                prefix_sum[i] = prefix_sum[i-1] + position_breed.get(i-1)[1];
                System.out.println(String.format("Point %dth prefix %d", i - 1, prefix_sum[i]));
                if (map.containsKey(prefix_sum[i])) {
                                        int index = map.get(prefix_sum[i]);
                    System.out.println(String.format("Start:%d End:%d", position_breed.get(i - 1)[0], position_breed.get(index)[0]));
                    max_len = Math.max(max_len, position_breed.get(i - 1)[0] - position_breed.get(index)[0]);
                } else {
                    map.put(prefix_sum[i], i);
                }
            }

            File outFile = new File("fairphoto.out");
            FileWriter fw = new FileWriter(outFile);
            fw.write(String.valueOf(max_len));
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}