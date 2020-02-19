import java.io.*;
import java.util.*;

// TODO: Have no idea

class BalancedTeams {
    public static void main(String...args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("bteams.in")));
            
            int[] cows = new int[12];
            for (int i = 0; i < 12; ++i) {
                cows[i] = Integer.parseInt(br.readLine().trim());
            }
            br.close();
            Arrays.sort(cows);

            Vector<int[]> teams = new Vector<>();
            for (int i = 0; i < 4; ++i) {
                teams.add(new int[] { cows[11 - i], 1 });
            }

            for (int i = 7; i >= 0; --i) {
                teams.sort(new Comparator<int[]>() {
                    @Override
                    public int compare(int[] a, int[]b) {
                        return a[0] - b[0];
                    }
                });

                for (int a[] : teams) {
                    System.out.println(a[0]);
                }
                System.out.println();


                while(teams.size() > 0) {
                    int[] a = teams.remove(0);
                    if (a[1] == 4) {
                        teams.add(a);
                        continue;
                    }
                    a[0] += cows[i];
                    a[1] ++;
                    teams.add(a);
                    break;
                }
            }

            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;
            for (int a[] : teams) {
                System.out.println(a[0]);
                max = Math.max(a[0], max);
                min = Math.min(a[0], min);
            }

            FileWriter fw = new FileWriter(new File("bteams.out"));
            fw.write(String.valueOf(max - min));
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}