class Solution {
    public int numDecodings(String s) {
        int a = 1, b = 0;
        if (s.charAt(0) >= '1' && s.charAt(0) <= '9') {
            b = 1;
        }

        int ans = b;
        for (int i = 2; i <= s.length(); ++i) {
            ans = 0;
            if (s.charAt(i - 1) >= '1' && s.charAt(i - 1) <= '9') {
                ans += b;
            }

            int num = Integer.parseInt(s.substring(i - 2, i));
            if (num > 9 && num < 27) {
                ans += a;
            }
            a = b;
            b = ans;
        }
        return ans;
    }
}