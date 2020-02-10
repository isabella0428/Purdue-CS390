import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.File;
import java.util.Arrays;
import java.util.HashSet;

//http://www.usaco.org/index.php?page=viewproblem2&cpid=571

class HighCardWins {
    public static void main(String ... args) {
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream("highcard.in")));

            int N = Integer.parseInt(bf.readLine().trim());
            int[] bessie_cards = new int[N];
            int[] elsie_cards  = new int[N];
            HashSet<Integer> elsie = new HashSet<>();
            
            for(int i = 0; i < N; ++i) {
                int elsie_point = Integer.parseInt(bf.readLine().trim());
                elsie_cards[i] = elsie_point;
                elsie.add(elsie_point);
            }

            int j = 0;
            for(int i = 1; i <= 2 * N; ++i) {
                if(!elsie.contains(i)) {
                    bessie_cards[j] = i;
                    ++j;
                }
            }

            bf.close();

            // Sort elsie cards
            Arrays.sort(elsie_cards);

            int points_gained = 0, b = 0;
            int used[] = new int[N];
            for(int e_point : elsie_cards) {
                while(b < N && (bessie_cards[b] < e_point || used[b] == 1)) ++b;
                if(b == N) break;
                ++points_gained;
                used[b] = 1;
            }

            File outFile = new File("highcard.out");
            FileWriter fw = new FileWriter(outFile);
            fw.write(String.valueOf(points_gained));
            fw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}