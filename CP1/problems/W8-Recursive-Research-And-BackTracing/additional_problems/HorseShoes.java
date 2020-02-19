import java.io.*;

// http://www.usaco.org/index.php?page=viewproblem2&cpid=189

class HorseShoes {
    private static int ans = 0;
    public static void main(String...args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("hshoe.in")));
            int n = Integer.parseInt(br.readLine().trim());

            char[][] horseShoes = new char[n][n];
            for (int i = 0; i < n; ++i) {
                char shoes[] = br.readLine().trim().toCharArray();
                for (int j = 0; j < n; ++j) {
                    horseShoes[i][j] = shoes[j];
                }
            }
            br.close();

            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    dfs(i, j, new int[n][n], horseShoes, 0, 0);
                }
            }

            FileWriter fw = new FileWriter(new File("hshoe.out"));
            fw.write(String.valueOf(ans));
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void dfs(int i, int j, int[][] seen, char[][] horsesShoes,int left, int right) {
        if (i < 0 || i >= horsesShoes.length || j < 0 || j >= horsesShoes.length 
                || seen[i][j] == 1 || (right > 0 && horsesShoes[i][j] == '(') || right > left) {
            return;
        }

        if (horsesShoes[i][j] == '(') {
            left += 1;
        } else {
            right += 1;
        }

        if (right >= left) {
            ans = Math.max(ans, 2 * left);
        }

        seen[i][j] = 1;

        dfs(i + 1, j, seen, horsesShoes, left, right);
        dfs(i - 1, j, seen, horsesShoes, left, right);
        dfs(i, j + 1, seen, horsesShoes, left, right);
        dfs(i, j - 1, seen, horsesShoes, left, right);
    }
}