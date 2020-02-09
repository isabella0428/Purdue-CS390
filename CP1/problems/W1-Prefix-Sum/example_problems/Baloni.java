import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Vector;
import java.util.HashMap;

class Baloni {
    public static void main(String ... args) {
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

            int n_ballon = Integer.parseInt(bf.readLine().trim());

            String line = bf.readLine().trim();

            HashMap<Integer, Integer> map = new HashMap<>();
            
            int arrow = 0;
            for (String num : line.split(" ", n_ballon)) {
                int height = Integer.parseInt(num);
                if (map.containsKey(height)) {
                    int count = map.get(height);
                    if (count == 1) {
                        map.remove(height);
                    } else {
                        map.replace(height, map.get(height), map.get(height) - 1); 
                    }

                    if (map.containsKey(height - 1)) {
                        map.replace(height - 1, map.get(height - 1), map.get(height - 1) + 1);
                    } else {
                        map.put(height - 1, 1);
                    }
                } else {
                    arrow ++;
                    if (map.containsKey(height - 1)) {
                        map.replace(height - 1, map.get(height - 1), map.get(height - 1) + 1);
                    } else {
                        map.put(height - 1, 1);
                    }
                }
            }
            System.out.println(arrow);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}