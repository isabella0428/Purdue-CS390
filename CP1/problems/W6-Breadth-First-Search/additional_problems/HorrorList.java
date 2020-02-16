import java.io.*;
import java.util.ArrayDeque;

// https://open.kattis.com/problems/horror

class HorrorList {
    public static void main(String...args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String line = br.readLine().trim();
            int numMovie = Integer.parseInt(line.split(" ")[0]);
            int numHorrible = Integer.parseInt(line.split(" ")[1]);
            int numSimilar = Integer.parseInt(line.split(" ")[2]);
            
            line = br.readLine().trim();
            int[] dist = new int[numMovie];
            for (int i = 0; i < numMovie; ++i) {
                dist[i] = Integer.MAX_VALUE;
            }

            
            int[] horribleList = new int[numHorrible];
            ArrayDeque<Integer> ad = new ArrayDeque<>();
            int j = 0;
            for (String num : line.split(" ")) {
                int a = Integer.parseInt(num);
                horribleList[j++] = a;
                dist[a] = 0;
                ad.add(a);
            }

            int matrix[][] = new int[numMovie][numMovie];
            for (int i = 0; i < numSimilar; ++i) {
                line = br.readLine().trim();
                int first = Integer.parseInt(line.split(" ")[0]);
                int second = Integer.parseInt(line.split(" ")[1]);
                matrix[first][second] = 1;
                matrix[second][first] = 1;
            }

            while (ad.size() != 0) {
                int num = ad.removeFirst();

                for (int i = 0; i < numMovie; ++i) {
                    if (matrix[num][i] == 1 && dist[i] == Integer.MAX_VALUE) {
                        dist[i] = dist[num] + 1;
                        ad.add(i);
                    } 
                }
            }

            int maxValue = Integer.MIN_VALUE, ans = -1;
            for (int i = 0; i < numMovie; ++i) {
                if (dist[i] > maxValue) {
                    maxValue = dist[i];
                    ans = i;
                }
            }

            System.out.println(ans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}