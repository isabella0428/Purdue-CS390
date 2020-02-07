// https://open.kattis.com/problems/cd

import java.io.BufferedReader;
import java.io.InputStreamReader;

class CD {
    public static void main(String ... args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int jack_num = 0, jill_num = 0;

        try {
            while (true) {
                int exist[] = new int[10010000];

                // Get CD nums of Jack and Jill
                String num_line = br.readLine();
                String[] nums = num_line.split(" ", 2);
                jack_num = Integer.parseInt(nums[0].strip());
                jill_num = Integer.parseInt(nums[1].strip());

                if (jack_num == 0 && jill_num == 0) {
                    break;
                }

                for (int i = 0; i < jack_num; ++i) {
                    String num = br.readLine().strip();
                    exist[Integer.parseInt(num)] = 1;
                }

                int count = 0;

                for (int j = 0; j < jill_num; ++j) {
                    if (exist[Integer.parseInt(br.readLine().strip())] == 1) {
                        ++count;
                    }
                }
                System.out.println(count);
            }
            br.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        
    }
}