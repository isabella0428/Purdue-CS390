// http://www.usaco.org/index.php?page=viewproblem2&cpid=487

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Vector;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.File;

class Marathon {
    public static void main(String ... args) {
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream("marathon.in"))); 
            File outFile = new File("marathon.out");
            outFile.createNewFile();
            FileWriter writer = new FileWriter(outFile);

            // Read point number
            int n_points = Integer.parseInt(bf.readLine().trim());
            Vector<int[]> points = new Vector<>();

            for(int i = 0; i < n_points; ++i) {
                int x, y;
                String line = bf.readLine();
                x = Integer.parseInt(line.split(" ", 2)[0].trim());
                y = Integer.parseInt(line.split(" ", 2)[1].trim());
                points.add(new int[] {x, y});
            }

            bf.close();

            int dist = 0;
            // Calculate total distance
            for (int i = 0; i < points.size() - 1; ++i) {
                int[] p1 = points.get(i);
                int[] p2 = points.get(i + 1);
                dist += calculateDist(p1, p2);
            }

            int best_save_dist = 0, save_dist = 0;
            // Find best_skip point
            for(int i = 1; i < points.size() - 1; ++i) {
                int[] cur_node = points.get(i);
                int[] prev_node = points.get(i - 1);
                int[] next_node = points.get(i + 1);
                int no_skip = calculateDist(cur_node, prev_node) + calculateDist(cur_node, next_node);
                int skip = calculateDist(prev_node, next_node);
                save_dist = no_skip - skip;

                if(save_dist > best_save_dist) {
                    best_save_dist = save_dist;
                }
            }

            String min_dist = String.valueOf(dist - best_save_dist);
            writer.write(min_dist);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int calculateDist(int[] p1, int[] p2) {
        return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
    } 
}