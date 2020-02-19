import java.io.*;
import java.util.ArrayList;

// http://www.usaco.org/index.php?page=viewproblem2&cpid=261

class BreedAssignment {
    private static int[][] relation;
    private static int count = 0;
    private static ArrayList<String> totalBreed = new ArrayList<>();
    public static void main(String...args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("assign.in")));
            String line = br.readLine();
            int nCows = Integer.parseInt(line.split(" ")[0]);
            int K = Integer.parseInt(line.split(" ")[1]);

            relation = new int[nCows + 1][nCows + 1];   // 0:no relation 1:same -1:different
            String[] breeds = new String[nCows + 1];

            for (int k = 0; k < K; ++k) {
                line = br.readLine().trim();
                String status = line.split(" ", 2)[0];

                line = line.split(" ", 2)[1];
                int first = Integer.parseInt(line.split(" ")[0]);
                int second = Integer.parseInt(line.split(" ")[1]);

                if (status.equals("S")) {
                    relation[first][second] = 1;
                    relation[second][first] = 1;
                } else {
                    relation[first][second] = -1;
                    relation[second][first] = -1;
                }
            }
            br.close();

            totalBreed.add("H");
            totalBreed.add("G");
            totalBreed.add("J");

            backtrace(1, nCows, breeds);

            FileWriter fw = new FileWriter(new File("assign.out"));
            fw.write(String.valueOf(count));
            fw.close();

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private static void backtrace(int pos, int nCows, String[] breeds) {
        if (pos > nCows) {
            ++count;
            return;
        }

        for (String b : totalBreed) {
            Boolean flag = true;
            for (int i = 1; i < pos; ++i) {
                if (relation[i][pos] == 1 && !breeds[i].equals(b)) {
                    flag = false;
                    break;
                }

                if (relation[i][pos] == -1 && breeds[i].equals(b)) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                breeds[pos] = b;
                backtrace(pos + 1, nCows, breeds);
            }
        }
    }
}