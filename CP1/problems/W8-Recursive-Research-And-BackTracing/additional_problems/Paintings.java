import java.io.*;
import java.util.*;

// https://open.kattis.com/problems/paintings

class Paintings {
    private static int ans;
    private static String best;
    private static HashMap<String, Integer> map;
    public static void main(String...args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String line = br.readLine().trim();
            int times = Integer.parseInt(line);

            for (int t = 0; t < times; ++t) {
                int nColors = Integer.parseInt(br.readLine().trim());

                map = new HashMap<>();

                String[] colors = new String[nColors];
                int s = 0;
                for (String c : br.readLine().trim().split(" ")) {
                    colors[s++] = c;
                    map.put(c, s - 1);
                }

                int[][] conflicts = new int[nColors][nColors];
                int nConflicts = Integer.parseInt(br.readLine().trim());
                for (int i = 0; i < nConflicts; ++i) {
                    line = br.readLine().trim();
                    String first = line.split(" ")[0];
                    String second = line.split(" ")[1];
                    conflicts[map.get(first)][map.get(second)] = 1;
                    conflicts[map.get(second)][map.get(first)] = 1;
                }

                ans = 0;
                best = "";

                // Start to backtrace
                for (int i = 0; i < nColors; ++i) {
                    ArrayList<Integer> temp = new ArrayList<>();
                    temp.add(i);
                    backtrace(temp, nColors, conflicts, colors);
                }

                System.out.println(ans);
                System.out.println(best);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void backtrace(ArrayList<Integer> cur, int nColors, int[][]conflicts, String[] colors) {
        if (cur.size() == nColors) {
            ++ans;
            if (best.equals("")) {
                String temp = "";
                for (Integer n : cur) {
                    temp += colors[n];
                    temp += " ";
                }
                best = temp.trim();
            }
            return;
        }

        int prevColor = cur.get(cur.size() - 1);
        for (int i = 0; i < nColors; ++i) {
            if (!cur.contains(i) && conflicts[prevColor][i] == 0) {
                cur.add(i);
                backtrace(cur, nColors, conflicts, colors);
                cur.remove(cur.size() - 1);
            }
        }
    }
}