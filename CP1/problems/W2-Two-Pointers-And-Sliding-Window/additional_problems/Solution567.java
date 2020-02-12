class Solution567 {
    public boolean checkInclusion(String s1, String s2) {
        int count[] = new int[26];
        int need_count[] = new int[26];
        int start = 0;

        for (char s : s1.toCharArray()) 
            need_count[s-'a']++;

        for (int end = 0; end < s2.length(); ++end) {
            count[s2.charAt(end)-'a']++;
            System.out.println(s2.substring(start, end+1));
            if (!testIfSatisfy(count, need_count, s1)) continue;

            while (testIfSatisfy(count, need_count, s1)) {
                count[s2.charAt(start)-'a']--;
                start++;
            }

            if (end - start + 2 == s1.length())
                return true;
        }
        return false;
    }

    private Boolean testIfSatisfy(int[] count, int[] need_count, String s1) {
        for (char c : s1.toCharArray()) {
            if (count[c-'a'] < need_count[c-'a'])
                return false;
        }
        return true;
    }
}