class Solution {
    public int countBinarySubstrings(String s) {
        int i = 0, a = 0, b = 0, sum = 0, n = s.length();
        while (i < n) {
            b = 1; // consecutive letter num
            ++i;
            while (i < n && s.charAt(i) == s.charAt(i - 1)) {
                ++i;
                ++b;
            }
            sum += Math.min(a, b);
            a = b;
        }
        return sum;
    }
}