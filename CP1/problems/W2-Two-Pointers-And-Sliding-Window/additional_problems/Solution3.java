class Solution3 {
    public int lengthOfLongestSubstring(String s) {
        if (s.equals(""))   return 0;

        int []count = new int[256];
        int start = 0, max_length = Integer.MIN_VALUE;
        for (int end = 0; end < s.length(); ++end) {
            count[s.charAt(end)] ++;

            if (count[s.charAt(end)] == 1) {
                max_length = Math.max(max_length, end - start + 1);
            }

            while(start <= end && count[s.charAt(end)] > 1) {
                count[s.charAt(start)]--;
                start++;
            }
        }
        return max_length;
    }
}