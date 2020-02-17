import java.io.*;

// https://open.kattis.com/problems/fruitbaskets
// TODO: 6/11 TLE

class FruitBasket {
    private static int totalWeight = 0;
    private static int[] weight;
    private static int[] prefixWeight;

    public static void main(String...args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String line = br.readLine().trim();
            int N = Integer.parseInt(line);

            weight = new int[N];
            prefixWeight = new int[N + 1];
            line = br.readLine().trim();
            int i = 0;
            for (String n : line.split(" ")) {
                weight[i++] = Integer.parseInt(n);
                prefixWeight[i] = prefixWeight[i - 1] + Integer.parseInt(n);
            }
            br.close();

            for (i = 0; i < N; ++i)
                pickFruit(i + 1, weight[i], N);

            System.out.println(totalWeight);

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private static void pickFruit(int start, int w, int N) {
        if (w >= 200)
            totalWeight += w;

        if (prefixWeight[N] - prefixWeight[start] + w < 200) {
            return;
        }

        for (int i = start; i < N; ++i) {
            pickFruit(i + 1, w + weight[i], N);
        }
        return;
    }
}