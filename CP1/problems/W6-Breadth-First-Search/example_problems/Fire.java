import java.io.*;
import java.util.ArrayDeque;

// TODO: 1/2 TLE

class Fire {
    public static void main(String...args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
            String line = br.readLine().trim();
            int n_testcases = Integer.parseInt(line);

            for (int i = 0; i < n_testcases; ++i) {
                line = br.readLine().trim();
                String[] temp = line.split(" ");
                int row = Integer.parseInt(temp[1]);
                int col = Integer.parseInt(temp[0]);

                char [][]map = new char[row][col];
                int  [][]fire = new int[row][col];

                for (int r = 0; r < row; ++r) {
                    for (int j = 0; j < col; ++j) {
                        fire[r][j] = Integer.MAX_VALUE;
                    }
                }

                for (int r = 0; r < row; ++r) {
                    line = br.readLine().trim();
                    int j = 0;
                    for (char c : line.toCharArray()) {
                        map[r][j++] = c;
                    }
                }

                // Calculate fire time
                ArrayDeque<int[]> adFire = new ArrayDeque<>();
                for (int r = 0; r < row; ++r) {
                    for (int j = 0; j < col; ++j) {
                        if (map[r][j] == '*') {
                            adFire.add(new int[]{r, j, 0});
                        }
                    }
                }

                while (adFire.size() != 0) {
                    int[] point = adFire.removeFirst();
                    int x = point[0];
                    int y = point[1];
                    int time = point[2];

                    if (x < 0 || x >= row || y < 0 || y >= col) continue;
                    if (time >= fire[x][y] || map[x][y] == '#') continue;
                    fire[x][y] = time;

                    int[] deltaX = new int[] { 1, -1, 0, 0 };
                    int[] deltaY = new int[] { 0, 0, 1, -1 };
                    for (int d = 0; d < 4; ++d) {
                        adFire.add(new int[]{x + deltaX[d], y + deltaY[d], time + 1});
                    }
                }

                ArrayDeque<int[]> adPeople = new ArrayDeque<>();
                for (int r = 0; r < row; ++r) {
                    for (int j = 0; j < col; ++j) {
                        if (map[r][j] == '@') {
                            adPeople.add(new int[]{r, j, 0});
                        }
                    }
                }

                int ans = -1;
                while(adPeople.size() > 0) {
                    int[] point = adPeople.removeFirst();
                    int x = point[0];
                    int y = point[1];
                    int time = point[2];

                    if (x < 0 || x >= row || y < 0 || y >= col) {
                        ans = time;
                        break;
                    }
                    if (time > fire[x][y] || map[x][y] == '#') continue;

                    int[] deltaX = new int[] { 1, -1, 0, 0 };
                    int[] deltaY = new int[] { 0, 0, 1, -1 };
                    for (int d = 0; d < 4; ++d) {
                        adPeople.add(new int[] { x + deltaX[d], y + deltaY[d], time + 1});
                    }
                }

                if (ans == -1){
                    pw.println("IMPOSSIBLE");
                } 
                else {
                    pw.println(ans);
                }
                pw.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}