import java.io.*;
import java.util.*;

// https://open.kattis.com/problems/goodmorning

class Goodmorning {
    private static HashMap<Integer, int[]> map;

    public static void main(String...args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String line = br.readLine().trim();
            int N = Integer.parseInt(line);

            map = new HashMap<>();
            map.put(1, new int[]{0, 0});
            map.put(2, new int[]{0, 1});
            map.put(3, new int[]{0, 2});
            map.put(4, new int[]{1, 0});
            map.put(5, new int[]{1, 1});
            map.put(6, new int[]{1, 2});
            map.put(7, new int[]{2, 0});
            map.put(8, new int[]{2, 1});
            map.put(9, new int[]{2, 2});
            map.put(0, new int[]{3, 1});

            // 1 ~ 200 possible numbers
            HashSet<Integer> okay = new HashSet<>();
            for (int i = 1; i <= 200; ++i) {
                char[] ch = String.valueOf(i).toCharArray();

                Boolean flag = true;
                for (int j = 1; j < ch.length; ++j) {
                    int jminusRow = map.get(ch[j - 1] - '0')[0];
                    int jminusCol = map.get(ch[j - 1] - '0')[1];
                    int jRow = map.get(ch[j] - '0')[0];
                    int jCol = map.get(ch[j] - '0')[1];

                    if (jminusRow > jRow || jminusCol > jCol) {
                        flag = false;
                        break;
                    }
                }
                
                if (flag) okay.add(i);
            }

            for (int request = 0; request < N; ++request) {
                line = br.readLine().trim();

                int target = Integer.parseInt(line);
                int ans = -1;
                
                int i = 0; 
                while(true){
                    if (okay.contains(target + i)) {
                        ans = target + i;
                        break;
                    }

                    if (okay.contains(target - i)) {
                        ans = target - i;
                        break;
                    }
                    ++i;
                }

                System.out.println(ans);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}