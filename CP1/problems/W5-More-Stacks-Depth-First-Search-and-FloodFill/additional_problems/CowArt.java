import java.io.*;

// http://www.usaco.org/index.php?page=viewproblem2&cpid=414

class CowArt {
    public static void main(String...args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("cowart.in")));
            String line = br.readLine().trim();
            int r = Integer.parseInt(line);

            char grid1[][] = new char[r][r];
            char grid2[][] = new char[r][r];
            for (int i = 0; i < r; ++i) {
                line = br.readLine().trim();
                int j = 0;      
                for (char c : line.toCharArray()) {
                    grid1[i][j] = c;
                    grid2[i][j++] = c;
                }
            }
            br.close();

            // Human area
            int humanAreas = 0;
            for (int i = 0; i < r; ++i) {
                for (int j = 0; j < r; ++j) {
                    if (grid1[i][j] != 'N') {
                        dfs(i, j, grid1, grid1[i][j]);
                        ++humanAreas;
                    }
                }
            }

            // Cow area
            int cowAreas = 0;
            for (int i = 0; i < r; ++i) {
                for (int j = 0; j < r; ++j) {
                    if (grid2[i][j] == 'B') {
                        dfs(i, j, grid2, 'B');
                        ++cowAreas;
                    }

                    if (grid2[i][j] != 'N') {
                        dfs(i, j, grid2, 'R', 'G');
                        cowAreas++;
                    }
                }
            }

            FileWriter fw = new FileWriter(new File("cowart.out"));
            fw.write(String.valueOf(humanAreas) + " " + String.valueOf(cowAreas));
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void dfs(int i, int j, char[][] grid, char...colors) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) {
            return;
        }

        Boolean flag = false;
        for (char c : colors) {
            if (grid[i][j] == c)
                flag = true;
        }

        if (!flag) return;

        grid[i][j] = 'N';
        
        int []deltaX = new int[]{1, -1, 0, 0};
        int []deltaY = new int[]{0, 0, 1, -1};
        for (int d = 0; d < 4; ++d) {
            dfs(i + deltaX[d], j + deltaY[d], grid, colors);
        }
    }
}