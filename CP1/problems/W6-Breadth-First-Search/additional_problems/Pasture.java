import java.io.*;
import java.util.ArrayDeque;

// http://www.usaco.org/index.php?page=viewproblem2&cpid=432#
// TODO: Wrong answer   3/10

class Pasture {
    public static void main(String...args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("decorate.in")));
            String line = br.readLine().trim();
            int nPasture = Integer.parseInt(line.split(" ")[0]);
            int nRoad = Integer.parseInt(line.split(" ")[1]);

            int matrix[][] = new int[nPasture][nPasture];
            for (int k = 0; k < nRoad; ++k) {
                line = br.readLine().trim();
                int start = Integer.parseInt(line.split(" ")[0]) - 1;
                int end = Integer.parseInt(line.split(" ")[1]) - 1;
                matrix[start][end] = 1;
                matrix[end][start] = 1;
            }
            br.close();

            int JCount = 0;
            ArrayDeque<Integer> ad = new ArrayDeque<>();
            char []seen = new char[nPasture];
            for (int i = 0; i < nPasture; ++i) {
                if (seen[i] != 'J' && seen[i] != 'F') {
                    seen[i] = 'J';
                    JCount++;
                    ad.add(i);

                    while (ad.size() > 0) {
                        int num = ad.removeFirst();
                        for (int j = 0; j < nPasture; ++j) {
                            if (seen[j] != 'J' && seen[j] != 'F' && matrix[j][num] == 1) {
                                seen[j] = 'J';
                                if (seen[num] == 'J') {
                                    seen[j] = 'F';
                                }
                                if (seen[j] == 'J') JCount++;
                                // System.out.println(String.format("p:%d %c", j, seen[j]));
                                ad.add(j);
                            }
                        }
                    }
                }
            }

            Boolean flag = true;
            for (int i = 0; i < nPasture; ++i) {
                for (int j = 0; j < nPasture; ++j) {
                    if (matrix[i][j] == 1 && seen[i] == seen[j]) {
                        flag = false;
                        break;
                    }
                }
            }

            if (!flag) JCount = -1;
            FileWriter fw = new FileWriter(new File("decorate.out"));
            fw.write(String.valueOf(JCount));
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}