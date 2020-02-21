class Solution {
    public int binaryGap(int N) {
        String n = Integer.toBinaryString(N);

        int pos = 0, prev = -1, ans = 0;
        for (char c : n.toCharArray()) {
            if (c == '1') {
                if (prev == -1) {
                    prev = pos;
                } else {
                    ans = Math.max(pos - prev, ans);
                    prev = pos;
                }
            }
            ++pos;
        }

        return ans;
    }
}