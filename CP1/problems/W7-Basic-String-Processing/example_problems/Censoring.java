import java.io.*;

// http://www.usaco.org/index.php?page=viewproblem2&cpid=526

class Censoring {
    public static void main(String...args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("censor.in")));
            String S = br.readLine().trim();
            String T = br.readLine().trim();
            br.close();

            int cc = 0;
            char ans[] = new char[S.length()];
            char sChars[] = S.toCharArray();
            char tChars[] = T.toCharArray();
            for (int i = 0; i < S.length(); ++i) {
                ans[cc] = sChars[i];
                int ss = cc, tt = T.length() - 1;
                // Evaluate backwards
                while (ss >= 0 && tt >= 0 && ans[ss] == tChars[tt]) {
                    --ss;
                    --tt;
                }
                if (tt == -1) cc -= T.length();
                ++cc;
            }

            FileWriter fw = new FileWriter(new File("censor.out"));
            fw.write(new String(ans, 0, cc));
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}