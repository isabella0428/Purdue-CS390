class Solution76 {
    public String minWindow(String s, String t) {
        if (s == null || s.length() < t.length() || s.length() == 0) {
            return "";
        }

        int t_count[] = new int[256];
        int cur_count[] = new int[256];

        for (Character c : t.toCharArray()) {
            t_count[c] ++;
        }

        int start = 0, min = Integer.MAX_VALUE;
        String ans = "";
        for(int end = 0; end < s.length(); ++end) {
            Character c = s.charAt(end);
            cur_count[c] ++;

            if (!testIfSatisfied(cur_count, t_count))
                continue;

            while (start <= end) {
                Character start_c = s.charAt(start);
                if (t_count[start_c] > 0) {
                    cur_count[start_c] --;
                }

                start++;
                if (!testIfSatisfied(cur_count, t_count)) {
                    break;
                }
            }

            int new_len = end - start + 2;
            if (new_len < min) {
                min = new_len;
                ans = s.substring(start - 1, end + 1);
            }
        }
        return ans;
    }

    private Boolean testIfSatisfied(int[] cur_count, int[] t_count) {
        for (int i = 0; i < 256; ++i) {
            if (cur_count[i] < t_count[i]) {
                System.out.println(i);
                return false;
            }
        }
        return true;
    }
}