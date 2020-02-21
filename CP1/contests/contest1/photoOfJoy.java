import java.io.*;
import java.util.*;

// TODO

class photoOfJoy {
    public static void main(String...args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String line = br.readLine().trim();
            int N_cows = Integer.parseInt(line.split(" ")[0]);
            int N_families = Integer.parseInt(line.split(" ")[1]);
            int K = Integer.parseInt(line.split(" ")[2]);

            HashMap<Integer, int[]> mothers_map = new HashMap<>();
            for (int i = 0; i < N_families; ++i) {
                line = br.readLine().trim();
                int mom_id = Integer.parseInt(line.split(" ", 2)[0]);
                line = line.split(" ", 2)[1];

                int max_id = Integer.MIN_VALUE, min_id = Integer.MAX_VALUE;
                String[] daughters = line.split(" ");
                for (int p = 0; p < daughters.length - 1; ++p) {
                    int d_id = Integer.parseInt(daughters[p]);
                    max_id = Math.max(max_id, d_id);
                    min_id = Math.min(min_id, d_id);
                }

                mothers_map.put(mom_id, new int[]{min_id, max_id});
            }

            List<Integer> moms = new ArrayList<>(mothers_map.keySet());
            Collections.sort(moms);

            Vector<int[]> ans = new Vector<>();
            int min_len = Integer.MAX_VALUE;

            for (int i = 0; i < moms.size() - K + 1; ++i) {
                int left = moms.get(i);
                int right = moms.get(i + K - 1);

                System.out.println(String.format("Overall Left:%d Right:%d", left, right));

                for (int j = i; j < i + K; ++j) {
                    int[] cur_mom = mothers_map.get(moms.get(j));
                    int cur_left = cur_mom[0];
                    int cur_right = cur_mom[1];
                    System.out.println(String.format("Current left: %d Current right:%d", cur_left, cur_right));
                    if (cur_left <= right && cur_left >= left) {
                        System.out.println("left okay");
                        continue;
                    }
                    if (cur_right <= right && cur_right >= left) {
                        System.out.println("right okay");
                        continue;
                    }
                    
                    int left_delta, right_delta;
                    if (cur_left < left) {
                        left_delta = left - cur_left;
                    } else {
                        left_delta = cur_left - right;
                    }

                    if (cur_right < left) {
                        right_delta = left - cur_right;
                    } else {
                        right_delta = cur_right - right;
                    }

                    if (left_delta < right_delta) {
                        left = Math.min(cur_left, left);
                        right = Math.max(cur_left, right);
                        System.out.println("Aloha");
                    } else {
                        left = Math.min(cur_right, left);
                        right = Math.max(cur_right, right);
                    }

                    if (right - left < min_len) {
                        ans = new Vector<>();
                        ans.add(new int[] { left, right });
                    }

                    if (right - left == min_len)
                        ans.add(new int[] { left, right });
                    min_len = Math.min(min_len, right - left);
                }
            }

            for (int[] a : ans) {
                System.out.println(String.format("%d %d", a[0], a[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}