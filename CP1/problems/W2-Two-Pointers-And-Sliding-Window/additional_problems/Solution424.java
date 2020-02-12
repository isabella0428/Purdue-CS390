class Solution424 {
    public int characterReplacement(String s, int k) {
        if (s.equals(""))   return 0;

        int count[] = new int[26];
        int longest = Integer.MIN_VALUE;
        int start = 0;
        for (int end = 0; end < s.length(); ++end) {
            count[s.charAt(end)-'A']++;
            // System.out.println(String.format("Start:%d End:%d Length:%d", start, end, end - start + 1));

            if (testIfSatisfy(count, k)) {
                longest = Math.max(longest, end - start + 1);
            }

            while (start <= end && !testIfSatisfy(count, k)) {
                count[s.charAt(start)-'A']--;
                start ++;
            }
        }
        return longest;
    }

    private Boolean testIfSatisfy(int[] count, int k) {
        int total_count = 0, max_count = 0;
        for (int i = 0; i < 26; ++i) {
            total_count += count[i];
            max_count = Math.max(count[i], max_count);
        }
        return total_count - max_count <= k;
    }
}